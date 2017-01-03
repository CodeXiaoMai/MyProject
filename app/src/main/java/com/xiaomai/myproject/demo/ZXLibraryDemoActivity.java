
package com.xiaomai.myproject.demo;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.xiaomai.myproject.R;
import com.xiaomai.myproject.base.BaseActivity;
import com.xiaomai.myproject.utils.ImageUtil;
import com.xiaomai.myproject.view.MyToast;

import java.io.IOException;

/**
 * Created by XiaoMai on 2017/1/3 13:22.</br>
 * 这是二维码扫描库的使用Demo
 */
public class ZXLibraryDemoActivity extends BaseActivity {

    private static final String TAG = "ZXLibraryDemoActivity";

    private static final int REQUEST_CODE_CAMERA = 1;

    private static final int REQUEST_CODE_PICTURE = 2;

    private Button mBtnScanner;

    private Button mBtnPicture;

    private Button mBtnCustom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBtnScanner = (Button) findViewById(R.id.btn_scanner);
        mBtnPicture = (Button) findViewById(R.id.btn_picture);
        mBtnCustom = (Button) findViewById(R.id.btn_custom_view);
        mBtnScanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 打开二维码扫描页面
                // 加入权限<uses-permission
                // android:name="android.permission.CAMERA"/>
                startActivityForResult(
                        new Intent(ZXLibraryDemoActivity.this, CaptureActivity.class),
                        REQUEST_CODE_CAMERA);
            }
        });
        mBtnPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 调用系统API打开图库
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_PICTURE);
            }
        });
        mBtnCustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 自定义页面
                startActivityForResult(
                        new Intent(ZXLibraryDemoActivity.this, ZXLibraryCustomDemoActivity.class), REQUEST_CODE_CAMERA);
            }
        });
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected int getContentLayout() {
        return R.layout.activity_zxing;
    }

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
                                    Toast.makeText(ZXLibraryDemoActivity.this, "解析结果:" + result,
                                            Toast.LENGTH_LONG).show();
                                }

                                @Override
                                public void onAnalyzeFailed() {
                                    Toast.makeText(ZXLibraryDemoActivity.this, "解析二维码失败",
                                            Toast.LENGTH_LONG).show();
                                }
                            });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
