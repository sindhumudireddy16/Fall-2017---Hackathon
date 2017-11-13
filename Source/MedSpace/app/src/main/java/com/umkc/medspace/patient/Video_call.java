package com.umkc.medspace.patient;

import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.umkc.medspace.BaseAppcompatActivity;
import com.umkc.medspace.R;
import com.umkc.medspace.activity.Preview;

import java.io.IOException;

public class Video_call extends BaseAppcompatActivity {

    private static final int PERMISSION_CAMERABACK_CONSTANT = 1001;
    private String name, mobile;
    private FrameLayout cameraView;
    private Preview preview;
    private FloatingActionButton callEnd;
    private ImageView rotate;
    private int currentCameraId;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_video_call;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolbar_title = (TextView) findViewById(R.id.context);
        toolbar_back = (ImageView) findViewById(R.id.back);
        toolbar_back.setVisibility(View.VISIBLE);

        name = getIntent().getExtras().getString("name", "");
        mobile = getIntent().getExtras().getString("mobile", "");

        toolbar_title.setText("Calling " + name.toUpperCase());

        toolbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        preview = new Preview(this);
        cameraView = (FrameLayout) findViewById(R.id.camera);
        callEnd = (FloatingActionButton) findViewById(R.id.callEnd);
        rotate = (ImageView) findViewById(R.id.rotate);

        cameraView.addView(preview);

        callEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        rotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preview.camera.stopPreview();
                preview.camera.setPreviewCallback(null);
                preview.getHolder().removeCallback(preview);
                preview.camera.release();
                preview.camera = null;
                if (currentCameraId == Camera.CameraInfo.CAMERA_FACING_BACK) {
                    currentCameraId = Camera.CameraInfo.CAMERA_FACING_FRONT;
                } else {
                    currentCameraId = Camera.CameraInfo.CAMERA_FACING_BACK;
                }
                preview.camera = Camera.open(currentCameraId);

                preview.setCameraDisplayOrientation(Video_call.this, currentCameraId, preview.camera);
                try {
                    preview.camera.setPreviewDisplay(preview.getHolder());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                preview.camera.startPreview();
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        try {
            preview.camera.stopPreview();
            preview.camera.setPreviewCallback(null);
            preview.getHolder().removeCallback(preview);
            preview.camera.release();
            preview.camera = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
