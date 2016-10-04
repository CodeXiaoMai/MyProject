package com.xiaomai.myproject.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.fragment.LazyFragmentOne;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XiaoMai on 2016/10/4.
 */
public class FragmentActivityDemoActivity extends FragmentActivity {

    private ViewPager mViewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_viewpager);
        initViews();
    }

    private void initViews() {
        mViewPager = (ViewPager) findViewById(R.id.act_viewpager_viewpager);
        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
    }

    class MyPagerAdapter extends FragmentPagerAdapter{

        private List<Fragment> list;

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
            list = new ArrayList<>();
            list.add(new LazyFragmentOne());
            list.add(new LazyFragmentOne());
            list.add(new LazyFragmentOne());
            list.add(new LazyFragmentOne());
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //do nothing
        }
    }
}
