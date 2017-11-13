package com.umkc.medspace.doctor;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.umkc.medspace.BaseAppcompatActivity;
import com.umkc.medspace.Constants.Constants;
import com.umkc.medspace.R;
import com.umkc.medspace.adapters.Clients_Adapter;
import com.umkc.medspace.adapters.Specialist_Detail_Adapter;
import com.umkc.medspace.api.ApiServices;
import com.umkc.medspace.api.ServiceGenerator;
import com.umkc.medspace.api.objects.Details;
import com.umkc.medspace.api.objects.Details_data;
import com.umkc.medspace.patient.Specialist_Details;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Clients extends BaseAppcompatActivity {
    private String specialist, sid;
    private RecyclerView rvSpecialists;
    private LinearLayoutManager linearLayout;
    private Clients_Adapter adapter;
    List<Details_data> details_data = new ArrayList<>();
    private ApiServices apiClientToken;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_clients;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_back = (ImageView) findViewById(R.id.toolbar_back);
        toolbar_back.setVisibility(View.VISIBLE);

        toolbar_title.setText("Clients");

        toolbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        rvSpecialists = (RecyclerView) findViewById(R.id.rvDetails);
        linearLayout = new LinearLayoutManager(this);
        rvSpecialists.setLayoutManager(linearLayout);

        adapter = new Clients_Adapter(Clients.this, details_data, specialist);
        rvSpecialists.setAdapter(adapter);
        getDetails();
    }

    private void getDetails() {
        showProgress(Constants.LOADING_MSG);
        apiClientToken = ServiceGenerator.createService(ApiServices.class, this);
        Call<Details> call = apiClientToken.getDetails();
        call.enqueue(new Callback<Details>() {
            @Override
            public void onResponse(Call<Details> call, Response<Details> response) {
                if (response.isSuccessful()) {
                    dismissProgress();
                    details_data.addAll(response.body().getData());
                    Collections.shuffle(details_data);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<Details> call, Throwable t) {

            }
        });
    }
}
