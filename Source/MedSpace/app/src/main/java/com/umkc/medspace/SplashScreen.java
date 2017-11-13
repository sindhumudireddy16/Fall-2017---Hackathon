package com.umkc.medspace;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.splunk.mint.Mint;
import com.umkc.medspace.Constants.Constants;
import com.umkc.medspace.doctor.Doctor_Hospital;
import com.umkc.medspace.doctor.Login;
import com.umkc.medspace.patient.Patient_Main;

public class SplashScreen extends BaseAppcompatActivity {
    private static int SPLASH_TIME_OUT = 2000;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_splash_screen;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mint.initAndStartSession(this.getApplication(), "7100d5ac");
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        boolean uas = Boolean.parseBoolean(Singleton.getPref(Constants.UAS, SplashScreen.this));
        gotoNext();
    }

    private void gotoNext() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (Singleton.getPref(Constants.UAS, SplashScreen.this).contains("doctor")) {
                    startActivity(new Intent(SplashScreen.this, Doctor_Hospital.class));
                    finish();
                } else if (Singleton.getPref(Constants.UAS, SplashScreen.this).contains("patient")) {
                    startActivity(new Intent(SplashScreen.this, Patient_Main.class));
                    finish();
                } else {
                    Singleton.clearPref(Constants.UAS, SplashScreen.this);
                    startActivity(new Intent(SplashScreen.this, Login.class));
                    finish();
                }
            }
        }, SPLASH_TIME_OUT);
    }
}
