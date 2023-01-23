package org.dogej.models.rawtx;

public class TransactionInput {
    private String txid;
    private Long vout;

    public TransactionInput(String txid, Long vout) {
        this.txid = txid;
        this.vout = vout;
    }

    public String getTxid() {
        return txid;
    }

    public void setTxid(String txid) {
        this.txid = txid;
    }

    public Long getVout() {
        return vout;
    }

    public void setVout(Long vout) {
        this.vout = vout;
    }

    @Override
    public String toString() {
        return "Input{" +
                "txid='" + txid + '\'' +
                ", vout=" + vout +
                '}';
    }
}
