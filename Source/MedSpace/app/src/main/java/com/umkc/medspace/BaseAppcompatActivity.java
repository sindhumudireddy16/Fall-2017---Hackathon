package com.umkc.medspace;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public abstract class BaseAppcompatActivity extends AppCompatActivity {
    protected Toolbar toolbar;
    protected TextView toolbar_title, tvClear;
    protected ImageView toolbar_back, toolbar_ok,toolbar_menu;
    private ProgressDialog mProgressDialog;

    protected abstract int getLayoutResId();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar_title = (TextView) toolbar.findViewById(R.id.toolbar_title);
//        tvClear = (TextView) toolbar.findViewById(R.id.tvClear);
//        toolbar_back = (ImageView) toolbar.findViewById(R.id.toolbar_back);
//        toolbar_back.setVisibility(View.GONE);
//        toolbar_title.setTypeface(FontManager.getClansBold(this));
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }


    protected void showProgress(String msg) {
        if (mProgressDialog != null && mProgressDialog.isShowing())
            dismissProgress();
        mProgressDialog = ProgressDialog.show(this, "", msg);
    }

    protected void dismissProgress() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }

    public boolean isNetworkStatusAvialable(Context context, View view, String msg) {
        boolean valid = true;
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            snackBar(view, msg);
            valid = false;
        } else {
            valid = true;
        }
        return valid;
    }

    protected void snackBar(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show();
    }


    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onStop() {
        super.onStop();

    }


    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}
