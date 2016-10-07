package com.xiaomai.myproject.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by XiaoMai on 2016/10/7 17:23.
 */
public class LoopViewPagerAdapter extends PagerAdapter {

    private List<ImageView> mList;

    private int[] mImageIds;

    public LoopViewPagerAdapter(List<ImageView> mList, int[] mImageIds) {
        this.mList = mList;
        this.mImageIds = mImageIds;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (position == 0) {
            mList.get(position).setImageResource(mImageIds[2]);
        } else if (position == (mList.size() - 1)) {
            mList.get(position).setImageResource(mImageIds[0]);
        } else {
            mList.get(position).setImageResource(mImageIds[position - 1]);
        }
        container.addView(mList.get(position));
        return mList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mList.get(position));
    }
}
