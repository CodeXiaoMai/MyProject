
package com.xiaomai.myproject.demo;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.Result;
import com.google.zxing.client.result.ResultParser;
import com.uuzuche.lib_zxing.activity.CaptureFragment;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.xiaomai.myproject.R;
import com.xiaomai.myproject.base.BaseActivity;
import com.xiaomai.myproject.utils.BitmapDecoder;
import com.xiaomai.myproject.utils.FileUtils;
import com.xiaomai.myproject.utils.ImageUtil;
import com.xiaomai.myproject.view.MyToast;

import java.io.File;
import java.io.IOException;

/**
 * Created by XiaoMai on 2017/1/3 14:25.
 */
public class ZXLibraryCustomDemoActivity extends BaseActivity {

    private static final int REQUEST_CODE_CAMERA = 1;

    private static final int REQUEST_CODE_PICTURE = 2;

    private static final int REQUEST_CODE_TAKE_PICTURE = 3;

    private Button mBtnImage;

    private Button mBtnPicture;

    private Button mBtnLight;

    private boolean mBoolLight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CaptureFragment captureFragment = new CaptureFragment();
        // 为二维码扫描界面设置定制化界面
        CodeUtils.setFragmentArgs(captureFragment, R.layout.custom_scanner_layout);
        captureFragment.setAnalyzeCallback(analyzeCallback);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_my_container, captureFragment).commit();
    }

    @Override
    protected void initViews() {
        mBtnImage = (Button) findViewById(R.id.btn_image);
        mBtnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 调用系统API打开图库
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_PICTURE);
            }
        });
        mBtnLight = (Button) findViewById(R.id.btn_light);
        // 控制闪光灯
        mBtnLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CodeUtils.isLightEnable(!mBoolLight);
                mBoolLight = !mBoolLight;
            }
        });
        mBtnPicture = (Button) findViewById(R.id.btn_picture);
        mBtnPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sdStatus = Environment.getExternalStorageState();
                if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
                    MyToast.show(ZXLibraryCustomDemoActivity.this, "请选择图片");
                    return;
                }
                File file = new File(FileUtils.getCacheRootPath());
                if (!file.exists()) {
                    file.mkdirs();
                }
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra("return-data", false);
                Uri imageUri = Uri.fromFile(new File(FileUtils.getCacheRootPath() + "temp.jpg"));
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
                intent.putExtra("noFaceDetection", true);
                startActivityForResult(intent, REQUEST_CODE_TAKE_PICTURE);
            }
        });
    }

    @Override
    protected int getContentLayout() {
        return R.layout.activity_zxing_custom;
    }

    /**
     * 二维码解析回调函数
     */
    CodeUtils.AnalyzeCallback analyzeCallback = new CodeUtils.AnalyzeCallback() {
        @Override
        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
            Intent resultIntent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_SUCCESS);
            bundle.putString(CodeUtils.RESULT_STRING, result);
            resultIntent.putExtras(bundle);
            setResult(RESULT_OK, resultIntent);
            finish();
        }

        @Override
        public void onAnalyzeFailed() {
            Intent resultIntent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_FAILED);
            bundle.putString(CodeUtils.RESULT_STRING, "");
            resultIntent.putExtras(bundle);
            setResult(RESULT_OK, resultIntent);
            finish();
        }
    };

    /**
     * 接收扫描结果
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_CAMERA) {
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    MyToast.show(this, result);
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    MyToast.show(this, "解析失败");
                }
            }
        } else if (requestCode == REQUEST_CODE_PICTURE) {
            if (data != null) {
                Uri uri = data.getData();
                ContentResolver contentResolver = getContentResolver();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri);
                    CodeUtils.analyzeBitmap(ImageUtil.getImageAbsolutePath(this, uri),
                            new CodeUtils.AnalyzeCallback() {
                                @Override
                                public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                                    Toast.makeText(ZXLibraryCustomDemoActivity.this,
                                            "解析结果:" + result, Toast.LENGTH_LONG).show();
                                }

                                @Override
                                public void onAnalyzeFailed() {
                                    Toast.makeText(ZXLibraryCustomDemoActivity.this, "解析二维码失败",
                                            Toast.LENGTH_LONG).show();
                                }
                            });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else if (requestCode == REQUEST_CODE_TAKE_PICTURE) {

            Bitmap img = ImageUtil.getCompressedBitmap(FileUtils.getCacheRootPath() + "temp.jpg");
            BitmapDecoder decoder = new BitmapDecoder(ZXLibraryCustomDemoActivity.this);
            Result result = decoder.getRawResult(img);
            if (result != null) {
                String scanStr = ResultParser.parseResult(result).toString();
                MyToast.show(ZXLibraryCustomDemoActivity.this, scanStr);
            } else {
                MyToast.show(ZXLibraryCustomDemoActivity.this, "解析二维码失败");
            }
        }

    }
}
