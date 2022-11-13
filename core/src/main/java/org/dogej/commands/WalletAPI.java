package org.dogej.commands;

import io.github.kosik.simplejsonrpc.client.JsonRpcClient;
import org.dogej.models.wallet.QueryOptions;
import org.dogej.models.wallet.Unspent;
import org.dogej.models.wallet.WalletInfo;
import org.dogej.models.wallet.WalletTransaction;

import java.util.Collection;

public class WalletAPI extends Operation {

    public WalletAPI(final JsonRpcClient jsonRpcClient){
        super(jsonRpcClient);
    }

    /**
     * @return an object containing various wallet state info.
     */
    public WalletInfo getWalletInfo(){
        return getJsonRpcClient().createRequest()
                .version(JSON_RPC_VERSION)
                .id(generateId())
                .method("getwalletinfo")
                .params()
                .returnAs(WalletInfo.class).execute();
    }

    /**
     * @return a new address for receiving payments.
     *
     */
    public String getNewAddress() {
        return getJsonRpcClient().createRequest()
                .version(JSON_RPC_VERSION)
                .id(generateId())
                .method("getnewaddress")
                .params()
                .returnAs(String.class).execute();
    }

    /**
     *
     * Returns the list of addresses for the given account. Arguments: 1. "account" (string, required) The account name<pre></pre>
     * @return array of Dogecoin address associated with the given account
     */
    public String[] getAddressesByAccount(){
        return getJsonRpcClient().createRequest()
                .version(JSON_RPC_VERSION)
                .id(generateId())
                .method("getaddressesbyaccount")
                .params("")
                .returnAsArray(String.class).execute();
    }

    /**
     * Dumps all wallet keys in a human-readable format to a node server-side file into ./backups directory.
     * A versions earlier 1.14.6 saves it into ~/Documents/bitcoin/share/rpcauth<pre></pre>
     *
     * Command does not allow overwriting existing files; Imported scripts are included in the dumpfile,
     * but corresponding BIP173 addresses, etc. may not be added automatically by importwallet.
     * Note that if your wallet contains keys which are not derived from your HD seed (e.g. imported keys),
     * these are not covered by only backing up the seed itself, and must be backed up too (e.g. ensure you back up the whole dumpfile).
     *<pre></pre>
     *
     * @param fileName
     */
    public boolean backupWallet(final String fileName){
        final Object result = getJsonRpcClient().createRequest()
                .version(JSON_RPC_VERSION)
                .id(generateId())
                .method("backupwallet")
                .params(fileName)
                .returnAs(Object.class).execute();

        return null == result ? true : false;
    }

    /**
     * Reveals the private key corresponding to ‘address’. Then the importprivkey can be used with this output
     * @return The private key
     */
    public String dumpPrivKey(final String address){
        return getJsonRpcClient().createRequest()
                .version(JSON_RPC_VERSION)
                .id(generateId())
                .method("dumpprivkey")
                .params(address)
                .returnAs(String.class).execute();

    }

    /**
     * Adds a private key (as returned by dumpprivkey) to your wallet. Requires a new wallet backup.
     * Hint: use importmulti to import more than one private key.<pre></pre>
     *
     *  Note: This call can take over an hour to complete if rescan is true, during that time, other rpc calls
     *  may report that the imported key exists but related transactions are still missing,
     *  leading to temporarily incorrect/bogus balances and unspent outputs until rescan completes.<pre></pre>
     *
     * Note: Use “getwalletinfo” to query the scanning progress.<pre></pre>
     */
    public boolean importPrivKey(final String dumpPrivkey){
        final Object result = getJsonRpcClient().createRequest()
                .version(JSON_RPC_VERSION)
                .id(generateId())
                .method("importprivkey")
                .params(dumpPrivkey)
                .returnAs(Object.class).execute();

        return null == result ? true : false;
    }


    /**
     * Adds a public key (in hex) that can be watched as if it were in your wallet but cannot be used to spend.
     * Requires a new wallet backup.<pre></pre>
     *
     * Hint: use importmulti to import more than one public key.<pre></pre>
     *
     * Note: This call can take over an hour to complete if rescan is true, during that time, other rpc calls
     * may report that the imported pubkey exists but related transactions are still missing,
     * leading to temporarily incorrect/bogus balances and unspent outputs until rescan completes.<pre></pre>
     *
     * Note: Use “getwalletinfo” to query the scanning progress.<pre></pre>
     *
     * @param pubKey The hex-encoded public key
     */
    public boolean importPubKey(final String pubKey){
        final Object result = getJsonRpcClient().createRequest()
                .version(JSON_RPC_VERSION)
                .id(generateId())
                .method("importpubkey")
                .params(pubKey)
                .returnAs(Object.class).execute();

        return null == result ? true : false;
    }
    
    /**
     * Send an amount to a given address.<p>
     * Requires wallet passphrase to be set with walletpassphrase call if wallet is encrypted.
     * @return The transaction id.
     */
    public String sendToAddress(final String toAddress, final Double amount){
        return getJsonRpcClient().createRequest()
                .version(JSON_RPC_VERSION)
                .id(generateId())
                .method("sendtoaddress")
                .params(toAddress, amount)
                .returnAs(String.class).execute();
    }

    /**
     * @return Returns the total available balance.<pre></pre>
     *
     * The available balance is what the wallet considers currently spendable, and is thus affected by options which
     * limit spendability such as -spendzeroconfchange.
     *
     */
    public Double getBalance(){
        return getJsonRpcClient().createRequest()
                .version(JSON_RPC_VERSION)
                .id(generateId())
                .method("getbalance")
                .params()
                .returnAs(Double.class).execute();
    }

    /**
     * @return Returns up to ‘count’ most recent transactions skipping the first ‘from’ transactions.<p>
     * If a label name is provided, this will return only incoming transactions paying to addresses with the specified label.
     */
    public Collection<WalletTransaction> listTransactions(){
        return getJsonRpcClient().createRequest()
                .version(JSON_RPC_VERSION)
                .id(generateId())
                .method("listtransactions")
                .params()
                .returnAsList(WalletTransaction.class).execute();
    }

    /**
     * Sign a message with the private key of an address. <pre></pre>
     *
     * Requires wallet passphrase to be set with walletpassphrase call if wallet is encrypted. <pre></pre>
     *
     * @param address The Dogecoin address to use for the private key.
     * @param message The message to create a signature of.
     *
     * @return The signature of the message encoded in base 64
     */
    public String signMessage(final String address, final String message){
        return getJsonRpcClient().createRequest()
                .version(JSON_RPC_VERSION)
                .id(generateId())
                .method("signmessage")
                .params(address, message)
                .returnAs(String.class).execute();
    }

    /**
     * Removes the wallet encryption key from memory, locking the wallet.<pre></pre>
     *
     * After calling this method, you will need to call walletpassphrase again before being able to call any methods
     * which require the wallet to be unlocked.
     */
    public boolean walletLock(){
        return null == getJsonRpcClient().createRequest()
                .version(JSON_RPC_VERSION)
                .id(generateId())
                .method("walletlock")
                .params()
                .returnAs(Object.class).execute() ? true : false;
    }

    /**
     * Stores the wallet decryption key in memory for ‘timeout’ seconds.<pre></pre>
     *
     * This is needed prior to performing transactions related to private keys such as sending Dogecoins;<pre></pre>
     *
     * Note: Issuing the walletpassphrase command while the wallet is already unlocked
     * will set a new unlock time that overrides the old one.<pre></pre>
     *
     * @param passPhrase The wallet passphrase
     * @param timeout The time to keep the decryption key in seconds; capped at 100000000 (~3 years).
     */
    public boolean walletPassPhrase(final String passPhrase, final Long timeout){
        return null == getJsonRpcClient().createRequest()
                .version(JSON_RPC_VERSION)
                .id(generateId())
                .method("walletpassphrase")
                .params(passPhrase, timeout)
                .returnAs(Object.class).execute() ? true : false;
    }

    /**
     * Encrypts the wallet with ‘passphrase’. This is for first time encryption.
     * After this, any calls that interact with private keys such as sending or signing will require the passphrase
     * to be set prior the making these calls.<pre></pre>
     *
     * Use the walletpassphrase call for this, and then walletlock call.<pre></pre>
     *
     * If the wallet is already encrypted, use the walletpassphrasechange call.<pre></pre>
     *
     * @param passPhrase The pass phrase to encrypt the wallet with. It must be at least 1 character, but should be long.<pre></pre>
     * @return A string with further instructions
     */
    public String encryptWallet(final String passPhrase){
        return getJsonRpcClient().createRequest()
                .version(JSON_RPC_VERSION)
                .id(generateId())
                .method("encryptwallet")
                .params(passPhrase)
                .returnAs(String.class).execute();

    }

    /**
     *
     * @param minconf - The minimum confirmations to filter
     * @param maxconf - The maximum confirmations to filter
     * @param addresses - filter to only include txouts paid to specified addresses
     * @param includeUnsafe - Include outputs that are not safe to spend
     * @param queryOptions query options
     * @return array of unspent transaction outputs with between minconf and maxconf (inclusive) confirmations.
     *
     * Request body params example:  "params": [0, 9999999, [] , true, { "maximumAmount": 100 }]
     */
    public Collection<Unspent> listUnspent(final Long minconf, final Long maxconf, final String[] addresses,
                                           final boolean includeUnsafe, final QueryOptions queryOptions){
        return getJsonRpcClient().createRequest()
                .version(JSON_RPC_VERSION)
                .id(generateId())
                .method("listunspent")
                .params(null == minconf ? 0 : minconf,
                        null == maxconf ? 1 : maxconf,
                        null == addresses ? new String[]{} : addresses,
                        includeUnsafe,
                        null == queryOptions ? new QueryOptions() : queryOptions)
                .returnAsList(Unspent.class).execute();
    }

}
