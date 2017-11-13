package com.umkc.medspace.Constants;

import android.app.Activity;

import java.util.ArrayList;


public class Constants {

    public static final String ACCESS_TOKEN = "";
    public static final String UAS = "UAS";
    public static final String BASE_URL = "http://www.featureio.com/";
    public static final String LOADING_MSG = "Loading...";


    public static final String D_ID = "D_ID";
    public static final String D_NAME = "D_NAME";
    public static final String D_EMAIL = "D_EMAIL";

    public static final String REGISTER_URL = "api/doctor/Register.php";
    public static final String DOCTOR_LOGIN = "api/doctor/login.php";
    public static final String HOSPITALS = "api/doctor/hospitals.php";
    public static final String SLOTS = "api/doctor/slots.php";
    public static final String SPECIALISTS = "api/patient/specialists.php";

    public static final String PATIENT_SIGNUP = "api/patient/signup.php";
    public static final String PATIENT_LOGIN = "api/patient/login.php";
    public static final String P_ID = "pid";
    public static final String P_NAME = "pname";
    public static final String P_MOBILE = "pmobile";
    public static final String P_EMAIL = "pemail";
    public static final String DETAILS = "api/patient/details.php";
    public static final String D_MOBILE = "dmobile";
    public static ArrayList<Activity> activity_stack = new ArrayList<Activity>();
    public static void killAll() {
        for (Activity act : Constants.activity_stack) {
            act.finish();
        }
        Constants.activity_stack.clear();
    }
}
