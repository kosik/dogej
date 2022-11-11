package org.dogej.models.blockchain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Vout {
    private Double value;
    private Long n;
    private ScriptPubKey scriptPubKey;

    @JsonProperty public Double getValue() {
        return value;
    }

    @JsonProperty public Long getN() {
        return n;
    }

    @JsonProperty public ScriptPubKey getScriptPubKey() {
        return scriptPubKey;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public void setN(Long n) {
        this.n = n;
    }

    public void setScriptPubKey(ScriptPubKey scriptPubKey) {
        this.scriptPubKey = scriptPubKey;
    }

    @Override
    public String toString() {
        return "Vout{" +
                "value=" + value +
                ", n=" + n +
                ", scriptPubKey=" + scriptPubKey +
                '}';
    }
}
