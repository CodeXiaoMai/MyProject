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

    private List<ImageView> mImageList;

    private int[] mImageIds;

    public LoopViewPagerAdapter(List<ImageView> mImageList, int[] mImageIds) {
        this.mImageList = mImageList;
        this.mImageIds = mImageIds;
    }

    @Override
    public int getCount() {
        return mImageList == null ? 0 : mImageList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (position == 0) {
            //  如果是第0个item就设置它的图片内容为最后一个图片的内容
            mImageList.get(position).setImageResource(mImageIds[mImageIds.length-1]);
        } else if (position == (mImageList.size() - 1)) {
            //  如果是最后一个item就设置它的图片内容为第一个图片的内容
            mImageList.get(position).setImageResource(mImageIds[0]);
        } else {
            //  这是正常的图片
            mImageList.get(position).setImageResource(mImageIds[position - 1]);
        }
        container.addView(mImageList.get(position));
        return mImageList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mImageList.get(position));
    }
}
