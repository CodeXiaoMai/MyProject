
package com.xiaomai.myproject.nohttpdemo.activity.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.nohttpdemo.activity.StartActivity;
import com.yolanda.nohttp.PosterHandler;

/**
 * Created by XiaoMai on 2016/11/3 18:44.
 */
public class WelcomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        PosterHandler.getInstance().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(WelcomeActivity.this, StartActivity.class));
                finish();
            }
        }, 1000);
    }
}
