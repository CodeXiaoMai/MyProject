package com.xiaomai.myproject.demo;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.adapter.LoopViewPagerAdapter;
import com.xiaomai.myproject.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XiaoMai on 2016/10/7 17:19.
 */
public class LoopViewPagerDemoActivity extends BaseActivity {

    private ViewPager mViewPager;

    private LoopViewPagerAdapter mLoopViewPager;

    private int[] mImageIds;

    private List<ImageView> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initVariables() {
        super.initVariables();
        mImageIds =new int[] {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.drawable.my_toast};
        mList = new ArrayList<>();
        int pagerCount = mImageIds.length + 2;
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        for (int i = 0; i < pagerCount; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(layoutParams);
//            imageView.setImageResource(mImageIds[i]);
            mList.add(imageView);
        }
    }

    @Override
    protected void initViews() {
        mViewPager = (ViewPager) findViewById(R.id.activity_loop_viewpager);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int pageIndex = position ;
                if (position == 0) {
                    // 当视图在第一个时，将页面号设置为图片的最后一张。
                    pageIndex = mImageIds.length;
                } else if (position == mImageIds.length + 1) {
                    // 当视图在最后一个是,将页面号设置为图片的第一张。
                    pageIndex = 1;
                }
                if (position != pageIndex) {
                    mViewPager.setCurrentItem(pageIndex, false);
                    return;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mLoopViewPager = new LoopViewPagerAdapter(mList, mImageIds);
        mViewPager.setAdapter(mLoopViewPager);
        mViewPager.setCurrentItem(1);
    }

    @Override
    protected int getContentLayout() {
        return R.layout.act_loopviewpager;
    }
}
