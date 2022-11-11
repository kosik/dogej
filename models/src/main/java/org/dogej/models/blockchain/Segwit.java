package org.dogej.models.blockchain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Segwit {
    private String status;
    private Long bit;
    private Long startTime;
    private Long timeout;
    private Long since;

    @JsonProperty public String getStatus() {
        return status;
    }

    @JsonProperty public Long getBit() {
        return bit;
    }

    @JsonProperty public Long getStartTime() {
        return startTime;
    }

    @JsonProperty public Long getTimeout() {
        return timeout;
    }

    @JsonProperty public Long getSince() {
        return since;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setBit(Long bit) {
        this.bit = bit;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public void setTimeout(Long timeout) {
        this.timeout = timeout;
    }

    public void setSince(Long since) {
        this.since = since;
    }
}
