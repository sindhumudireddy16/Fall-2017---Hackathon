package com.umkc.medspace.api.request;

public class Doctor_SignUp_Request {
    private String name;
    private String mobile;
    private String email;
    private String designation;
    private String password;

    public Doctor_SignUp_Request(String name, String mobile,String email, String designation, String password) {
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.designation = designation;
        this.password = password;
    }
}
