package org.dogej.commands;

import io.github.kosik.simplejsonrpc.client.JsonRpcClient;
import org.dogej.models.blockchain.Transaction;
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


    /**
     * Return the raw transaction data.
     * By default this function only works for mempool transactions. When called with a blockhash argument,
     * getrawtransaction will return the transaction if the specified block is available and the transaction is found
     * in that block. getrawtransaction returns the transaction if it is
     * in the mempool, or if -txindex is enabled and the transaction is in a block in the blockchain.
     *
     * Hint: Use gettransaction for wallet transactions.
     * If verbose is ‘true’, returns an Object with information about ‘txid’.
     * If verbose is ‘false’ or omitted, returns a string that is serialized, hex-encoded data for ‘txid’.
     *
     * curl --user user_name --data-binary '{"jsonrpc": "1.0", "id":"0", "method": "getrawtransaction",
     * "params": ["b8187e268e947167b7a46e343e9315151219119672c87957f2725bd16ebcc7cc", true]}' -H 'content-type: text/plain;' http://127.0.0.1:22555/
     */
    public Transaction getRawTx(final String transactionId, final boolean verbose) {
        return getJsonRpcClient().createRequest()
                .version(JSON_RPC_VERSION)
                .id(generateId())
                .method("getrawtransaction")
                .params(transactionId, verbose)
                .returnAs(Transaction.class).execute();
    }
}
