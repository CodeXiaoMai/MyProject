package com.xiaomai.myproject.launchmode;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by HSEDU on 2017/3/15 11:12.
 */

public class ActivityB extends AppCompatActivity {

    private static final String TAG = "ActivityB";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate: " + getTaskId());
        startActivity(new Intent(this, ActivityC.class));
    }
}
