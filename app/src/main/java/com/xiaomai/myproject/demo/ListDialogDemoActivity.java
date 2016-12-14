
package com.xiaomai.myproject.demo;

import android.app.Activity;
import android.os.Bundle;

import com.xiaomai.myproject.view.ListDialog;
import com.xiaomai.myproject.view.MyToast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XiaoMai on 2016/12/14 13:37.
 */
public class ListDialogDemoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        new ListDialog.Builder(this, list)
                .setOnItemClickListener(new ListDialog.onItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        MyToast.show(getApplicationContext(), position + "");
                    }
                }).create().show();
    }
}
