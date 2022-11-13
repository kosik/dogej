package org.dogej.models.wallet;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Unspent {
    private String txid;
    private String address;
    private String account;
    private String label;
    private String scriptPubKey;
    private String redeemScript;
    private String witnessScript;
    private String desc;
    private Long vout;
    private Double amount;
    private Long confirmations;
    private Boolean spendable;
    private Boolean solvable;
    private Boolean reused;
    private Boolean safe;

    @JsonProperty public String getTxid() {
        return txid;
    }

    @JsonProperty public String getAddress() {
        return address;
    }

    @JsonProperty public String getAccount() {return account;}

    @JsonProperty public String getLabel() {
        return label;
    }

    @JsonProperty public String getScriptPubKey() {
        return scriptPubKey;
    }

    @JsonProperty public String getRedeemScript() {
        return redeemScript;
    }

    @JsonProperty public String getWitnessScript() {
        return witnessScript;
    }

    @JsonProperty public String getDesc() {
        return desc;
    }

    @JsonProperty public Long getVout() {
        return vout;
    }

    @JsonProperty public Double getAmount() {
        return amount;
    }

    @JsonProperty public Long getConfirmations() {
        return confirmations;
    }

    @JsonProperty public Boolean getSpendable() {
        return spendable;
    }

    @JsonProperty public Boolean getSolvable() {
        return solvable;
    }

    @JsonProperty public Boolean getReused() {
        return reused;
    }

    @JsonProperty public Boolean getSafe() {
        return safe;
    }

    public void setTxid(String txid) {
        this.txid = txid;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setScriptPubKey(String scriptPubKey) {
        this.scriptPubKey = scriptPubKey;
    }

    public void setRedeemScript(String redeemScript) {
        this.redeemScript = redeemScript;
    }

    public void setWitnessScript(String witnessScript) {
        this.witnessScript = witnessScript;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setVout(Long vout) {
        this.vout = vout;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setConfirmations(Long confirmations) {
        this.confirmations = confirmations;
    }

    public void setSpendable(Boolean spendable) {
        this.spendable = spendable;
    }

    public void setSolvable(Boolean solvable) {
        this.solvable = solvable;
    }

    public void setReused(Boolean reused) {
        this.reused = reused;
    }

    public void setSafe(Boolean safe) {
        this.safe = safe;
    }

    @Override
    public String toString() {
        return "Unspent{" +
                "txid='" + txid + '\'' +
                ", address='" + address + '\'' +
                ", account='" + account + '\'' +
                ", label='" + label + '\'' +
                ", scriptPubKey='" + scriptPubKey + '\'' +
                ", redeemScript='" + redeemScript + '\'' +
                ", witnessScript='" + witnessScript + '\'' +
                ", desc='" + desc + '\'' +
                ", vout=" + vout +
                ", amount=" + amount +
                ", confirmations=" + confirmations +
                ", spendable=" + spendable +
                ", solvable=" + solvable +
                ", reused=" + reused +
                ", safe=" + safe +
                '}';
    }
}
