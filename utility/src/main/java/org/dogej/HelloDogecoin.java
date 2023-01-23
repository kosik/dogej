package org.dogej;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.kosik.simplejsonrpc.client.exception.JsonRpcException;
import io.github.kosik.simplejsonrpc.core.domain.ErrorMessage;
import org.dogej.models.blockchain.BlockHeader;
import org.dogej.models.blockchain.BlockchainInfo;

import java.util.Arrays;

public class HelloDogecoin {

    private ObjectMapper objectMapper = new ObjectMapper();

    public static void main(final String[] args) {
        final String dogeHost = System.getenv("DOGE_HOST");
        final String userName = System.getenv("DOGE_USER");
        final String pw = System.getenv("DOGE_PW");

        final DogecoinNodeClient dogecoinClient = new DogecoinNodeClient(dogeHost, userName, pw);

        try {

        new HelloDogecoin().blockchainInfo(dogecoinClient);

        System.out.println("\n\nSuccess. Welcome to Dogecoin blockchain network!");

        } catch (final JsonRpcException jre) {
            final ErrorMessage errorMessage = jre.getErrorMessage();
            System.out.println(errorMessage.code() +" : " + errorMessage.getMessage());
        } catch (final Exception e){
            System.out.println("***** Handler for any other exceptions");
        }
    }


    public void blockchainInfo(final DogecoinNodeClient dogecoinClient) throws Exception {
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

}
