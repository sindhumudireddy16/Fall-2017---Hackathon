package com.umkc.medspace.doctor;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.umkc.medspace.BaseAppcompatActivity;
import com.umkc.medspace.Constants.Constants;
import com.umkc.medspace.R;
import com.umkc.medspace.Singleton;
import com.umkc.medspace.api.ApiServices;
import com.umkc.medspace.api.ServiceGenerator;
import com.umkc.medspace.api.request.Doctor_Login_Request;
import com.umkc.medspace.api.response.Doctor_Login_Response;
import com.umkc.medspace.api.response.Patient_Login_Response;
import com.umkc.medspace.patient.Patient_Main;
import com.umkc.medspace.patient.Patient_Signup;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends BaseAppcompatActivity {

    private Button btnLogin;
    private EditText etPassword;
    private EditText etUsername;
    private TextView dlinkSignUp, plinkSignUp;
    private TextView doctor;
    private TextView patient;
    private RelativeLayout rlDoctotLogin;
    private RelativeLayout rlPatientLogin;
    private EditText etUsernameP, etPasswordP;
    private Button btnLoginp;

    @Override
    protected int getLayoutResId() {
        return R.layout.login_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_back = (ImageView) findViewById(R.id.toolbar_back);
        toolbar_back.setVisibility(View.GONE);
        toolbar_title.setText("Login");

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLoginp = (Button) findViewById(R.id.btnLoginP);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etUsernameP = (EditText) findViewById(R.id.etUsernameP);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etPasswordP = (EditText) findViewById(R.id.etPasswordP);
        dlinkSignUp = (TextView) findViewById(R.id.dlinkSignUp);
        plinkSignUp = (TextView) findViewById(R.id.plinkSignUp);
        doctor = (TextView) findViewById(R.id.doctor);
        patient = (TextView) findViewById(R.id.patient);
        rlDoctotLogin = (RelativeLayout) findViewById(R.id.rlDoctotLogin);
        rlPatientLogin = (RelativeLayout) findViewById(R.id.rlPatientLogin);
        doctor.setTextColor(getResources().getColor(R.color.black));
        patient.setTextColor(getResources().getColor(R.color.dark_blue));
        doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rlDoctotLogin.setVisibility(View.VISIBLE);
                rlPatientLogin.setVisibility(View.GONE);
                doctor.setTextColor(getResources().getColor(R.color.black));
                patient.setTextColor(getResources().getColor(R.color.dark_blue));
            }
        });

        patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rlPatientLogin.setVisibility(View.VISIBLE);
                rlDoctotLogin.setVisibility(View.GONE);
                doctor.setTextColor(getResources().getColor(R.color.dark_blue));
                patient.setTextColor(getResources().getColor(R.color.black));
            }
        });

        etPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (!etUsername.getText().toString().isEmpty() && etPassword.getText().toString().length() >= 4) {
                    String email = etUsername.getText().toString();
                    String pass = etPassword.getText().toString();
                    getDoctor(email, pass);
                } else {
                    Toast.makeText(Login.this, "Username and password you entered is invalid", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });

        etPasswordP.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (!etUsernameP.getText().toString().isEmpty() && etPasswordP.getText().toString().length() >= 4) {
                    String email = etUsernameP.getText().toString();
                    String pass = etPasswordP.getText().toString();
                    getPatientSignin(email, pass);
                } else {
                    Toast.makeText(Login.this, "Username and password you entered is invalid", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });

        dlinkSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Doctor_Signup.class));
            }
        });

        plinkSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Patient_Signup.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etUsername.getText().toString().isEmpty() && etPassword.getText().toString().length() >= 4) {
                    String email = etUsername.getText().toString();
                    String pass = etPassword.getText().toString();
                    getDoctor(email, pass);
                } else {
                    Toast.makeText(Login.this, "Username and password you entered is invalid", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnLoginp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etUsernameP.getText().toString().isEmpty() && etPasswordP.getText().toString().length() >= 6) {
                    String email = etUsernameP.getText().toString();
                    String pass = etPasswordP.getText().toString();
                    getPatientSignin(email, pass);
                } else {
                    Toast.makeText(Login.this, "Username and password you entered is invalid", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getPatientSignin(String email, String pass) {
        showProgress(Constants.LOADING_MSG);
        Doctor_Login_Request request = new Doctor_Login_Request(email, pass);
        ApiServices apiClientToken = ServiceGenerator.createService(ApiServices.class, Login.this);
        final Call<Patient_Login_Response> plogin = apiClientToken.patientLogin(request);
        plogin.enqueue(new Callback<Patient_Login_Response>() {
            @Override
            public void onResponse(Call<Patient_Login_Response> call, Response<Patient_Login_Response> response) {
                if (response.isSuccessful()) {
                    dismissProgress();
                    Singleton.setPref(Constants.UAS, "patient", Login.this);
                    Singleton.setPref(Constants.P_ID, response.body().getPid(), Login.this);
                    Singleton.setPref(Constants.P_NAME, response.body().getName(), Login.this);
                    Singleton.setPref(Constants.P_MOBILE, response.body().getMobile(), Login.this);
                    Singleton.setPref(Constants.P_EMAIL, response.body().getEmail(), Login.this);
                    startActivity(new Intent(Login.this, Patient_Main.class));
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Patient_Login_Response> call, Throwable t) {
                dismissProgress();
                Toast.makeText(Login.this, "failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getDoctor(String email, String pass) {
        showProgress(Constants.LOADING_MSG);
        Doctor_Login_Request request = new Doctor_Login_Request(email, pass);
        ApiServices apiClientToken = ServiceGenerator.createService(ApiServices.class, Login.this);
        final Call<Doctor_Login_Response> dlogin = apiClientToken.doctorLogin(request);
        dlogin.enqueue(new Callback<Doctor_Login_Response>() {
            @Override
            public void onResponse(Call<Doctor_Login_Response> call, Response<Doctor_Login_Response> response) {
                if (response.isSuccessful()) {
                    dismissProgress();
                    Singleton.setPref(Constants.D_ID, response.body().getDid(), Login.this);
                    Singleton.setPref(Constants.D_NAME, response.body().getName(), Login.this);
                    Singleton.setPref(Constants.D_EMAIL, response.body().getEmail(), Login.this);
                    Singleton.setPref(Constants.UAS, "doctor", Login.this);
                    startActivity(new Intent(Login.this, Doctor_Hospital.class));
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Doctor_Login_Response> call, Throwable t) {
                Toast.makeText(Login.this, "failure", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
