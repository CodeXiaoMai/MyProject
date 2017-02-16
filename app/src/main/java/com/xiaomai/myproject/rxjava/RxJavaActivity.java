
package com.xiaomai.myproject.rxjava;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.base.BaseActivity;

public class RxJavaActivity extends BaseActivity implements View.OnClickListener {

    private Button bt_rx_java_simple;

    private Button bt_rx_java_complex;

    private Button bt_rx_java_scheduler;

    private Button bt_rx_java_flat;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_rx_java;
    }

    @Override
    protected void initViews() {
        super.initViews();
        setTitle("RxJava");
        setOnMoreClickListener(null);
        bt_rx_java_simple = (Button) findViewById(R.id.bt_rx_java_simple);
        bt_rx_java_complex = (Button) findViewById(R.id.bt_rx_java_complex);
        bt_rx_java_scheduler = (Button) findViewById(R.id.bt_rx_java_scheduler);
        bt_rx_java_flat = (Button) findViewById(R.id.bt_rx_java_flat);
        bt_rx_java_flat.setOnClickListener(this);
        bt_rx_java_scheduler.setOnClickListener(this);
        bt_rx_java_simple.setOnClickListener(this);
        bt_rx_java_complex.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_rx_java_simple:
                startActivity(new Intent(mContext, RxJavaSimpleActivity.class));
                break;
            case R.id.bt_rx_java_complex:
                startActivity(new Intent(mContext, RxJavaComplexActivity.class));
                break;
            case R.id.bt_rx_java_scheduler:
                startActivity(new Intent(mContext, RxJavaSchedulerActivity.class));
                break;
            case R.id.bt_rx_java_flat:
                startActivity(new Intent(mContext, RxJavaFlatActivity.class));
                break;
        }
    }
}
