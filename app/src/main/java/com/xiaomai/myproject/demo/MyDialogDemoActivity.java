package com.xiaomai.myproject.demo;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;

import com.xiaomai.myproject.view.MyDialog;
import com.xiaomai.myproject.view.MyToast;

/**
 * Created by XiaoMai on 2016/9/4.
 */
public class MyDialogDemoActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyDialog.Builder builder = new MyDialog.Builder(this);
        builder
                .setTitle("标题")
                .setMessage("这是内容")
                .setCancelable(true)
                .setOnNeagtiveButtonClickListener(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MyToast.show(getApplicationContext(), "取消了");
                        dialogInterface.dismiss();
                    }
                })
                .setOnPositiveButtonClickListener(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MyToast.show(getApplicationContext(), "确定了");
                        dialogInterface.dismiss();
                    }
                }).create().show();
    }
}
