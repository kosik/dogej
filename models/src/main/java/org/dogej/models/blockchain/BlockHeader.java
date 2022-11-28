package org.dogej.models.blockchain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BlockHeader {
    private String hash;
    private Integer confirmations;
    private Long weight;
    private Long height;
    private Long version;
    private String versionHex;
    private String merkleroot;
    private Long time;
    private Long mediantime;
    private Long nonce;
    private String bits;
    private Long difficulty;
    private String chainwork;
    private String previousblockhash;
    private String nextblockhash;

    @JsonProperty public String getHash() {
        return hash;
    }

    @JsonProperty public Integer getConfirmations() {
        return confirmations;
    }

    @JsonProperty public Long getWeight() {
        return weight;
    }

    @JsonProperty public Long getHeight() {
        return height;
    }

    @JsonProperty public Long getVersion() {
        return version;
    }

    @JsonProperty public String getVersionHex() {
        return versionHex;
    }

    @JsonProperty public String getMerkleroot() {
        return merkleroot;
    }

    @JsonProperty public Long getTime() {
        return time;
    }

    @JsonProperty public Long getMediantime() {
        return mediantime;
    }

    @JsonProperty public Long getNonce() {
        return nonce;
    }

    @JsonProperty public String getBits() {
        return bits;
    }

    @JsonProperty public Long getDifficulty() {
        return difficulty;
    }

    @JsonProperty public String getChainwork() {
        return chainwork;
    }

    @JsonProperty public String getPreviousblockhash() {
        return previousblockhash;
    }

    @JsonProperty public String getNextblockhash() {
        return nextblockhash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public void setConfirmations(Integer confirmations) {
        this.confirmations = confirmations;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public void setVersionHex(String versionHex) {
        this.versionHex = versionHex;
    }

    public void setMerkleroot(String merkleroot) {
        this.merkleroot = merkleroot;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public void setMediantime(Long mediantime) {
        this.mediantime = mediantime;
    }

    public void setNonce(Long nonce) {
        this.nonce = nonce;
    }

    public void setBits(String bits) {
        this.bits = bits;
    }

    public void setDifficulty(Long difficulty) {
        this.difficulty = difficulty;
    }

    public void setChainwork(String chainwork) {
        this.chainwork = chainwork;
    }

    public void setPreviousblockhash(String previousblockhash) {
        this.previousblockhash = previousblockhash;
    }

    public void setNextblockhash(String nextblockhash) {
        this.nextblockhash = nextblockhash;
    }

    @Override
    public String toString() {
        return "BlockHeader{" +
                "hash='" + hash + '\'' +
                ", confirmations=" + confirmations +
                ", weight=" + weight +
                ", height=" + height +
                ", version=" + version +
                ", versionHex='" + versionHex + '\'' +
                ", merkleroot='" + merkleroot + '\'' +
                ", time=" + time +
                ", mediantime=" + mediantime +
                ", nonce=" + nonce +
                ", bits='" + bits + '\'' +
                ", difficulty=" + difficulty +
                ", chainwork='" + chainwork + '\'' +
                ", previousblockhash='" + previousblockhash + '\'' +
                ", nextblockhash='" + nextblockhash + '\'' +
                '}';
    }
}
