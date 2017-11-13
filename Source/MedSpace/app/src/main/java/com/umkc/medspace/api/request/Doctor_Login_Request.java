package com.umkc.medspace.api.request;


public class Doctor_Login_Request {
    private String email;
    private String password;

    public Doctor_Login_Request(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
