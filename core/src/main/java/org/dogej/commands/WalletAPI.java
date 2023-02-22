package org.dogej.commands;

import io.github.kosik.simplejsonrpc.client.JsonRpcClient;

import org.dogej.DomainException;
import org.dogej.ErrorCodes;
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
     * Find arbitrary unspent-amounts on the wallet, decrease required amount from the one or more unspents.
     * Send the required amount given toAddress. The remaining unspent minus commissions moveto a newly generated address
     * whiting the wallet.
     *
     * NB: Requires wallet passphrase to be set with walletPassphrase call if wallet is encrypted.
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
     * Sets the account associated with the given address.
     * Account is internal wallet title. Node Is using for set of internal operations like move. Internal operations
     * means it does not broadcast into blockchain.
     *
     * @param address The dogecoin address to be associated with an account.
     * @param accountTitle The account to assign the address to.
     * return Dogecoin node return null if the command execution is successful
     */
    @Deprecated public boolean setAccount(final String address, final String accountTitle){
        if(null == address || "".equals(address) ||
            null == accountTitle || "".equals(accountTitle))
            throw new DomainException(ErrorCodes.InvalidInput, "err-invalid-input");

        return null == getJsonRpcClient().createRequest()
                .version(JSON_RPC_VERSION)
                .id(generateId())
                .method("setaccount")
                .params(address, accountTitle)
                .returnAs(String.class).execute() ? true : false;
    }

    /**
     * Sent an amount from an account to a dogecoin address. Requires wallet passphrase to be set with walletpassphrase call.
     * @param fromAccount (string, required) The name of the account to send funds from. May be the default account using "".
     *                    Specifying an account does not influence coin selection, but it does associate the newly created
     *                    transaction with the account, so the account's balance computation and transaction history can reflect the spend.
     * @param toAddress (string, required) The dogecoin address to send funds to. setAccount
     * @param amount (numeric or string, required) The amount in DOGE (transaction fee is added on top).
     * @param minconf (numeric, optional, default=1) Only use funds with at least this many confirmations.
     * @param comment (string, optional) A comment used to store what the transaction is for. This is not part of the transaction, just kept in your wallet.
     * @param comment_to (string, optional) An optional comment to store the name of the person or organization to which
     *                   you're sending the transaction. This is not part of the transaction, it is just kept in your wallet.
     * @return "txid" (string) The transaction id.
     */
    @Deprecated public String sendFrom(final String fromAccount, final String toAddress, final Double amount,
                                       final Integer minconf, final String comment, final String comment_to){

        if(null == fromAccount || "".equals(fromAccount) || null == toAddress || "".equals(toAddress) ||
            null == amount || 0 > amount)
            throw new DomainException(ErrorCodes.InvalidInput, "err-invalid-input");

        return getJsonRpcClient().createRequest()
                .version(JSON_RPC_VERSION)
                .id(generateId())
                .method("sendfrom")
                .params(fromAccount, toAddress, amount,
                        null == minconf ? 1 : minconf,
                        null == comment ? "" : comment,
                        null == comment_to ? "" : comment_to)
                .returnAs(String.class).execute();
    }

    /**
     * Move a specified amount from one account in your wallet to another.
     * The operation does not published result into blockchain, nevertheless it visible whiting the wallet {@code getBalance} operation.
     *
     * NB: the command move fromAccount toAccount more than available, so digit on fromAccount could become negative.
     * I would rather avoid such behaviour, so has added extra logic.
     *
     * @see {@code setAccount} method
     */
    public String move(final String fromAccount, final String toAccount, final Double amount){
        return getJsonRpcClient().createRequest()
                .version(JSON_RPC_VERSION)
                .id(generateId())
                .method("move")
                .params(fromAccount, toAccount, amount)
                .returnAs(String.class).execute();
    }

    /**
     * @return Returns the total available balance.<pre></pre>
     *
     * The available balance is what the wallet considers currently spendable, and is thus affected by options which
     * limit spendability such as -spendzeroconfchange.
     *
     * If account is not specified, returns the server's total available balance. If account is specified (DEPRECATED),
     * returns the balance in the account. Note that the account "" is not the same as leaving the parameter out.
     * The server total may be different to the balance in the default "" account.
     *
     * @param account (string, optional) DEPRECATED. The account string may be given as a specific account name to find
     *                the balance associated with wallet keys in a named account, or as
     *        the empty string ("") to find the balance associated with wallet keys not in any named account, or as
     *        "*" to find the balance associated with all wallet keys regardless of account. When this option is specified,
     *                it calculates the balance in a different way than when it is not specified, and which can count spends twice when
     *                there are conflicting pending transactions (such as those created by the bumpfee command),
     *                temporarily resulting in low or even negative balances. In general, account balance calculation is
     *                not considered reliable and has resulted in confusing outcomes, so it is recommended to avoid
     *                passing this argument.
     *
     * @param minconf (numeric, optional, default=1) Only include transactions confirmed at least this many times.
     * @param includeWatchonly (bool, optional, default=false) Also include balance in watch-only addresses (see 'importaddress')
     */
    public Double getBalance(final String account, final Integer minconf, final boolean includeWatchonly){
        return getJsonRpcClient().createRequest()
                .version(JSON_RPC_VERSION)
                .id(generateId())
                .method("getbalance")
                .params( null == account ? "" : account,
                        null == minconf ? MIN_CONF : minconf,
                        includeWatchonly)
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
                        null == maxconf ? 9999999 : maxconf,
                        null == addresses ? new String[]{} : addresses,
                        includeUnsafe,
                        null == queryOptions ? new QueryOptions() : queryOptions)
                .returnAsList(Unspent.class).execute();
    }


    /**
     * Adds an address or script (in hex) that can be watched as if it were in your wallet but cannot be used to spend.
     * Requires a new wallet backup.
     *
     * Note: This call can take over an hour to complete if rescan is true, during that time, other rpc calls may report
     * that the imported address exists but related transactions are still missing, leading to temporarily incorrect/bogus
     * balances and unspent outputs until rescan completes.
     *
     * NB: If you have the full public key, you should call importpubkey instead of this.
     * NB: importmulti to import more than one address.
     * NB: If you import a non-standard raw script in hex form, outputs sending to it will be treated as change,
     *      and not show up in many RPCs.
     * NB: Use “getwalletinfo” to query the scanning progress.
     *
     * @param label An optional label, default=””
     * @param rescan Rescan the wallet for transactions, default = true
     * @param p2sh Add the P2SH version of the script as well, default = false
     *
     * @return NB: positive result is JSON-RPC null object
     */
    public void importAddress(final String address, final String label, final boolean rescan, final boolean p2sh){
        getJsonRpcClient().createRequest()
                .version(JSON_RPC_VERSION)
                .id(generateId())
                .method("importaddress")
                .params(address,
                        null == label ? "" : label,
                        rescan, p2sh)
                .returnAs(String.class).execute();
    }


}
