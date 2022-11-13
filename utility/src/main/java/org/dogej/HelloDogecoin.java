package org.dogej;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.kosik.simplejsonrpc.client.exception.JsonRpcException;
import io.github.kosik.simplejsonrpc.core.domain.ErrorMessage;
import org.dogej.models.blockchain.BlockHeader;
import org.dogej.models.blockchain.BlockchainInfo;

import java.util.Arrays;
import java.util.UUID;

public class HelloDogecoin {
    final private String WALLET_PASS_PHRASE = "secret";
    private ObjectMapper objectMapper = new ObjectMapper();

    public static void main(final String[] args) {

        final DogecoinNodeClient dogecoinClient
                = new DogecoinNodeClient("http://127.0.0.1:22555", "myusername", "secret");

        try {
        new HelloDogecoin().blockchainAPIExample(dogecoinClient);

        new HelloDogecoin().createWallet(dogecoinClient);

        new HelloDogecoin().walletMaintenance(dogecoinClient);

        new HelloDogecoin().sendMoney(dogecoinClient);

        System.out.println("\n\nSuccess. Welcome to Dogecoin blockchain network!");


        } catch (final JsonRpcException jre) {
            final ErrorMessage errorMessage = jre.getErrorMessage();
            System.out.println(errorMessage.code() +" : " + errorMessage.getMessage());
        } catch (final Exception e){
            System.out.println("***** Handler for any other exceptions");
        }
    }


    public void blockchainAPIExample(final DogecoinNodeClient dogecoinClient) throws Exception {
        final String firstHash = dogecoinClient.getBlockchainAPI().getBlockHash(0L);
        System.out.println("\nHash #1: " + firstHash +"\n");

        System.out.println("Blocks count: " + dogecoinClient.getBlockchainAPI().getBlockCount() +"\n");

        System.out.println("Best blockHash: " + dogecoinClient.getBlockchainAPI().getBestBlockHash() +"\n");

        final Double difficulty = dogecoinClient.getBlockchainAPI().getDifficulty();
        System.out.println("Difficulty: " + difficulty +"\n");

        System.out.println("Chain-tips: " + Arrays.toString(dogecoinClient.getBlockchainAPI().getChainTips()) +"\n");

        final BlockchainInfo blockchainInfo = dogecoinClient.getBlockchainAPI().getBlockchainInfo();
        System.out.println("Blockchain information:\n "+ objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(blockchainInfo) +"\n");

        final BlockHeader blockHeader = dogecoinClient.getBlockchainAPI().getBlockHeader(firstHash);
        System.out.println("Block-header for the first block hash:\n "
                + objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(blockHeader) +"\n");
    }//

    public void createWallet(final DogecoinNodeClient dogecoinClient) throws Exception {
        System.out.println("Wallet info: \n" +
                objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(
                    dogecoinClient.getWalletAPI().getWalletInfo()
            ));

        System.out.println("\nNew Dogecoin address has being created: " + dogecoinClient.getWalletAPI().getNewAddress());

        System.out.println("\nSee addresses by account: \n" + Arrays.toString(
                dogecoinClient.getWalletAPI().getAddressesByAccount()));

    }//

    public void walletMaintenance(final DogecoinNodeClient dogecoinClient){
        System.out.println("\nBackup-file Result: "
                + dogecoinClient.getWalletAPI().backupWallet(UUID.randomUUID().toString()+".dat"));

        final String[] addresses = dogecoinClient.getWalletAPI().getAddressesByAccount();

        dogecoinClient.getWalletAPI().walletPassPhrase(WALLET_PASS_PHRASE, 5L);

        System.out.println("\nDump private keys for wallet addresses:");
        Arrays.stream(addresses).iterator()
                .forEachRemaining(w -> System.out.println("PK for " + w + ": "
                        + dogecoinClient.getWalletAPI().dumpPrivKey(w)));

        System.out.println("\n Example signing message with Dogecoin address private key: \n");
        Arrays.stream(addresses).iterator()
                .forEachRemaining(a -> {
                    final String sign = dogecoinClient.getWalletAPI().signMessage(a, "Your keys - your money!");
                    System.out.println("Address " + a + " : " + sign);
                });
    }//

    public void sendMoney(final DogecoinNodeClient dogecoinClient) throws JsonRpcException, Exception {

        final Double balance = dogecoinClient.getWalletAPI().getBalance();

        System.out.println("\n Wallet Total Balance: "
                + String.format("%,.5f", balance) +"\n");

        final String[] addresses = dogecoinClient.getWalletAPI().getAddressesByAccount();

        dogecoinClient.getWalletAPI().walletPassPhrase(WALLET_PASS_PHRASE, 5L);

        System.out.println("Money has being sent. The transaction id: "
                + dogecoinClient.getWalletAPI().sendToAddress(addresses[0], 200D));

        System.out.println("\n" + "List the wallet transactions:\n");
        dogecoinClient.getWalletAPI().listTransactions().stream().forEach(t -> System.out.println(t.toString()));
    }//

    /**
     * Single time operation
     */
    public void walletSecurity(final DogecoinNodeClient dogecoinClient) {
        System.out.println(dogecoinClient.getWalletAPI().encryptWallet(WALLET_PASS_PHRASE));
    }//

}
