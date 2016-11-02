package com.xiaomai.myproject.demo;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.adapter.LoopViewPagerAdapter2;
import com.xiaomai.myproject.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XiaoMai on 2016/10/9 17:44.
 *
 * 原理：在Adapter中将getCount设置为无限大
 */
public class LoopViewPagerDemo2Activity extends BaseActivity {

    /**
     * 更新图片的时间
     */
    private static final int UPDATE_TIME = 3 * 1000;

    /**
     * 图片资源集合
     */
    private int[] mImageIds;

    /**
     * 图片集合
     */
    private List<ImageView> mImageList;

    /**
     * 当前广告的index
     */
    private int mCurrentPageIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initVariables() {
        super.initVariables();
        /**
         * 初始化图片的资源id
         */
        mImageIds = new int[]{
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,
                R.drawable.my_toast,
                R.drawable.my_toast,
                R.drawable.my_toast,
                R.drawable.my_toast};
        /**
         * 添加ImageView
         */
        mImageList = new ArrayList<>();
        ViewGroup.LayoutParams layoutParams =
                new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
        for (int i = 0; i < mImageIds.length; i++) {
            ImageView imageView = new ImageView(mContext);
            imageView.setLayoutParams(layoutParams);
            mImageList.add(imageView);
        }
    }

    @Override
    protected void initViews() {
        /*
      指示Point
     */
        LinearLayout mLinearLayoutPonits = (LinearLayout) findViewById(R.id.activity_ll_container);
        ViewPager mViewPager = (ViewPager) findViewById(R.id.activity_loop_viewpager);
        LoopViewPagerAdapter2 mAdapter = new LoopViewPagerAdapter2(mImageList, mImageIds);
        mViewPager.setAdapter(mAdapter);
        int middle = mAdapter.getCount() / 2;
        mViewPager.setCurrentItem(middle - middle % mImageList.size());
    }

    @Override
    protected int getContentLayout() {
        return R.layout.act_loopviewpager;
    }
}
