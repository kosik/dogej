package org.dogej.models.blockchain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChainTip {
    private Long height;
    private String hash;
    private Long branchlen;
    private String status;

    @JsonProperty public Long getHeight() {
        return height;
    }

    @JsonProperty public String getHash() {
        return hash;
    }

    @JsonProperty public Long getBranchlen() {
        return branchlen;
    }

    @JsonProperty public String getStatus() {
        return status;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public void setBranchlen(Long branchlen) {
        this.branchlen = branchlen;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ChainTip{" +
                "height=" + height +
                ", hash='" + hash + '\'' +
                ", branchlen=" + branchlen +
                ", status='" + status + '\'' +
                '}';
    }
}
