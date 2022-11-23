package org.dogej.commands;

import io.github.kosik.simplejsonrpc.client.JsonRpcClient;
import org.dogej.models.util.ValidateAddress;

public class UtilAPI extends Operation {

    public UtilAPI(final JsonRpcClient jsonRpcClient) {
        super(jsonRpcClient);
    }

    /**
     * @return information about the given address.
     */
    public ValidateAddress getValidateAddress(final String address){
        return getJsonRpcClient().createRequest()
                .version(JSON_RPC_VERSION)
                .id(generateId())
                .method("validateaddress")
                .params(address)
                .returnAs(ValidateAddress.class).execute();
    }

}
