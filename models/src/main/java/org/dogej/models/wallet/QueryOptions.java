package org.dogej.models.wallet;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class QueryOptions {
    private Double minimumAmount;
    private Double maximumAmount;
    private Long maximumCount;
    private Double minimumSumAmount;

    @JsonProperty public Double getMinimumAmount() {
        return minimumAmount;
    }

    @JsonProperty public Double getMaximumAmount() {
        return maximumAmount;
    }

    @JsonProperty public Long getMaximumCount() {
        return maximumCount;
    }

    @JsonProperty public Double getMinimumSumAmount() {
        return minimumSumAmount;
    }

    public void setMinimumAmount(Double minimumAmount) {
        this.minimumAmount = minimumAmount;
    }

    public void setMaximumAmount(Double maximumAmount) {
        this.maximumAmount = maximumAmount;
    }

    public void setMaximumCount(Long maximumCount) {
        this.maximumCount = maximumCount;
    }

    public void setMinimumSumAmount(Double minimumSumAmount) {
        this.minimumSumAmount = minimumSumAmount;
    }

    @Override
    public String toString() {
        return "QueryOptions{" +
                "minimumAmount=" + minimumAmount +
                ", maximumAmount=" + maximumAmount +
                ", maximumCount=" + maximumCount +
                ", minimumSumAmount=" + minimumSumAmount +
                '}';
    }
}
