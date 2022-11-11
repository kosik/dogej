package org.dogej.models.wallet;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

public class WalletTransaction {
    private String account;
    private String address;
    private String category;
    private Double amount;
    private String label;
    private Long vout;
    private Long confirmations;
    private Boolean trusted;
    private String txid;
    private String[] walletconflicts;
    private Long time;
    private Long timereceived;
    private String bip125Replaceable;
    private Boolean generated;
    private String blockhash;
    private Long blockindex;
    private Double fee;
    private Boolean abandoned;

    @JsonProperty public String getAccount() {
        return account;
    }

    @JsonProperty public String getAddress() {
        return address;
    }

    @JsonProperty public String getCategory() {
        return category;
    }

    @JsonProperty public Double getAmount() {
        return amount;
    }

    @JsonProperty public String getLabel() {
        return label;
    }

    @JsonProperty public Long getVout() {
        return vout;
    }

    @JsonProperty public Long getConfirmations() {
        return confirmations;
    }

    @JsonProperty public Boolean getTrusted() {
        return trusted;
    }

    @JsonProperty public String getTxid() {
        return txid;
    }

    @JsonProperty public String[] getWalletconflicts() {
        return walletconflicts;
    }

    @JsonProperty public Long getTime() {
        return time;
    }

    @JsonProperty public Long getTimereceived() {
        return timereceived;
    }

    @JsonProperty("bip125-replaceable") public String getBip125Replaceable() {
        return bip125Replaceable;
    }

    @JsonProperty public Boolean getGenerated() {
        return generated;
    }

    @JsonProperty public String getBlockhash() {
        return blockhash;
    }

    @JsonProperty public Long getBlockindex() {
        return blockindex;
    }

    public void setBlockhash(String blockhash) {
        this.blockhash = blockhash;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setVout(Long vout) {
        this.vout = vout;
    }

    public void setConfirmations(Long confirmations) {
        this.confirmations = confirmations;
    }

    public void setTrusted(Boolean trusted) {
        this.trusted = trusted;
    }

    public void setTxid(String txid) {
        this.txid = txid;
    }

    public void setWalletconflicts(String[] walletconflicts) {
        this.walletconflicts = walletconflicts;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public void setTimereceived(Long timereceived) {
        this.timereceived = timereceived;
    }

    public void setBip125Replaceable(String bip125Replaceable) {
        this.bip125Replaceable = bip125Replaceable;
    }

    public void setGenerated(Boolean generated) {
        this.generated = generated;
    }

    public void setBlockindex(Long blockindex) {
        this.blockindex = blockindex;
    }

    @JsonProperty public Boolean getAbandoned() {
        return abandoned;
    }

    public void setAbandoned(Boolean abandoned) {
        this.abandoned = abandoned;
    }

    @JsonProperty public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    @Override
    public String toString() {
        return "WalletTransaction{" +
                "account='" + account + '\'' +
                ", address='" + address + '\'' +
                ", category='" + category + '\'' +
                ", amount=" + amount +
                ", label='" + label + '\'' +
                ", vout=" + vout +
                ", confirmations=" + confirmations +
                ", trusted=" + trusted +
                ", txid='" + txid + '\'' +
                ", walletconflicts=" + Arrays.toString(walletconflicts) +
                ", time=" + time +
                ", timereceived=" + timereceived +
                ", bip125Replaceable='" + bip125Replaceable + '\'' +
                ", generated=" + generated +
                ", blockhash='" + blockhash + '\'' +
                ", blockindex=" + blockindex +
                '}';
    }

}
