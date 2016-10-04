package com.xiaomai.myproject.demo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.base.BaseActivity;
import com.xiaomai.myproject.view.NumberProgressBar;

/**
 * Created by XiaoMai on 2016/9/29.
 */
public class NumberProgressBarDemoActivity extends BaseActivity {

    private NumberProgressBar mNumberProgressBar;

    private Button mButtonStart;

    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initVariables() {
        super.initVariables();
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                mNumberProgressBar.setProgress(msg.what);
            }
        };
    }

    @Override
    protected void initViews() {
        mNumberProgressBar = (NumberProgressBar) findViewById(R.id.activity_number_progress);
        mButtonStart = (Button) findViewById(R.id.activity_number_progress_button_start);
        mButtonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){
                    @Override
                    public void run() {
                        int progress = 0;
                        while (progress < 100){
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            mHandler.sendEmptyMessage(++progress);
                        }
                    }
                }.start();
            }
        });
    }

    @Override
    protected int getContentLayout() {
        return R.layout.act_number_progressbar;
    }
}
