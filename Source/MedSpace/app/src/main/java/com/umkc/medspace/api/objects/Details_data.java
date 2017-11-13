package com.umkc.medspace.api.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Details_data {


    @SerializedName("sid")
    @Expose
    private int sid;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("hospital")
    @Expose
    private String hospital;
    @SerializedName("designation")
    @Expose
    private String designation;
    @SerializedName("mobile")
    @Expose
    private String mobile;

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
