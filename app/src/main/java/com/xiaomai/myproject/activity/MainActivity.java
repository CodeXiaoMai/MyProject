package com.xiaomai.myproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.base.BaseActivity;
import com.xiaomai.myproject.demo.VitamioWithoutControllerDemoActivity;

public class MainActivity extends BaseActivity {

    private Button mButton;

    private View mView;

    private View mShow;

    private View mYing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setFlags(flag,flag);
        super.onCreate(savedInstanceState);
        dissMissProgressDialog();
//        startActivity(new Intent(this,WebViewJsDemoActivity.class));
    }

    @Override
    protected void initViews() {
        mButton = (Button) findViewById(R.id.bt_enter);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, VitamioWithoutControllerDemoActivity.class));
            }
        });
        final TranslateAnimation mTranslateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 1f, Animation.RELATIVE_TO_SELF, 0
        );
        mTranslateAnimation.setDuration(1000);
        mTranslateAnimation.setFillAfter(true);
        mView = findViewById(R.id.textview);

        mShow = findViewById(R.id.show);

        mYing = findViewById(R.id.yin);
        mYing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mView.offsetTopAndBottom(-mView.getHeight());
                mView.startAnimation(mTranslateAnimation);
            }
        });

        final TranslateAnimation translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, -1f, Animation.RELATIVE_TO_SELF, 0
        );
        translateAnimation.setDuration(1000);
        translateAnimation.setFillAfter(true);

        mShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mView.offsetTopAndBottom(mView.getHeight());
                mView.startAnimation(translateAnimation);
            }
        });
    }

    @Override
    protected boolean isWithoutToolbar() {
        return true;
    }

    @Override
    protected int getContentLayout() {
        return R.layout.act_main;
    }
}
