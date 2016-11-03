package com.xiaomai.myproject.nohttpdemo.activity.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by XiaoMai on 2016/11/3 18:43.
 */
public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(this, WelcomeActivity.class));
        finish();
    }
}
