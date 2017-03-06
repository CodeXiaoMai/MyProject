
package com.xiaomai.myproject.activity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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

public class SdCardSelectActivity extends BaseActivity implements View.OnClickListener {

    private TextView tv_sd_card;

    private SparseArray<String> sparseArray;

    private TextView tv_primary_storage;

    private LinearLayout ll_sd_card;

    private LinearLayout ll_primary_storage;

    private ImageView iv_primary_storage;

    private ImageView iv_sd_card;

    private String mPath;

    private Button bt_sd_card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentLayout() {
        return R.layout.activity_sd_card_check;
    }

    @Override
    protected void initViews() {
        super.initViews();
        setTitle("SD卡选择");
        // 当Sdk版本大于等于4.4时，若要向Sd卡中写入数据，只能向Android/data/[包名]/目录中写入，
        // 调用getExternalFilesDirs();系统自动创建该目录，app没有权限创建。
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getExternalFilesDirs(null);
        }
        ll_sd_card = (LinearLayout) findViewById(R.id.ll_sd_card);
        ll_primary_storage = (LinearLayout) findViewById(R.id.ll_primary_storage);
        tv_primary_storage = (TextView) findViewById(R.id.tv_primary_storage);
        tv_sd_card = (TextView) findViewById(R.id.tv_sd_card);
        iv_primary_storage = (ImageView) findViewById(R.id.iv_primary_storage);
        iv_sd_card = (ImageView) findViewById(R.id.iv_sd_card);
        bt_sd_card = (Button) findViewById(R.id.bt_sd_card);
        bt_sd_card.setOnClickListener(this);
        sparseArray = scanStorage();
        if (sparseArray == null || sparseArray.size() <= 0) {
            return;
        }
        mPath = sparseArray.get(0);
        ll_primary_storage.setOnClickListener(this);
        if (sparseArray.size() >= 2) {
            ll_sd_card.setVisibility(View.VISIBLE);
            ll_sd_card.setOnClickListener(this);
        }
        for (int i = 0; i < sparseArray.size(); i++) {
            final int finalI = i;
            Observable.create(new Observable.OnSubscribe<String>() {
                @Override
                public void call(Subscriber<? super String> subscriber) {
                    subscriber.onStart();
                    subscriber.onNext("剩余:"
                            + FileUtils.formatSize(
                                    FileUtils.getDirAvailableSize(sparseArray.get(finalI)))
                            + "/总量:" + FileUtils.formatSize(
                                    FileUtils.getDirTotalSize(sparseArray.get(finalI))));
                    subscriber.onCompleted();
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<String>() {
                        @Override
                        public void call(String s) {
                            if (finalI == 0) {
                                tv_primary_storage.setText(s);
                            } else if (finalI == 1) {
                                tv_sd_card.setText(s);
                            }
                        }
                    });
        }
    }

    @Override
    protected int getCodeResId() {
        return R.string.code_sdcard_select;
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_primary_storage:
                iv_primary_storage.setVisibility(View.VISIBLE);
                iv_sd_card.setVisibility(View.GONE);
                mPath = sparseArray.get(0);
                break;
            case R.id.ll_sd_card:
                iv_primary_storage.setVisibility(View.GONE);
                iv_sd_card.setVisibility(View.VISIBLE);
                mPath = sparseArray.get(1);
                break;
            case R.id.bt_sd_card:
                copyFile();
                break;
        }
        MyToast.show(mContext, mPath);
    }

    private void copyFile() {
        Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                boolean copy = FileUtils.copy(Environment.getExternalStorageDirectory().getPath() + "/1.apk",
                        mPath + "/Android/data/" + getPackageName() + "/files/1.apk", true);
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
}
