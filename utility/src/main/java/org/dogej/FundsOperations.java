package org.dogej;

import io.github.kosik.simplejsonrpc.client.exception.JsonRpcException;
import org.dogej.models.wallet.QueryOptions;
import org.dogej.models.wallet.Unspent;

import java.util.Collection;

public class FundsOperations {

    public static void main(final String[] args) {
        final String dogeHost = System.getenv("DOGE_HOST");
        final String userName = System.getenv("DOGE_USER");
        final String pw = System.getenv("DOGE_PW");

        final DogecoinNodeClient dogecoinClient = new DogecoinNodeClient(dogeHost, userName, pw);

        new FundsOperations().seeUnspent(dogecoinClient, "xxx");

    }

    public void getBalance(final DogecoinNodeClient dogecoinClient) throws JsonRpcException, Exception {
        final Double balance = dogecoinClient.getWalletAPI().getBalance(null, null, false);

        System.out.println("\n Wallet Total Balance: "
                + String.format("%,.5f", balance) + "\n" );

    }

    public void seeTransactions(final DogecoinNodeClient dogecoinClient){
        System.out.println("\n" + "List the wallet transactions:\n");
        dogecoinClient.getWalletAPI().listTransactions().stream().forEach(t -> System.out.println(t.toString()));
    }

    public void seeUnspent(final DogecoinNodeClient dogecoinClient, final String address){
        final QueryOptions queryOptions = new QueryOptions();
        queryOptions.setMaximumAmount(200D);
        final Collection<Unspent> unspentList = dogecoinClient.getWalletAPI()
                .listUnspent(1L, null, new String[]{address}, true, null);

        System.out.println("list unspent");
        System.out.println(unspentList.toString());
    }

    public void move(final DogecoinNodeClient dogecoinClient, final String fromAddress, final String recipientAddress){
        dogecoinClient.getWalletAPI().setAccount(fromAddress, fromAddress);
        dogecoinClient.getWalletAPI().setAccount(recipientAddress, recipientAddress);

        System.out.println("balance " + dogecoinClient.getWalletAPI().getBalance("*", null, false));

        System.out.println("fromAddress balance " + dogecoinClient.getWalletAPI().getBalance(fromAddress, null, false));
        System.out.println("recipientAddress balance " + dogecoinClient.getWalletAPI().getBalance(recipientAddress, null, false));

        dogecoinClient.getWalletAPI().move(fromAddress, recipientAddress, 0.01D);

        System.out.println("Move result: ");
        System.out.println("fromAddress balance " + dogecoinClient.getWalletAPI().getBalance(fromAddress, null, false));
        System.out.println("recipientAddress balance " + dogecoinClient.getWalletAPI().getBalance(recipientAddress, null, false));
    }//move


    public void sendMoney(final DogecoinNodeClient dogecoinClient) throws JsonRpcException, Exception {

        final Double balance = dogecoinClient.getWalletAPI().getBalance(null, null, false);

        System.out.println("\n Wallet Total Balance: "
                + String.format("%,.5f", balance) +"\n");

        final String[] addresses = dogecoinClient.getWalletAPI().getAddressesByAccount();

        dogecoinClient.getWalletAPI().walletPassPhrase(System.getenv("WALLET-PASS-PHRASE"), 1L);

        System.out.println("Money has being sent. The transaction id: "
                + dogecoinClient.getWalletAPI().sendToAddress(addresses[0], 0.01D));

        System.out.println("\n" + "List the wallet transactions:\n");
        dogecoinClient.getWalletAPI().listTransactions().stream().forEach(t -> System.out.println(t.toString()));
    }//

    public void rawTransaction(final DogecoinNodeClient dogecoinClient, final String fromAddress, final String toAddress){

        dogecoinClient.getWalletAPI().walletPassPhrase(System.getenv("WALLET-PASS-PHRASE"), 1L);

        System.out.println(
            new org.dogej.service.MonetaryOperations(dogecoinClient).send(fromAddress, toAddress, 0.01D)
        );

    }//raw-tx

}
