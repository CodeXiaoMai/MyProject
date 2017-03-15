package com.xiaomai.myproject.launchmode;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by HSEDU on 2017/3/15 11:12.
 */

public class ActivityC extends AppCompatActivity {

    private static final String TAG = "ActivityC";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate: " + getTaskId());
    }
}
