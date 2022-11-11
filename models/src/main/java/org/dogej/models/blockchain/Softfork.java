package org.dogej.models.blockchain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Softfork {
    private String id;
    private Long version;
    private String reject;

    @JsonProperty public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @JsonIgnore public String getReject() {
        return reject;
    }

    public void setReject(String reject) {
        this.reject = reject;
    }

}
