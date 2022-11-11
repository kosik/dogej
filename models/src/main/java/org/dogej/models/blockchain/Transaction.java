package org.dogej.models.blockchain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

public class Transaction {
    private String hex;
    private String txid;
    private String hash;
    private Long size;
    private Long vsize;
    private Long version;
    private Long locktime;
    private String blockhash;

    private  String vin;//which structure except here ?
    private Vout[] vout;

    @JsonProperty public String getHex() {
        return hex;
    }

    @JsonProperty public String getTxid() {
        return txid;
    }

    @JsonProperty public String getHash() {
        return hash;
    }

    @JsonProperty public Long getSize() {
        return size;
    }

    @JsonProperty public Long getVsize() {
        return vsize;
    }

    @JsonProperty public Long getVersion() {
        return version;
    }

    @JsonProperty public Long getLocktime() {
        return locktime;
    }

    @JsonIgnore public String getVin() {
        return vin;
    }

    @JsonProperty public Vout[] getVout() {
        return vout;
    }

    @JsonProperty public String getBlockhash() {
        return blockhash;
    }

    public void setHex(String hex) {
        this.hex = hex;
    }

    public void setTxid(String txid) {
        this.txid = txid;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public void setVsize(Long vsize) {
        this.vsize = vsize;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public void setLocktime(Long locktime) {
        this.locktime = locktime;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public void setVout(Vout[] vout) {
        this.vout = vout;
    }

    public void setBlockhash(String blockhash) {
        this.blockhash = blockhash;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "hex='" + hex + '\'' +
                ", txid='" + txid + '\'' +
                ", hash='" + hash + '\'' +
                ", size=" + size +
                ", vsize=" + vsize +
                ", version=" + version +
                ", locktime=" + locktime +
                ", blockhash='" + blockhash + '\'' +
                ", vin='" + vin + '\'' +
                ", vout=" + Arrays.toString(vout) +
                '}';
    }
}
