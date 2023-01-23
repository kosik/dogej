package org.dogej;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.dogej.models.util.ValidateAddress;

import java.util.Arrays;
import java.util.UUID;

public class WalletMaintenance {
    private ObjectMapper objectMapper = new ObjectMapper();

    public static void main(final String[] args) {
        final String dogeHost = System.getenv("DOGE_HOST" );
        final String userName = System.getenv("DOGE_USER" );
        final String pw = System.getenv("DOGE_PW" );

        final DogecoinNodeClient dogecoinClient = new DogecoinNodeClient(dogeHost, userName, pw);

        try {
            new WalletMaintenance().createWallet(dogecoinClient);

        } catch (final Exception e) {
            throw new RuntimeException(e);
        }

    }//main

    public void validateAddress(final DogecoinNodeClient dogecoinClient, final String address) throws Exception {

        final ValidateAddress validationInfo = dogecoinClient.getUtilAPI().getValidateAddress(address);

        System.out.println("Wallet info: \n" +
                objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(
                        validationInfo
                ));
    }

    public void createWallet(final DogecoinNodeClient dogecoinClient) throws Exception {

        System.out.println("\nNew Dogecoin address has being created: " + dogecoinClient.getWalletAPI().getNewAddress());

        System.out.println("\nSee addresses by account: \n" + Arrays.toString(
                dogecoinClient.getWalletAPI().getAddressesByAccount()));

    }//

    public void backupWallet(final DogecoinNodeClient dogecoinClient) {
        System.out.println("\nBackup-file Result: "
                + dogecoinClient.getWalletAPI().backupWallet("imported-00000-" + UUID.randomUUID().toString() + ".dat" ));

        final String[] addresses = dogecoinClient.getWalletAPI().getAddressesByAccount();

        //dogecoinClient.getWalletAPI().walletPassPhrase(System.getenv("WALLET_SECRET"), 1L);

        Arrays.stream(addresses).iterator().forEachRemaining(w -> System.out.println(w));

    }

    public void dumpAndSign(final DogecoinNodeClient dogecoinClient) {
        dogecoinClient.getWalletAPI().walletPassPhrase(System.getenv("WALLET-PASS-PHRASE" ), 1L);

        final String[] addresses = dogecoinClient.getWalletAPI().getAddressesByAccount();

        System.out.println("\nDump private keys for wallet addresses:" );
        Arrays.stream(addresses).iterator()
                .forEachRemaining(w -> System.out.println("PK for " + w + ": "
                        + dogecoinClient.getWalletAPI().dumpPrivKey(w)));

        System.out.println("\n Example signing message with Dogecoin address private key: \n" );
        Arrays.stream(addresses).iterator()
                .forEachRemaining(a -> {
                    final String sign = dogecoinClient.getWalletAPI().signMessage(a, "Your keys - your money!" );
                    System.out.println("Address " + a + " : " + sign);
                });
    }//

    public void importAddress(final DogecoinNodeClient dogecoinNodeClient, final String address) {
        dogecoinNodeClient.getWalletAPI().importAddress(address, null, false, false);
    }

    /**
     * Single time operation
     */
    public void walletSecurity(final DogecoinNodeClient dogecoinClient) {
        final String WALLET_PASS_PHRASE = System.getenv("WALLET-PASS-PHRASE");

        System.out.println(
                dogecoinClient.getWalletAPI().encryptWallet(WALLET_PASS_PHRASE)
        );
    }//

}
