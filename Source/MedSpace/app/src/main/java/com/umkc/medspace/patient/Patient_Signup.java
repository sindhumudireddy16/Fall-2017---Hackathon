package com.umkc.medspace.patient;

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
import com.umkc.medspace.api.request.Patient_SignUp_Request;
import com.umkc.medspace.api.response.Patient_SignUp_Response;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Patient_Signup extends BaseAppcompatActivity {

    private TextView plinkSignin;
    private EditText etName, etEmail, etPassword, etGender, etMobile;
    private Button btnSignup;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_patient_signup;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_back = (ImageView) findViewById(R.id.toolbar_back);
        toolbar_back.setVisibility(View.VISIBLE);
        toolbar_title.setText("Sign Up");

        plinkSignin = (TextView) findViewById(R.id.plinkSignin);

        etName = (EditText) findViewById(R.id.etUsername);
        etMobile = (EditText) findViewById(R.id.etMobile);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etGender = (EditText) findViewById(R.id.etGender);
        btnSignup = (Button) findViewById(R.id.btnSignup);

        toolbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        plinkSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString().trim();
                String mobile = etMobile.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String pass = etPassword.getText().toString().trim();
                String gender = etGender.getText().toString().trim();
                patientSignup(name, mobile, email, pass, gender);
            }
        });

        etPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String name = etName.getText().toString().trim();
                String mobile = etMobile.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String pass = etPassword.getText().toString().trim();
                String gender = etGender.getText().toString().trim();
                patientSignup(name, mobile, email, pass, gender);
                return false;
            }
        });

    }

    private void patientSignup(String name, String mobile, String email, String pass, String gender) {
        showProgress(Constants.LOADING_MSG);
        Patient_SignUp_Request request = new Patient_SignUp_Request(name, mobile, email, pass, gender);
        ApiServices apiClientToken = ServiceGenerator.createService(ApiServices.class, Patient_Signup.this);
        final Call<Patient_SignUp_Response> signup = apiClientToken.psignup(request);
        signup.enqueue(new Callback<Patient_SignUp_Response>() {
            @Override
            public void onResponse(Call<Patient_SignUp_Response> call, Response<Patient_SignUp_Response> response) {
                if (response.isSuccessful()) {
                    dismissProgress();
                    Singleton.setPref(Constants.UAS, "true", Patient_Signup.this);
                    Singleton.setPref(Constants.P_ID, response.body().getPid(), Patient_Signup.this);
                    Singleton.setPref(Constants.P_NAME, response.body().getName(), Patient_Signup.this);
                    Singleton.setPref(Constants.P_MOBILE, response.body().getMobile(), Patient_Signup.this);
                    Singleton.setPref(Constants.P_EMAIL, response.body().getEmail(), Patient_Signup.this);
                    startActivity(new Intent(Patient_Signup.this, Patient_Main.class));
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Patient_SignUp_Response> call, Throwable t) {
                dismissProgress();
                Toast.makeText(Patient_Signup.this, "failure", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
