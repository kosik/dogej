package org.dogej.models.blockchain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ScriptPubKey {
    private String asm;
    private String hex;
    private Long reqSigs;
    private String type;
    private String[] addresses;

    @JsonProperty public String getAsm() {
        return asm;
    }

    @JsonProperty public String getHex() {
        return hex;
    }

    @JsonProperty public Long getReqSigs() {
        return reqSigs;
    }

    @JsonProperty public String getType() {
        return type;
    }

    @JsonProperty public String[] getAddresses() {
        return addresses;
    }

    public void setAsm(String asm) {
        this.asm = asm;
    }

    public void setHex(String hex) {
        this.hex = hex;
    }

    public void setReqSigs(Long reqSigs) {
        this.reqSigs = reqSigs;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAddresses(String[] addresses) {
        this.addresses = addresses;
    }
}
