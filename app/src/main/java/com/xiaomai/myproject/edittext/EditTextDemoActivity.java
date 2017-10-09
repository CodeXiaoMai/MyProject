package com.xiaomai.myproject.edittext;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.base.BaseActivity;

/**
 * Created by XiaoMai on 2017/8/23.
 */

public class EditTextDemoActivity extends BaseActivity {
    private TextView mTvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initViews() {
        super.initViews();
        mTvContent = (TextView) findViewById(R.id.tv_content);
        mTvContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(mContext, EditActivity.class), 0);
            }
        });
    }

    @Override
    protected int getContentLayout() {
        return R.layout.activity_edit_text;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String content = data.getStringExtra("content");
        mTvContent.setText(content);
    }
}
