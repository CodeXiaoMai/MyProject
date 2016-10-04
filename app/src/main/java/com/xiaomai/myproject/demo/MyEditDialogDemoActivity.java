package com.xiaomai.myproject.demo;

import android.os.Bundle;

import com.xiaomai.myproject.base.BaseActivity;
import com.xiaomai.myproject.view.MyEditDialog;
import com.xiaomai.myproject.view.MyToast;

/**
 * Created by XiaoMai on 2016/9/5.
 */
public class MyEditDialogDemoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final MyEditDialog.Builder builder = new MyEditDialog.Builder(this);
        builder.setTitle("请输入您的名字")
                .setHint("不能有空格")
                .setInput("您叫什么")
                .setCancelable(false)
                .setOnPositiveButtonClickListener(new MyEditDialog.Builder.onPositiveButtonClickListener() {
                    @Override
                    public void onClick(MyEditDialog dialog, String input) {
                        MyToast.show(MyEditDialogDemoActivity.this, input);
                    }
                })
                .onCreate().show();
    }

    @Override
    protected void initViews() {
    }

    @Override
    protected int getContentLayout() {
        return 0;
    }
}
