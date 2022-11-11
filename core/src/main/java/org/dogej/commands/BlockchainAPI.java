package org.dogej.commands;

import io.github.kosik.simplejsonrpc.client.JsonRpcClient;
import org.dogej.models.blockchain.Block;
import org.dogej.models.blockchain.BlockHeader;
import org.dogej.models.blockchain.BlockchainInfo;
import org.dogej.models.blockchain.ChainTip;

/**
 * @author s.kosik
 * @since November 2022
 *
 */
public class BlockchainAPI extends Operation {

    public BlockchainAPI(final JsonRpcClient jsonRpcClient) {
        super(jsonRpcClient);
    }

    /**
     * getbestblockhash - Returns the hash of the best (tip) block in the longest blockchain.
     *
     * @return "hex" (string) the block hash hex encoded
     */
    public String getBestBlockHash() {
        return getJsonRpcClient().createRequest()
                .version(JSON_RPC_VERSION).id(generateId())
                .method("getbestblockhash")
                .params()
                .returnAs(String.class).execute();
    }

    /**
     * @param blockHash hash identifier
     * @param verbosity numeric, optional, default=1<p>
     *        If verbosity is 0, returns a string that is serialized, hex-encoded data for block ‘hash’.<p>
     *        If verbosity is 1, returns an Object with information about block ‘hash’.<p>
     *        If verbosity is 2, returns an Object with information about block ‘hash’ and information about each transaction.<p>
     *
     * @return detailed info about block<p>
     */
    public Block getBlock(final String blockHash) {
        return getJsonRpcClient().createRequest()
                .version(JSON_RPC_VERSION).id(generateId())
                .method("getblock")
                .params(blockHash)
                .returnAs(Block.class).execute();
    }

    /**
     * @param blockHash
     * @return info about block
     */
    public BlockHeader getBlockHeader(final String blockHash){//NB seems duplicates getBlock
        return getJsonRpcClient().createRequest()
                .version(JSON_RPC_VERSION).id(generateId())
                .method("getblockheader")
                .params(blockHash)
                .returnAs(BlockHeader.class).execute();
    }

    /**
     * getblockchaininfo - Returns an object containing various state info regarding blockchain processing.
     */
    public BlockchainInfo getBlockchainInfo() {
        return getJsonRpcClient().createRequest()
                .version(JSON_RPC_VERSION).id(generateId())
                .method("getblockchaininfo")
                .params()
                .returnAs(BlockchainInfo.class).execute();
    }

    /**
     * @return Returns the height of the most-work fully-validated chain.
     */
    public Long getBlockCount(){
        return getJsonRpcClient().createRequest()
                .version(JSON_RPC_VERSION).id(generateId())
                .method("getblockcount")
                .params()
                .returnAs(Long.class).execute();
    }

    /**
     * @param height The height index
     * @return Returns hash of block in best-block-chain at height provided.
     */
    public String getBlockHash(final Long height){
        return getJsonRpcClient().createRequest()
                .version(JSON_RPC_VERSION).id(generateId())
                .method("getblockhash")
                .params(height)
                .returnAs(String.class).execute();
    }

    /**
     * @return information about all known tips in the block tree, including the main chain as well as orphaned branches
     * <pre>Response object contains:
     * height :  height of the chain tip
     * hash : block hash of the tip
     * branchlen : zero for main chain, otherwise length of branch connecting the tip to the main chain
     *
     * status :  status of the chain, "active" for the main chain. Possible values for status:
     *  1.  "invalid"               This branch contains at least one invalid block
     *  2.  "headers-only"          Not all blocks for this branch are available, but the headers are valid
     *  3.  "valid-headers"         All blocks are available for this branch, but they were never fully validated
     *  4.  "valid-fork"            This branch is not part of the active chain, but is fully validated
     *  5.  "active"                This is the tip of the active main chain, which is certainly valid
     * </pre>
     *
     */
    public ChainTip[] getChainTips(){
        return getJsonRpcClient().createRequest()
                .version(JSON_RPC_VERSION).id(generateId())
                .method("getchaintips")
                .params()
                .returnAsArray(ChainTip.class).execute();
    }

    /**
     * @return the proof-of-work difficulty as a multiple of the minimum difficulty.
     */
    public Double getDifficulty(){
        return getJsonRpcClient().createRequest()
                .version(JSON_RPC_VERSION).id(generateId())
                .method("getdifficulty")
                .params()
                .returnAs(Double.class).execute();
    }

}
