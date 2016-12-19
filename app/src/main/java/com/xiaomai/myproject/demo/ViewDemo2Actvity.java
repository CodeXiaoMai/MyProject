
package com.xiaomai.myproject.demo;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.base.BaseActivity;
import com.xiaomai.myproject.view.ScrollTextView;

/**
 * Created by XiaoMai on 2016/12/16 10:39.
 */
public class ViewDemo2Actvity extends BaseActivity {

    private static final String TAG = "ViewDemo2Actvity";

    private ScrollTextView scroll;

    @Override
    protected void initViews() {
        scroll = (ScrollTextView) findViewById(R.id.scrollView);
        scroll.smoothScrollTo(-300, 0, 10000);
    }


    @Override
    protected int getContentLayout() {
        return R.layout.act_view;
    }
}
