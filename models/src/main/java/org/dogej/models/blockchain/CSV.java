package org.dogej.models.blockchain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CSV {
    private String status;
    private Long startTime;
    private Long timeout;
    private Long since;
    private Long bit;

    @JsonProperty public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    @JsonProperty public Long getTimeout() {
        return timeout;
    }

    public void setTimeout(Long timeout) {
        this.timeout = timeout;
    }

    @JsonProperty public Long getSince() {
        return since;
    }

    public void setSince(Long since) {
        this.since = since;
    }

    @JsonProperty public Long getBit() {
        return bit;
    }

    public void setBit(Long bit) {
        this.bit = bit;
    }

    @Override
    public String toString() {
        return "CSV{" +
                "status='" + status + '\'' +
                ", startTime=" + startTime +
                ", timeout=" + timeout +
                ", since=" + since +
                ", bit=" + bit +
                '}';
    }
}
