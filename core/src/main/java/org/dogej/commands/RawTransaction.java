package org.dogej.commands;

import io.github.kosik.simplejsonrpc.client.JsonRpcClient;
import org.dogej.models.rawtx.SignTxResponse;
import org.dogej.models.rawtx.TransactionInput;

import java.util.Map;

public class RawTransaction extends Operation {
    public RawTransaction(final JsonRpcClient jsonRpcClient){
        super(jsonRpcClient);
    }

    /**
     * Create a transaction spending the given inputs and creating new outputs. Outputs can be addresses or data.
     * Note that the transaction’s inputs are not signed, and it is not stored in the wallet or transmitted to the network.
     *
     * NB: all the remaining of funds of Unspent will be charged Miner. To avoid such behaviour add second receiver address.
     *
     * @return hex-encoded raw transaction.
     */
    public String createRawTransaction(final TransactionInput[] inputs, final Map<String, Double> recipient) {
        return getJsonRpcClient().createRequest()
                .version(JSON_RPC_VERSION)
                .id(generateId())
                .method("createrawtransaction")
                .params(inputs, recipient)
                .returnAs(String.class).execute();
    }

    public String signRawTransaction(final String hexEncodedRawTx){
        final SignTxResponse signTxResponse = getJsonRpcClient().createRequest()
                .version(JSON_RPC_VERSION)
                .id(generateId())
                .method("signrawtransaction")
                .params(hexEncodedRawTx)
                .returnAs(SignTxResponse.class).execute();

        return signTxResponse.getHex();
    }

    public String sendRawTransaction(final String signedTxHex){
        return getJsonRpcClient().createRequest()
                .version(JSON_RPC_VERSION)
                .id(generateId())
                .method("sendrawtransaction")
                .params(signedTxHex)
                .returnAs(String.class).execute();
    }
}
