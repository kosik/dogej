package org.dogej;

import io.github.kosik.simplejsonrpc.client.JsonRpcClient;
import io.github.kosik.simplejsonrpc.client.Transport;

import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.dogej.commands.BlockchainAPI;
import org.dogej.commands.WalletAPI;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class DogecoinNodeClient {
    private CloseableHttpClient httpClient;

    private String host;
    private JsonRpcClient jsonRpcClient;
    private Transport transport;

    private BlockchainAPI blockchainAPI;
    private WalletAPI walletAPI;

    public DogecoinNodeClient(final String host, final String username, final String pw) {
        this.host = host;
        final String encoding = Base64.getEncoder().encodeToString((username + ":" + pw).getBytes());
        jsonRpcClient = new JsonRpcClient(buildTransport(host, encoding));
    }

    private Transport buildTransport(final String host, final String credentials) {
        return new Transport() {
            @Override public String pass(final String requestBody) throws IOException {
                final HttpPost post = new HttpPost(host);
                post.setEntity(new StringEntity(requestBody, StandardCharsets.UTF_8));

                post.setHeader(HttpHeaders.CONTENT_TYPE, "text/plain; charset=utf-8");
                post.setHeader(HttpHeaders.AUTHORIZATION, "Basic " + credentials);

                try (final CloseableHttpResponse httpResponse = getHttpClient().execute(post)) {
                    return EntityUtils.toString(httpResponse.getEntity(), StandardCharsets.UTF_8);
                } }
        };
    }

    private CloseableHttpClient getHttpClient() {
        if(null == httpClient){
            httpClient = HttpClients.createDefault();

        }
        return httpClient;
    }

    public BlockchainAPI getBlockchainAPI() {
        if(null == blockchainAPI){
            blockchainAPI = new BlockchainAPI(getJsonRpcClient());
        }
        return blockchainAPI;
    }

    public WalletAPI getWalletAPI() {
        if(null == walletAPI){
            walletAPI = new WalletAPI(getJsonRpcClient());
        }
        return walletAPI;
    }

    public String getHost() {
        return host;
    }

    public JsonRpcClient getJsonRpcClient() {
        return jsonRpcClient;
    } //getJsonRpcClient
}
