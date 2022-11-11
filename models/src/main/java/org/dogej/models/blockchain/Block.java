package org.dogej.models.blockchain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

public class Block extends BlockHeader{
    private String[] tx;
    private Auxpow auxpow;

    private Long size;
    private Long strippedsize;

    @JsonProperty public String[] getTx() {
        return tx;
    }

    @JsonProperty public Auxpow getAuxpow() {
        return auxpow;
    }

    @JsonProperty public Long getSize() {
        return size;
    }

    @JsonProperty public Long getStrippedsize() {
        return strippedsize;
    }

    public void setSize(final Long size) {
        this.size = size;
    }

    public void setStrippedsize(final Long strippedsize) {
        this.strippedsize = strippedsize;
    }

    public void setTx(final String[] tx) {
        this.tx = tx;
    }

    public void setAuxpow(Auxpow auxpow) {
        this.auxpow = auxpow;
    }

    @Override
    public String toString() {
        return "Block{" +
                "tx=" + Arrays.toString(tx) +
                ", auxpow=" + auxpow +
                ", size=" + size +
                ", strippedsize=" + strippedsize +
                '}';
    }
}