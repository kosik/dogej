package org.dogej.service;

import org.dogej.DogecoinNodeClient;
import org.dogej.DomainException;
import org.dogej.ErrorCodes;
import org.dogej.models.rawtx.TransactionInput;
import org.dogej.models.util.ValidateAddress;
import org.dogej.models.wallet.Unspent;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MonetaryOperations {
    private DogecoinNodeClient nodeClient;
    final private double MINER_FEE_AMOUNT = 0.0035d;

    public MonetaryOperations(final DogecoinNodeClient dogecoinNodeClient) {
        //TODO validate
        this.nodeClient = dogecoinNodeClient;
    }

    private DogecoinNodeClient getNodeClient() {
        return nodeClient;
    }

    /**
     *
     * The method validates input parameters. Get unspent-amounts list from @param fromAddress. Build rawTxInput and
     * sends the required volume to the recipient's address, the entire remaining volume minus
     * MINER_FEE_AMOUNT is pass back to the sender's address.
     *
     * NB-1: The method requires explicit walletPassPhrase execution.
     * NB-2: The method does minimal validation so could throw DomainException.
     * NB-3: The method could throw JSON-RPC [code=-26, message=66: insufficient priority] if network demands higher commission.
     * TODO calculate MINER_FEE_AMOUNT in accordance with network difficulty
     *
     * @see org.dogej.commands.WalletAPI.sendToAddress() the major differences, this method returns unspent to fromAddress.
     *
     * @param remainAddress adress where remain will be sent. If it is null, the remain will be send to address from Unspent
     * @return transaction id
     */
    public String send(final String fromAddress, final String toAddress, final Double amount, final String remainAddress){

        if(0 > amount)
            throw new DomainException(ErrorCodes.InvalidInput, "err-positive-amount");

        final ValidateAddress validateAddress = getNodeClient().getUtilAPI().getValidateAddress(toAddress);

        if(!validateAddress.isValid())
            throw new DomainException(ErrorCodes.InvalidInput, "err-invalid-input");

        final Collection<Unspent> unspentList = getNodeClient().getWalletAPI()
                .listUnspent(1L, null, new String[]{fromAddress}, true, null);

        if(null == unspentList || unspentList.isEmpty())
            throw new DomainException(ErrorCodes.NotFound, "err-not-found");

        final TransactionInput[] rawTxInput = new TransactionInput[unspentList.size()];

        int i = 0;
        Double unspentsAmount = 0D;
        for (final Unspent unspentItem : unspentList){
            rawTxInput[i++] = new TransactionInput(unspentItem.getTxid(), unspentItem.getVout());
            unspentsAmount = unspentsAmount + unspentItem.getAmount();

        }//

        if(unspentsAmount < amount)
            throw new DomainException(ErrorCodes.InsufficentAmount, "err-insufficient-amount");

        final Map<String, Double> destination = new HashMap<>();

        destination.put(toAddress, BigDecimal.valueOf(amount).setScale(7, RoundingMode.HALF_UP).doubleValue());

        unspentsAmount = BigDecimal.valueOf((unspentsAmount - amount) - MINER_FEE_AMOUNT)
                .setScale(7, RoundingMode.HALF_UP)
                .doubleValue();

        destination.put(null == remainAddress ?
                unspentList.stream().findFirst().get().getAddress() : remainAddress, unspentsAmount);

        final String transactionHEX = getNodeClient().getRawTransaction().createRawTransaction(rawTxInput, destination);

        final String signedTxHex = getNodeClient().getRawTransaction().signRawTransaction(transactionHEX);

        return getNodeClient().getRawTransaction().sendRawTransaction(signedTxHex);
    }

    public Double calculateUnspents(final String address){
        final Collection<Unspent> unspentList = getNodeClient().getWalletAPI()
                .listUnspent(1L, null, new String[]{address}, true, null);

        final Double totalAmount = unspentList.stream().mapToDouble(u -> u.getAmount()).sum();

        return totalAmount;
    }

}
