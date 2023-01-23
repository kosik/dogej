package org.dogej.service;

import org.dogej.DogecoinNodeClient;
import org.dogej.DomainException;
import org.dogej.ErrorCodes;
import org.dogej.models.rawtx.TransactionInput;
import org.dogej.models.wallet.Unspent;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MonetaryOperations {
    private DogecoinNodeClient nodeClient;
    final private double MINER_FEE_AMOUNT = 0.0025d;

    public MonetaryOperations(final DogecoinNodeClient dogecoinNodeClient) {
        //TODO validate
        this.nodeClient = dogecoinNodeClient;
    }

    private DogecoinNodeClient getNodeClient() {
        return nodeClient;
    }

    /**
     *
     * The method get unspent-amounts list of the @param fromAddress, bring the first one and
     * sends the required volume to the recipient's address, the entire remaining volume minus
     * MINER_FEE_AMOUNT is returned to the sender's address.
     *
     * NB-1: The method requires explicit walletPassPhrase execution.
     * NB-2: The method does minimal validation so could throw DomainException.
     * NB-3: The method could throw JSON-RPC [code=-26, message=66: insufficient priority] if network demands higher commission.
     *
     * @see org.dogej.commands.WalletAPI.sendToAddress() the major differences, this method returns unspent to fromAddress.
     *
     * @return transaction id
     */
    public String send(final String fromAddress, final String toAddress, final Double amount){

        if(0 > amount)
            throw new DomainException(ErrorCodes.InvalidInput, "err-positive-amount");

        //TODO validate address

        final Collection<Unspent> unspentList = getNodeClient().getWalletAPI()
                .listUnspent(1L, null, new String[]{fromAddress}, true, null);

        final Unspent unspent = unspentList.stream().findFirst().get();

        if(null == unspent)
            throw new DomainException(ErrorCodes.NotFound, "err-not-found");

        if(unspent.getAmount() < amount)
            throw new DomainException(ErrorCodes.InsufficentAmount, "err-insufficient-amount");

        final Map<String, Double> destination = new HashMap<>();
        destination.put(toAddress, amount);

        final Double unspentAmount = BigDecimal.valueOf((unspent.getAmount() - amount) - MINER_FEE_AMOUNT)
                .setScale(7, RoundingMode.HALF_UP)
                .doubleValue();

        destination.put(unspent.getAddress(), unspentAmount);

        final String transactionHEX = getNodeClient().getRawTransaction().createRawTransaction(
                new TransactionInput[]{new TransactionInput(unspent.getTxid(), 1L)}, destination);

        final String signedTxHex = getNodeClient().getRawTransaction().signRawTransaction(transactionHEX);

        return getNodeClient().getRawTransaction().sendRawTransaction(signedTxHex);
    }

}
