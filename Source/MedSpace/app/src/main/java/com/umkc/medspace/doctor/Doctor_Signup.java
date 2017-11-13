package com.umkc.medspace.doctor;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.umkc.medspace.BaseAppcompatActivity;
import com.umkc.medspace.Constants.Constants;
import com.umkc.medspace.R;
import com.umkc.medspace.Singleton;
import com.umkc.medspace.api.ApiServices;
import com.umkc.medspace.api.ServiceGenerator;
import com.umkc.medspace.api.request.Doctor_SignUp_Request;
import com.umkc.medspace.api.response.Doctor_SignUp_Response;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Doctor_Signup extends BaseAppcompatActivity {

    private TextView linkSignin;
    private Button btnSignup;
    private EditText etUsername, etEmail, etDuty, etPassword;
    private String name, email, designation, password, mobile;
    private EditText etMobile;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_doctor_signup;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_back = (ImageView) findViewById(R.id.toolbar_back);
        toolbar_back.setVisibility(View.VISIBLE);
        toolbar_title.setText("Sign Up");

        linkSignin = (TextView) findViewById(R.id.dlinkSignin);
        btnSignup = (Button) findViewById(R.id.btnSignup);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etDuty = (EditText) findViewById(R.id.etDuty);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etMobile = (EditText) findViewById(R.id.etMobile);

        etPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                name = etUsername.getText().toString();
                email = etEmail.getText().toString();
                designation = etDuty.getText().toString();
                password = etPassword.getText().toString();
                mobile = etMobile.getText().toString();
                if (!name.isEmpty() && !email.isEmpty() && !mobile.isEmpty() && !password.isEmpty()) {
                    getSignedUp(name, mobile, email, designation, password);
                } else {
                    Toast.makeText(Doctor_Signup.this, "please input all fields", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });

        toolbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        linkSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSignedUp(name, mobile, email, designation, password);
            }
        });

    }


    private void getSignedUp(String name, String mobile, String email, String designation, String password) {
        showProgress(Constants.LOADING_MSG);
        Doctor_SignUp_Request request = new Doctor_SignUp_Request(name, mobile, email, designation, password);
        ApiServices apiClientToken = ServiceGenerator.createService(ApiServices.class, Doctor_Signup.this);
        final Call<Doctor_SignUp_Response> signup = apiClientToken.getRegistered(request);
        signup.enqueue(new Callback<Doctor_SignUp_Response>() {
            @Override
            public void onResponse(Call<Doctor_SignUp_Response> call, Response<Doctor_SignUp_Response> response) {
                if (response.isSuccessful()) {
                    dismissProgress();
                    Singleton.setPref(Constants.UAS, "true", Doctor_Signup.this);
                    Singleton.setPref(Constants.UAS, "doctor", Doctor_Signup.this);
                    Singleton.setPref(Constants.D_ID, String.valueOf(response.body().getDid()), Doctor_Signup.this);
                    Singleton.setPref(Constants.D_NAME, response.body().getName(), Doctor_Signup.this);
                    Singleton.setPref(Constants.D_MOBILE, response.body().getMobile(), Doctor_Signup.this);
                    Singleton.setPref(Constants.D_EMAIL, response.body().getEmail(), Doctor_Signup.this);
                    Toast.makeText(Doctor_Signup.this, response.body().getStatus(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Doctor_Signup.this, Doctor_Hospital.class));
                    finish();
                } else {
                    dismissProgress();
                    Toast.makeText(Doctor_Signup.this, "invalid inputs", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Doctor_SignUp_Response> call, Throwable t) {
                dismissProgress();
                Toast.makeText(Doctor_Signup.this, "failure", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
