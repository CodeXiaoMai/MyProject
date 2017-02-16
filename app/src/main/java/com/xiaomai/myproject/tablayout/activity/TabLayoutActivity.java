
package com.xiaomai.myproject.tablayout.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.base.BaseActivity;
import com.xiaomai.myproject.tablayout.adapter.ViewPagerAdapter;
import com.xiaomai.myproject.tablayout.fragment.MyFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class TabLayoutActivity extends BaseActivity {

    @BindView(R.id.vp_tab_layout)
    ViewPager vpTabLayout;

    @BindView(R.id.tb_tab_layout)
    TabLayout tbTabLayout;

    private List<MyFragment> fragments;

    private ViewPagerAdapter mAdapter;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_tab_layout;
    }

    @Override
    protected void initViews() {
        super.initViews();
        fragments = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            fragments.add(MyFragment.newInstance("标题" + i, "内容" + i));
        }

        mAdapter = new ViewPagerAdapter(getSupportFragmentManager(), fragments);
        vpTabLayout.setAdapter(mAdapter);
        tbTabLayout.setupWithViewPager(vpTabLayout);
        // 设置固定的
        // tbTabLayout.setTabMode(TabLayout.MODE_FIXED);
        // 可滚动的
        tbTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    @Override
    protected int getCodeResId() {
        return R.string.code_tab_layout;
    }
}
