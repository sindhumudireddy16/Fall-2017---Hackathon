package com.umkc.medspace.patient;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.umkc.medspace.BaseAppcompatActivity;
import com.umkc.medspace.Constants.Constants;
import com.umkc.medspace.R;
import com.umkc.medspace.Singleton;
import com.umkc.medspace.doctor.Login;

public class Help_Support extends BaseAppcompatActivity {

    private RelativeLayout rl1;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_help_support;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_back = (ImageView) findViewById(R.id.toolbar_back);
        toolbar_back.setVisibility(View.VISIBLE);
        toolbar_title.setText("Help & Support");


        toolbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        rl1 = (RelativeLayout) findViewById(R.id.rl1);
        rl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Singleton.clear();
                Singleton.clearPref(Constants.UAS, Help_Support.this);
                Singleton.clearPref(Constants.D_NAME, Help_Support.this);
                Singleton.clearPref(Constants.D_ID, Help_Support.this);
                Singleton.clearPref(Constants.D_EMAIL, Help_Support.this);
                Singleton.clearPref(Constants.P_ID, Help_Support.this);
                Singleton.clearPref(Constants.P_NAME, Help_Support.this);
                Singleton.clearPref(Constants.P_EMAIL, Help_Support.this);
                Singleton.clearPref(Constants.P_MOBILE, Help_Support.this);
                Constants.killAll();
                startActivity(new Intent(Help_Support.this, Login.class));
                finish();
            }
        });
    }
}
