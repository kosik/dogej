package org.dogej.models.blockchain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

public class Auxpow {
    private Transaction tx;
    private Long index;
    private Long chainindex;
    private String[] merklebranch;
    private String[] chainmerklebranch;
    private String parentblock;

    @JsonProperty public Transaction getTx() {
        return tx;
    }

    @JsonProperty public Long getIndex() {
        return index;
    }

    @JsonProperty public Long getChainindex() {
        return chainindex;
    }

    @JsonProperty public String[] getMerklebranch() {
        return merklebranch;
    }

    @JsonProperty public String[] getChainmerklebranch() {
        return chainmerklebranch;
    }

    @JsonProperty public String getParentblock() {
        return parentblock;
    }

    public void setTx(Transaction tx) {
        this.tx = tx;
    }

    public void setIndex(Long index) {
        this.index = index;
    }

    public void setChainindex(Long chainindex) {
        this.chainindex = chainindex;
    }

    public void setMerklebranch(String[] merklebranch) {
        this.merklebranch = merklebranch;
    }

    public void setChainmerklebranch(String[] chainmerklebranch) {
        this.chainmerklebranch = chainmerklebranch;
    }

    public void setParentblock(String parentblock) {
        this.parentblock = parentblock;
    }

    @Override
    public String toString() {
        return "Auxpow{" +
                "tx=" + tx +
                ", index=" + index +
                ", chainindex=" + chainindex +
                ", merklebranch=" + Arrays.toString(merklebranch) +
                ", chainmerklebranch=" + Arrays.toString(chainmerklebranch) +
                ", parentblock='" + parentblock + '\'' +
                '}';
    }
}
