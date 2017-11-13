package com.umkc.medspace.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by manojvayubollam on 11/11/17.
 */

public class Specialist_data {

    @SerializedName("sid")
    @Expose
    private int sid;
    @SerializedName("specialists")
    @Expose
    private String specialists;

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSpecialists() {
        return specialists;
    }

    public void setSpecialists(String specialists) {
        this.specialists = specialists;
    }

}
