package com.xiaomai.myproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.base.BaseActivity;
import com.xiaomai.myproject.demo.VitamioWithoutControllerDemoActivity;

public class MainActivity extends BaseActivity {

    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dissMissProgressDialog();
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
    }

    @Override
    protected int getContentLayout() {
        return R.layout.act_main;
    }
}
