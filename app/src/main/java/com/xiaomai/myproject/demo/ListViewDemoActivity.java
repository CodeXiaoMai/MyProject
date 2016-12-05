package com.xiaomai.myproject.demo;

import android.view.View;
import android.widget.TextView;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.base.BaseActivity;
import com.xiaomai.myproject.utils.ViewUtils;

/**
 * Created by XiaoMai on 2016/12/2 15:48.
 */
public class ListViewDemoActivity extends BaseActivity {
    @Override
    protected void initViews() {
        TextView textView = (TextView) findViewById(R.id.textview);
        ViewUtils.setSolidCorner(this, textView, R.drawable.circle_normal, R.drawable.circle_select, R.color.colorAccent, 0, true);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected int getContentLayout() {
        return R.layout.listview_demo;
    }
}
