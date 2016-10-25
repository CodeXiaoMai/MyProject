package com.xiaomai.myproject.demo;

import android.view.View;
import android.widget.Button;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.base.BaseActivity;

/**
 * Created by XiaoMai on 2016/10/25 19:01.
 */
public class NoHttpDownloadDemoActivity extends BaseActivity {

    private Button mButtonStart;

    private Button mButtonStop;

    @Override
    protected void initViews() {
        mButtonStart = (Button) findViewById(R.id.start);
        mButtonStop = (Button) findViewById(R.id.stop);

        mButtonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mButtonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected int getContentLayout() {
        return R.layout.act_nohttp_download;
    }
}
