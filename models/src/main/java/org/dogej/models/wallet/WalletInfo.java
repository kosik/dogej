package org.dogej.models.wallet;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WalletInfo {
    private Long walletversion;
    private Double balance;
    private Double unconfirmed_balance;
    private Double immature_balance;
    private Long txcount;
    private Long keypoololdest;
    private Long keypoolsize;
    private Double paytxfee;
    private String hdmasterkeyid;
    private Long unlockedUntil;

    @JsonProperty public Long getWalletversion() {
        return walletversion;
    }

    @JsonProperty public Double getBalance() {
        return balance;
    }

    @JsonProperty public Double getUnconfirmed_balance() {
        return unconfirmed_balance;
    }

    @JsonProperty public Double getImmature_balance() {
        return immature_balance;
    }

    @JsonProperty public Long getTxcount() {
        return txcount;
    }

    @JsonProperty public Long getKeypoololdest() {
        return keypoololdest;
    }

    @JsonProperty public Long getKeypoolsize() {
        return keypoolsize;
    }

    @JsonProperty public Double getPaytxfee() {
        return paytxfee;
    }

    @JsonProperty public String getHdmasterkeyid() {
        return hdmasterkeyid;
    }

    public void setWalletversion(Long walletversion) {
        this.walletversion = walletversion;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public void setUnconfirmed_balance(Double unconfirmed_balance) {
        this.unconfirmed_balance = unconfirmed_balance;
    }

    public void setImmature_balance(Double immature_balance) {
        this.immature_balance = immature_balance;
    }

    public void setTxcount(Long txcount) {
        this.txcount = txcount;
    }

    public void setKeypoololdest(Long keypoololdest) {
        this.keypoololdest = keypoololdest;
    }

    public void setKeypoolsize(Long keypoolsize) {
        this.keypoolsize = keypoolsize;
    }

    public void setPaytxfee(Double paytxfee) {
        this.paytxfee = paytxfee;
    }

    public void setHdmasterkeyid(String hdmasterkeyid) {
        this.hdmasterkeyid = hdmasterkeyid;
    }

    @JsonProperty("unlocked_until") public Long getUnlockedUntil() {
        return unlockedUntil;
    }

    public void setUnlockedUntil(Long unlockedUntil) {
        this.unlockedUntil = unlockedUntil;
    }

    @Override
    public String toString() {
        return "WalletInfo{" +
                "walletversion=" + walletversion +
                ", balance=" + balance +
                ", unconfirmed_balance=" + unconfirmed_balance +
                ", immature_balance=" + immature_balance +
                ", txcount=" + txcount +
                ", keypoololdest=" + keypoololdest +
                ", keypoolsize=" + keypoolsize +
                ", paytxfee=" + paytxfee +
                ", hdmasterkeyid='" + hdmasterkeyid + '\'' +
                ", unlockedUntil=" + unlockedUntil +
                '}';
    }
}
