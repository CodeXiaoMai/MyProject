package com.xiaomai.myproject.demo;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by XiaoMai on 2017/5/23.
 */

public class FragmentStackDemo extends BaseActivity {
    @BindView(R.id.container)
    FrameLayout container;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_fragment_stack;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);

        getFragmentManager().beginTransaction().add(R.id.container, StackFragment.newInstance()).commit();
    }
}
