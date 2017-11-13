package com.umkc.medspace.doctor;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.umkc.medspace.BaseAppcompatActivity;
import com.umkc.medspace.Constants.Constants;
import com.umkc.medspace.R;
import com.umkc.medspace.Singleton;
import com.umkc.medspace.adapters.Hospital_list_Adapter;
import com.umkc.medspace.api.ApiServices;
import com.umkc.medspace.api.ServiceGenerator;
import com.umkc.medspace.api.response.Hospital_data;
import com.umkc.medspace.api.response.Hospitals;
import com.umkc.medspace.patient.Chat;
import com.umkc.medspace.patient.Specialist;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Doctor_Hospital extends BaseAppcompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Hospital_list_Adapter hospitals_adapter;
    private LinearLayoutManager linearLayout;
    private RecyclerView rvHospitals;
    List<Hospital_data> hospitals = new ArrayList<>();
    private ApiServices apiClientToken;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private View navHeader;
    private TextView txtName, txtWebsite;
    private ImageView imgNavHeaderBg, imgProfile;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_back = (ImageView) findViewById(R.id.toolbar_back);
        toolbar_menu = (ImageView) findViewById(R.id.toolbar_menu);
        toolbar_back.setVisibility(View.GONE);
        toolbar_menu.setVisibility(View.VISIBLE);
        toolbar_title.setText("Hospitals");

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        // Navigation view header
        navHeader = navigationView.getHeaderView(0);
        txtName = (TextView) navHeader.findViewById(R.id.name);

        rvHospitals = (RecyclerView) findViewById(R.id.rvHospitals);
        linearLayout = new LinearLayoutManager(this);
        rvHospitals.setLayoutManager(linearLayout);
        hospitals_adapter = new Hospital_list_Adapter(Doctor_Hospital.this, hospitals);
        rvHospitals.setAdapter(hospitals_adapter);
        // load nav menu header data
        loadNavHeader();
        getHospitalss();
        navigationView.setNavigationItemSelectedListener(this);
        toolbar_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(GravityCompat.START);  // OPEN DRAWER
            }
        });
    }

    private void loadNavHeader() {
        txtName.setText(Singleton.getPref(Constants.D_NAME, Doctor_Hospital.this));
    }

    private void getHospitalss() {
        showProgress(Constants.LOADING_MSG);
        apiClientToken = ServiceGenerator.createService(ApiServices.class, this);
        Call<Hospitals> call = apiClientToken.getHospitals();
        call.enqueue(new Callback<Hospitals>() {
            @Override
            public void onResponse(Call<Hospitals> call, Response<Hospitals> response) {
                if (response.isSuccessful()) {
                    dismissProgress();
                    hospitals.addAll(response.body().getData());
                    hospitals_adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<Hospitals> call, Throwable t) {
                dismissProgress();
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.nav_home:
                break;
            case R.id.chat:
                Intent intent = new Intent(this, Clients.class);
                intent.putExtra("name", Singleton.getPref(Constants.D_NAME, this));
                intent.putExtra("mobile", Singleton.getPref(Constants.D_MOBILE, this));
                startActivity(intent);
                break;
            case R.id.nav_settings:
                doLogout();
                break;
        }
        //close navigation drawer
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void doLogout() {
        Singleton.clear();
        Singleton.clearPref(Constants.UAS, Doctor_Hospital.this);
        Singleton.clearPref(Constants.D_NAME, Doctor_Hospital.this);
        Singleton.clearPref(Constants.D_ID, Doctor_Hospital.this);
        Singleton.clearPref(Constants.D_EMAIL, Doctor_Hospital.this);
        Singleton.clearPref(Constants.P_ID, Doctor_Hospital.this);
        Singleton.clearPref(Constants.P_NAME, Doctor_Hospital.this);
        Singleton.clearPref(Constants.P_EMAIL, Doctor_Hospital.this);
        Singleton.clearPref(Constants.P_MOBILE, Doctor_Hospital.this);
        Constants.killAll();
        startActivity(new Intent(Doctor_Hospital.this, Login.class));
        finish();
    }
}
