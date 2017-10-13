package com.xiaomai.myproject.capturing;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.view.MyToast;

import java.io.ByteArrayOutputStream;
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
    private boolean mIsSuccess = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        int memClass = activityManager.getMemoryClass();//64，以m为单位
        Log.e(TAG, "memClass: " + memClass);
        setContentView(R.layout.activity_control_camera);
        mPreview = (Preview) findViewById(R.id.preview_view);
        mPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIsSuccess = false;
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

        findViewById(R.id.button_flash).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCamera != null) {
                    // 开启闪光灯
                    Camera.Parameters parameters = mCamera.getParameters();
                    parameters.setFlashMode(Camera.Parameters.FLASH_MODE_ON);
                    mCamera.setParameters(parameters);
                }
            }
        });
    }

    private Camera.PictureCallback mPictureCallBack = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            // 拍照后需重新开始预览
            mCamera.startPreview();
            // 放到Io线程中操作
            File pictureFile = null;
            try {
                pictureFile = getOutPutMediaFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (pictureFile == null) {
                MyToast.show(getApplicationContext(), "拍照失败！");
                Log.d(TAG, "onPictureTaken: Error creating media file, check storage permissions:");
                return;
            }

            Bitmap resource = BitmapFactory.decodeByteArray(data, 0, data.length);
            Matrix matrix = new Matrix();
            matrix.setRotate(90f);

            Bitmap bitmap = Bitmap.createBitmap(resource, 0, 0, resource.getWidth(), resource.getHeight(), matrix, true);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

            try {
                FileOutputStream fos = new FileOutputStream(pictureFile);
                fos.write(byteArrayOutputStream.toByteArray());
                fos.close();
                MyToast.show(getApplicationContext(), "照片保存到" + pictureFile.getAbsolutePath());
            } catch (FileNotFoundException e) {
                MyToast.show(getApplicationContext(), "保存照片失败");
                Log.d(TAG, "onPictureTaken: File not found:" + e.getMessage());
            } catch (IOException e) {
                MyToast.show(getApplicationContext(), "保存照片失败");
                Log.d(TAG, "onPictureTaken: Error accessing file; " + e.getMessage());
            }


        }
    };


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
                if (pictureSize.width * pictureSize.height <= 8 * 1024 * 1024) {
                    parameters.setPictureSize(pictureSize.width, pictureSize.height);
                    Log.e(TAG, "pictureSize: " + pictureSize.width + "*" + pictureSize.height);
                    break;
                }
            }

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
                            if (mIsSuccess) return;
                            mCamera.autoFocus(new Camera.AutoFocusCallback() {
                                @Override
                                public void onAutoFocus(boolean b, Camera camera) {
                                    mIsSuccess = b;
                                    if (mIsSuccess) {
                                        MyToast.show(getApplicationContext(), "聚焦成功");
                                    }
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
