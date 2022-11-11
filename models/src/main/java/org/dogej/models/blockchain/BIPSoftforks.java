package org.dogej.models.blockchain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BIPSoftforks {
    private CSV csv;
    private Segwit segwit;
    private String warnings;

    @JsonProperty public CSV getCsv() {
        return csv;
    }

    public void setCsv(CSV csv) {
        this.csv = csv;
    }

    @JsonProperty public Segwit getSegwit() {
        return segwit;
    }

    public void setSegwit(Segwit segwit) {
        this.segwit = segwit;
    }

    @JsonProperty public String getWarnings() {
        return warnings;
    }

    public void setWarnings(String warnings) {
        this.warnings = warnings;
    }

    @Override
    public String toString() {
        return "BIPSoftforks{" +
                "csv=" + csv +
                ", segwit=" + segwit +
                ", warnings='" + warnings + '\'' +
                '}';
    }
}
