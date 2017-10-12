package com.xiaomai.myproject.capturing;

import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.xiaomai.myproject.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by XiaoMai on 2017/10/9.
 */

public class ControlCameraActivity extends AppCompatActivity {
    private static final String TAG = "ControlCameraActivity";
    private Preview mPreview;
    private Camera mCamera;
    private Button mButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_control_camera);
        mPreview = (Preview) findViewById(R.id.preview_view);
        mPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                success = false;
            }
        });
        
        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCamera != null) {
                    mCamera.takePicture(null, null, mPictureCallBack);
                }
            }
        });
    }

    private Camera.PictureCallback mPictureCallBack = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            File pictureFile = null;
            try {
                pictureFile = getOutPutMediaFile();
            } catch (IOException e) {
            }

            if (pictureFile == null) {
                Log.d(TAG, "onPictureTaken: Error creating media file, check storage permissions:");
                return;
            }

            try {
                FileOutputStream fos = new FileOutputStream(pictureFile);
                fos.write(data);
                fos.close();
            } catch (FileNotFoundException e) {
                Log.d(TAG, "onPictureTaken: File not found:" + e.getMessage());
            } catch (IOException e) {
                Log.d(TAG, "onPictureTaken: Error accessing file; " + e.getMessage());
            }
        }
    };

    boolean success = false;

    @Override
    protected void onResume() {
        super.onResume();
        boolean safeCameraOpen = safeCameraOpen();
        if (safeCameraOpen) {
            // 旋转方向
            mCamera.setDisplayOrientation(90);
            Camera.Parameters parameters = mCamera.getParameters();
            List<String> focusModes = parameters.getSupportedFocusModes();
            if (focusModes.contains(Camera.Parameters.FOCUS_MODE_AUTO)) {
                parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
            }
            List<Camera.Size> pictureSizes = parameters.getSupportedPictureSizes();
            for (Camera.Size pictureSize : pictureSizes) {
                Log.e(TAG, "pictureSize: " + pictureSize.width + "*" + pictureSize.height);
            }
            parameters.setPictureSize(pictureSizes.get(0).width, pictureSizes.get(0).height);

            List<Camera.Size> previewSizes = parameters.getSupportedPreviewSizes();
            for (Camera.Size pictureSize : pictureSizes) {
                Log.e(TAG, "previewSize: " + pictureSize.width + "*" + pictureSize.height);
            }
            parameters.setPreviewSize(previewSizes.get(0).width, previewSizes.get(0).height);

            List<String> sceneModes = parameters.getSupportedSceneModes();
            for (String sceneMode : sceneModes) {
                Log.e(TAG, "sceneMode: " + sceneMode);
            }

            if (sceneModes.contains(Camera.Parameters.SCENE_MODE_AUTO)) {
                parameters.setSceneMode(Camera.Parameters.SCENE_MODE_AUTO);
            }
            mCamera.setParameters(parameters);



            Observable.interval(1000, TimeUnit.MILLISECONDS)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<Long>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(Long aLong) {
                            if (success) return;
                            mCamera.autoFocus(new Camera.AutoFocusCallback() {
                                @Override
                                public void onAutoFocus(boolean b, Camera camera) {
                                    success = b;
                                }
                            });
                        }
                    });

            mPreview.setCamera(mCamera);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        releaseCameraAndPreview();
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

    public File getOutPutMediaFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        return File.createTempFile(
                imageName,
                ".jpg",
                storageDir
        );
    }
}
