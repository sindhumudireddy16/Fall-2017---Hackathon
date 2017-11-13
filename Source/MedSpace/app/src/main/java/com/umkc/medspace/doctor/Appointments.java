package com.umkc.medspace.doctor;

import android.os.Bundle;
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
import com.umkc.medspace.api.request.Slot_Request;
import com.umkc.medspace.api.response.Slot_response;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Appointments extends BaseAppcompatActivity implements View.OnClickListener {

    private EditText etname;
    private EditText etDesignation, etHospital;
    private EditText mon_start, mon_end, tue_start, tue_end, wed_start, wed_end,
            thu_start, thu_end, fri_start, fri_end, sat_start, sat_end, sun_start, sun_end;
    private String h_name, h_id;
    private Button btnSlot;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_appointments;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_back = (ImageView) findViewById(R.id.toolbar_back);
        toolbar_back.setVisibility(View.VISIBLE);
        toolbar_title.setText("Set Slots");

        toolbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        h_name = getIntent().getExtras().getString("hospital_name", "");
        h_id = getIntent().getExtras().getString("hid", "");

        etDesignation = (EditText) findViewById(R.id.etDesignation);
        etHospital = (EditText) findViewById(R.id.etHospital);

        mon_start = (EditText) findViewById(R.id.monstart);
        mon_end = (EditText) findViewById(R.id.monend);
        tue_start = (EditText) findViewById(R.id.tuestart);
        tue_end = (EditText) findViewById(R.id.tueend);
        wed_start = (EditText) findViewById(R.id.wedstart);
        wed_end = (EditText) findViewById(R.id.wedend);
        thu_start = (EditText) findViewById(R.id.thustart);
        thu_end = (EditText) findViewById(R.id.thuend);
        fri_start = (EditText) findViewById(R.id.fristart);
        fri_end = (EditText) findViewById(R.id.friend);
        sat_start = (EditText) findViewById(R.id.satstart);
        sat_end = (EditText) findViewById(R.id.satend);
        sun_start = (EditText) findViewById(R.id.sunstart);
        sun_end = (EditText) findViewById(R.id.sunend);
        btnSlot = (Button) findViewById(R.id.btnSlot);

        etHospital.setText(h_name);
        btnSlot.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSlot:
                String d_name = Singleton.getPref(Constants.D_NAME, this);
                int did = Integer.parseInt(Singleton.getPref(Constants.D_ID, this));
                String hospital_name = h_name.toString().trim();
                String designation = etDesignation.getText().toString();
                int hid = Integer.parseInt(h_id);
                String mons = mon_start.getText().toString();
                String mone = mon_end.getText().toString();
                String tues = tue_start.getText().toString();
                String tuee = tue_end.getText().toString();
                String weds = wed_start.getText().toString();
                String wede = wed_end.getText().toString();
                String thus = thu_start.getText().toString();
                String thue = thu_end.getText().toString();
                String fris = fri_start.getText().toString();
                String frie = fri_end.getText().toString();
                String sats = sat_start.getText().toString();
                String sate = sat_end.getText().toString();
                String suns = sun_start.getText().toString();
                String sune = sun_end.getText().toString();
                uploadSlots(d_name, hospital_name, designation, hid, did, mons, mone, tues, tuee, weds, wede, thus, thue, fris, frie, sats, sate, suns, sune);
                break;
        }
    }

    private void uploadSlots(String d_name, String hospital_name, String s, int hid, int did, String mons, String mone, String tues, String tuee, String weds, String wede, String thus, String thue, String fris, String frie, String sats, String sate, String suns, String sune) {
        showProgress(Constants.LOADING_MSG);
        Slot_Request request = new Slot_Request(d_name, hospital_name, s, hid, did, mons, mone, tues, tuee, weds, wede, thus, thue, fris, frie, sats, sate, suns, sune);
        ApiServices apiClientToken = ServiceGenerator.createService(ApiServices.class, Appointments.this);
        final Call<Slot_response> slot_responseCall = apiClientToken.setslot(request);
        slot_responseCall.enqueue(new Callback<Slot_response>() {
            @Override
            public void onResponse(Call<Slot_response> call, Response<Slot_response> response) {
                if (response.isSuccessful()) {
                    dismissProgress();
                    Toast.makeText(Appointments.this, response.body().getStatus(), Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    dismissProgress();
                    Toast.makeText(Appointments.this, "Slot upload failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Slot_response> call, Throwable t) {
                dismissProgress();
                Toast.makeText(Appointments.this, "Slot upload failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
