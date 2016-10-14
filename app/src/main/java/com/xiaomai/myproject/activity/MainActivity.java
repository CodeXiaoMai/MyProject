package com.xiaomai.myproject.activity;

import android.content.Intent;
import android.os.Bundle;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.base.BaseActivity;
import com.xiaomai.myproject.demo.VitamioDemoActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(mContext, VitamioDemoActivity.class);
        startActivity(intent);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected int getContentLayout() {
        return R.layout.act_main;
    }
}
