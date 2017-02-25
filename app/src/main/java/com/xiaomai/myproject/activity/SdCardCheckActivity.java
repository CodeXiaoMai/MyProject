package com.xiaomai.myproject.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.widget.TextView;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.base.BaseActivity;

import java.lang.reflect.InvocationTargetException;

public class SdCardCheckActivity extends BaseActivity {

    private TextView tv_sd_card;

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
        setTitle("SD卡检测");
        tv_sd_card = (TextView) findViewById(R.id.tv_sd_card);
        StringBuilder stringBuilder = new StringBuilder();
        StorageManager storageManager = (StorageManager) getSystemService(Context.STORAGE_SERVICE);
        try {
            String[] paths = (String[]) storageManager.getClass().getMethod("getVolumePaths").invoke(storageManager);
            for (int i = 0; i < paths.length; i++) {
                String status = (String) storageManager.getClass().getMethod("getVolumeState", String.class).invoke(storageManager, paths[i]);
                if (Environment.MEDIA_MOUNTED.equals(status)) {
                    stringBuilder.append(paths[i] + "\n");
                }
            }
            tv_sd_card.setText(stringBuilder.toString());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
