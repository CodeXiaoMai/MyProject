package com.xiaomai.myproject.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by XiaoMai on 2016/10/9 17:53.
 */
public class LoopViewPagerAdapter2 extends PagerAdapter {

    private List<ImageView> mImageList;

    private int[] mImageIds;

    public LoopViewPagerAdapter2(List<ImageView> mImageList, int[] mImageIds) {
        this.mImageList = mImageList;
        this.mImageIds = mImageIds;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = mImageList.get(position % mImageList.size());
        imageView.setImageResource(mImageIds[position % mImageIds.length]);
        container.addView(imageView);
        return mImageList.get(position % mImageList.size());
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mImageList.get(position % mImageList.size()));
    }
}
