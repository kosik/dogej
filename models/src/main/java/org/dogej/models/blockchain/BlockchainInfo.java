package org.dogej.models.blockchain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

public class BlockchainInfo {
    private String chain;
    private Long blocks;
    private Long headers;
    private String bestblockhash;
    private Double difficulty;
    private Long mediantime;
    private Double verificationprogress;
    private Boolean initialblockdownload;
    private String chainwork;
    private Long size_on_disk;
    private Boolean pruned;
    private Softfork[] softforks;
    private BIPSoftforks bip9_softforks;
    private String warnings;

    @JsonProperty public String getChain() {
        return chain;
    }

    public void setChain(String chain) {
        this.chain = chain;
    }

    @JsonProperty public Long getBlocks() {
        return blocks;
    }

    public void setBlocks(Long blocks) {
        this.blocks = blocks;
    }

    @JsonProperty public Long getHeaders() {
        return headers;
    }

    public void setHeaders(Long headers) {
        this.headers = headers;
    }

    @JsonProperty public String getBestblockhash() {
        return bestblockhash;
    }

    public void setBestblockhash(String bestblockhash) {
        this.bestblockhash = bestblockhash;
    }

    @JsonProperty public Double getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Double difficulty) {
        this.difficulty = difficulty;
    }

    @JsonProperty public Long getMediantime() {
        return mediantime;
    }

    public void setMediantime(Long mediantime) {
        this.mediantime = mediantime;
    }

    @JsonProperty public Double getVerificationprogress() {
        return verificationprogress;
    }

    public void setVerificationprogress(Double verificationprogress) {
        this.verificationprogress = verificationprogress;
    }

    @JsonProperty public Boolean getInitialblockdownload() {
        return initialblockdownload;
    }

    public void setInitialblockdownload(Boolean initialblockdownload) {
        this.initialblockdownload = initialblockdownload;
    }

    @JsonProperty public String getChainwork() {
        return chainwork;
    }

    public void setChainwork(String chainwork) {
        this.chainwork = chainwork;
    }

    @JsonProperty public Long getSize_on_disk() {
        return size_on_disk;
    }

    public void setSize_on_disk(Long size_on_disk) {
        this.size_on_disk = size_on_disk;
    }

    @JsonProperty public Boolean getPruned() {
        return pruned;
    }

    public void setPruned(Boolean pruned) {
        this.pruned = pruned;
    }

    @JsonProperty public Softfork[] getSoftforks() {
        return softforks;
    }

    public void setSoftforks(Softfork[] softforks) {
        this.softforks = softforks;
    }

    @JsonProperty public BIPSoftforks getBip9_softforks() {
        return bip9_softforks;
    }

    public void setBip9_softforks(BIPSoftforks bip9_softforks) {
        this.bip9_softforks = bip9_softforks;
    }

    @JsonProperty public String getWarnings() {
        return warnings;
    }

    public void setWarnings(String warnings) {
        this.warnings = warnings;
    }

    @Override
    public String toString() {
        return "BlockchainInfo{" +
                "chain='" + chain + '\'' +
                ", blocks=" + blocks +
                ", headers=" + headers +
                ", bestblockhash='" + bestblockhash + '\'' +
                ", difficulty=" + difficulty +
                ", mediantime=" + mediantime +
                ", verificationprogress=" + verificationprogress +
                ", initialblockdownload=" + initialblockdownload +
                ", chainwork='" + chainwork + '\'' +
                ", size_on_disk=" + size_on_disk +
                ", pruned=" + pruned +
                ", softforks=" + Arrays.toString(softforks) +
                ", bip9_softforks=" + bip9_softforks +
                ", warnings='" + warnings + '\'' +
                '}';
    }
}
