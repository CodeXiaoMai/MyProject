
package com.xiaomai.myproject.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.base.BaseActivity;
import com.xiaomai.myproject.utils.FileUtils;
import com.xiaomai.myproject.view.MyToast;

import java.lang.reflect.InvocationTargetException;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class RuntimePermissionActivity extends BaseActivity implements View.OnClickListener {

    private static final int REQUEST_CODE_CALL = 0x0001;

    private static final int REQUEST_CODE_FILE = 0x0002;

    private Button bt_permission_call;

    private Button bt_permission_file;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_runtime_permission;
    }

    @Override
    protected void initViews() {
        super.initViews();
        bt_permission_call = (Button) findViewById(R.id.bt_permission_call);
        bt_permission_file = (Button) findViewById(R.id.bt_permission_file);
        bt_permission_call.setOnClickListener(this);
        bt_permission_file.setOnClickListener(this);
    }

    @Override
    protected int getCodeResId() {
        return R.string.code_runtime_permission;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_permission_call:
                if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[] {
                            Manifest.permission.CALL_PHONE
                    }, REQUEST_CODE_CALL);
                } else {
                    call();
                }
                break;
            case R.id.bt_permission_file:
                if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[] {
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    }, REQUEST_CODE_FILE);
                } else {
                    copyFile();
                }
                break;
        }
    }
    private void copyFile() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getExternalFilesDirs(null);
        }
        Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                boolean copy = FileUtils.copy(Environment.getExternalStorageDirectory().getPath() + "/1.jpg",
                        scanStorage().get(1) +  "/Android/data/" + getPackageName() + "/files/1.jpg", true);
                subscriber.onNext(copy);
                subscriber.onCompleted();
            }
        }).
                subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean bool) {
                        if (bool) MyToast.show(mContext, "拷贝成功");
                        else MyToast.show(mContext, "拷贝失败");
                    }
                });
    }

    /**
     * 扫描存储设备
     *
     * @return
     */
    private SparseArray<String> scanStorage() {
        SparseArray<String> sparseArray = new SparseArray<>();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            // 这种方式获取到的都是Android/data/[包名]目录/files
//            File[] externalFilesDirs = getExternalFilesDirs(null);
//            if (externalFilesDirs == null) {
//                return sparseArray;
//            } else {
//                for (int i = 0; i < externalFilesDirs.length; i++) {
//                    sparseArray.append(i, externalFilesDirs[i].getAbsolutePath());
//                    // /storage/emulated/0/Android/data/com.xiaomai.myproject/files
//                    // /storage/extSdCard/Android/data/com.xiaomai.myproject/files
//                }
//            }
//            return sparseArray;
//        }

        final StorageManager storageManager = (StorageManager) getSystemService(
                Context.STORAGE_SERVICE);
        try {
            String[] paths = (String[]) storageManager.getClass().getMethod("getVolumePaths")
                    .invoke(storageManager);
            for (int i = 0; i < paths.length; i++) {
                String status = (String) storageManager.getClass()
                        .getMethod("getVolumeState", String.class).invoke(storageManager, paths[i]);
                if (Environment.MEDIA_MOUNTED.equals(status)) {
                    sparseArray.append(i, paths[i]);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return sparseArray;
    }

    private void call() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:10086"));
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
            @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_CALL:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    call();
                } else {
                    MyToast.show(this, "你拒绝了我！");
                }
                break;
            case REQUEST_CODE_FILE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    copyFile();
                } else {
                    MyToast.show(this, "你拒绝了我！");
                }
                break;
        }
    }
}
