package com.umkc.medspace.api.request;

public class Patient_SignUp_Request {
    private String name;
    private String mobile;
    private String email;
    private String password;
    private String gender;

    public Patient_SignUp_Request(String name, String mobile, String email, String password, String gender) {
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
        this.gender = gender;
    }
}
