package org.dogej;

import org.dogej.models.wallet.QueryOptions;
import org.dogej.models.wallet.Unspent;

import java.util.Collection;

public class UnspentOperations {

    public static void main(final String[] args) {
        final String dogeHost = System.getenv("DOGE_HOST");
        final String userName = System.getenv("DOGE_USER");
        final String pw = System.getenv("DOGE_PW");

        final DogecoinNodeClient dogecoinClient = new DogecoinNodeClient(dogeHost, userName, pw);

        final QueryOptions queryOptions = new QueryOptions();
        queryOptions.setMaximumAmount(200D);
        final Collection<Unspent> unspentList = dogecoinClient.getWalletAPI()
                .listUnspent(1L,2000L, null, true, queryOptions);

        System.out.println(unspentList.toString());

        System.out.println("\n" + "List the wallet transactions:\n");
        dogecoinClient.getWalletAPI().listTransactions().stream().forEach(t -> System.out.println(t.toString()));
    }
}
