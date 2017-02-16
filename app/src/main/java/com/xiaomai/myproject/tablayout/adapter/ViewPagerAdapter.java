
package com.xiaomai.myproject.tablayout.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.xiaomai.myproject.tablayout.fragment.MyFragment;

import java.util.List;

/**
 * Created by XiaoMai on 2017/2/16 17:17.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    private List<MyFragment> fragments;

    public ViewPagerAdapter(FragmentManager fm, List<MyFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments == null ? 0 : fragments.size();
    }

    /**
     * 得到页面的标题
     * 
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return fragments.get(position).getArguments().getString(MyFragment.TITLE);
    }

}
