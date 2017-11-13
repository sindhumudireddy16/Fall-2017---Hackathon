package com.umkc.medspace.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Slot_response {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("status-code")
    @Expose
    private Integer statusCode;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

}
