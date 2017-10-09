package com.xiaomai.myproject.capturing;

import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.xiaomai.myproject.R;

/**
 * Created by XiaoMai on 2017/10/9.
 */

public class ControlCameraActivity extends AppCompatActivity {
    private static final String TAG = "ControlCameraActivity";
    private Preview mPreview;
    private Camera mCamera;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_control_camera);
        mPreview = (Preview) findViewById(R.id.preview_view);
    }

    @Override
    protected void onResume() {
        super.onResume();
        boolean safeCameraOpen = safeCameraOpen();
        if (safeCameraOpen) {
            mPreview.setCamera(mCamera);
        }
    }

    private boolean safeCameraOpen() {
        boolean qOpened = false;

        try {
            releaseCameraAndPreview();
            mCamera = Camera.open();
            qOpened = (mCamera != null);
        } catch (Exception e) {
            Log.e(TAG, "safeCameraOpen: ");
            e.printStackTrace();
        }

        return qOpened;
    }

    private void releaseCameraAndPreview() {
        mPreview.setCamera(null);
        if (mCamera != null) {
            mCamera.release();
            mCamera = null;
        }
    }
}
