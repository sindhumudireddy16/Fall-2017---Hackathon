package com.umkc.medspace.patient;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.umkc.medspace.BaseAppcompatActivity;
import com.umkc.medspace.Constants.Constants;
import com.umkc.medspace.R;
import com.umkc.medspace.adapters.Specialist_list_Adapter;
import com.umkc.medspace.api.ApiServices;
import com.umkc.medspace.api.ServiceGenerator;
import com.umkc.medspace.api.response.Specialist_data;
import com.umkc.medspace.api.response.Specialists;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Specialist extends BaseAppcompatActivity {

    private RecyclerView rvSpecialists;
    private LinearLayoutManager linearLayout;
    private ApiServices apiClientToken;
    private Specialist_list_Adapter adapter;
    List<Specialist_data> specialistData = new ArrayList<>();

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_specialists;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_back = (ImageView) findViewById(R.id.toolbar_back);
        toolbar_back.setVisibility(View.VISIBLE);
        toolbar_title.setText("Specialists");

        toolbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        rvSpecialists = (RecyclerView) findViewById(R.id.rvSpecialists);
        linearLayout = new LinearLayoutManager(this);
        rvSpecialists.setLayoutManager(linearLayout);

        adapter = new Specialist_list_Adapter(Specialist.this, specialistData);
        rvSpecialists.setAdapter(adapter);

        getSpecialists();
    }

    private void getSpecialists() {
        showProgress(Constants.LOADING_MSG);
        apiClientToken = ServiceGenerator.createService(ApiServices.class, this);
        Call<Specialists> call = apiClientToken.getSpecialists();
        call.enqueue(new Callback<Specialists>() {
            @Override
            public void onResponse(Call<Specialists> call, Response<Specialists> response) {
                if (response.isSuccessful()) {
                    dismissProgress();
                    specialistData.addAll(response.body().getData());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<Specialists> call, Throwable t) {

            }
        });
    }
}
