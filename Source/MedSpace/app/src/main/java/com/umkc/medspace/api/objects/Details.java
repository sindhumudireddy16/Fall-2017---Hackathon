package com.umkc.medspace.api.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Details {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("status-code")
    @Expose
    private Integer statusCode;
    @SerializedName("data")
    @Expose
    private List<Details_data> data = null;

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

    public List<Details_data> getData() {
        return data;
    }

    public void setData(List<Details_data> data) {
        this.data = data;
    }
}