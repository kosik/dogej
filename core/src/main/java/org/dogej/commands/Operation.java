package org.dogej.commands;

import io.github.kosik.simplejsonrpc.client.JsonRpcClient;
import io.github.kosik.simplejsonrpc.client.builder.RequestBuilder;

import java.util.UUID;

class Operation {
    final protected int MIN_CONF = 15;
    final protected int JSON_RPC_VERSION = 1;

    private JsonRpcClient jsonRpcClient;

    Operation(final JsonRpcClient jsonRpcClient){
        this.jsonRpcClient = jsonRpcClient;
    }

    String generateId(){
        return UUID.randomUUID().toString();
    }

    protected RequestBuilder getRequestBuilder(){
        return getJsonRpcClient()
                .createRequest()
                .version(JSON_RPC_VERSION)
                .id(generateId());
    }

    JsonRpcClient getJsonRpcClient() {
        return jsonRpcClient;
    }
}
