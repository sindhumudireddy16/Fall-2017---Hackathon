package com.umkc.medspace.api.request;

public class Slot_Request {
    private String d_name;
    private String h_name;
    private String designation;
    int did;
    int hid;

    private String mon_start;
    private String mon_end;
    private String tue_start;
    private String tue_end;
    private String wed_start;
    private String wed_end;
    private String thu_start;
    private String thu_end;
    private String fri_start;
    private String fri_end;
    private String sat_start;
    private String sat_end;
    private String sun_start;
    private String sun_end;


    public Slot_Request(String d_name, String h_name, String designation, int did, int hid, String mon_start, String mon_end, String tue_start, String tue_end, String wed_start, String wed_end, String thu_start, String thu_end, String fri_start, String fri_end, String sat_start, String sat_end, String sun_start, String sun_end) {
        this.d_name = d_name;
        this.h_name = h_name;
        this.designation = designation;
        this.did = did;
        this.hid = hid;
        this.mon_start = mon_start;
        this.mon_end = mon_end;
        this.tue_start = tue_start;
        this.tue_end = tue_end;
        this.wed_start = wed_start;
        this.wed_end = wed_end;
        this.thu_start = thu_start;
        this.thu_end = thu_end;
        this.fri_start = fri_start;
        this.fri_end = fri_end;
        this.sat_start = sat_start;
        this.sat_end = sat_end;
        this.sun_start = sun_start;
        this.sun_end = sun_end;
    }


}
