package com.xiaomai.myproject.demo;

import android.os.Bundle;
import android.os.Handler;
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
 * 实现原理：
 * 假设有3张图片，分别是1,2,3，那么就创建5张图片，这5张图片的顺序为：3,1,2,3,1，
 * 其中1,2,3为我们要实现滑动的图片，最左面的3和最右面的1是我们另外添加的图片，
 * 开始时，显示图片1，当图片向左滑动依次为1,2,3，当从第3张图片继续向左滑动，会出现我们多添加
 * 的图片1，这时，将当前的index设置为真正的图片1所在的位置。
 */
public class LoopViewPagerDemoActivity extends BaseActivity {

    private static final int UPDATE_TIME = 3 * 1000;
    private ViewPager mViewPager;

    private LoopViewPagerAdapter mLoopViewPager;

    /**
     * 图片资源集合
     */
    private int[] mImageIds;

    /**
     * 图片集合
     */
    private List<ImageView> mImageList;

    /**
     * 当前页面是否可见
     */
    private boolean mIsResume;

    /**
     * 当前广告的index
     */
    private int mCurrentPageIndex;

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mHandler.postDelayed(myRunnable, UPDATE_TIME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mHandler.removeCallbacks(myRunnable);
    }

    Runnable myRunnable = new Runnable() {
        @Override
        public void run() {
            mViewPager.setCurrentItem(mCurrentPageIndex + 1);
            mHandler.postDelayed(myRunnable, UPDATE_TIME);
        }
    };

    @Override
    protected void initVariables() {
        super.initVariables();
        /**
         * 初始化图片的资源id
         */
        mImageIds = new int[]{
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,
                R.drawable.my_toast,
                R.drawable.my_toast};
        /**
         * 添加ImageView
         */
        mImageList = new ArrayList<>();
        //额外添加两张图片
        int pagerCount = mImageIds.length + 2;
        ViewGroup.LayoutParams layoutParams =
                new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
        for (int i = 0; i < pagerCount; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(layoutParams);
            mImageList.add(imageView);
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
                mCurrentPageIndex = position;
                if (position == 0) {
                    // 当视图在第一个时，将页面号设置为图片的最后一张。
                    mCurrentPageIndex = mImageIds.length;
                } else if (position == mImageIds.length + 1) {
                    // 当视图在最后一个时,将页面号设置为图片的第一张。
                    mCurrentPageIndex = 1;
                }
                /**
                 * 当视图在第一个或者最后一个时，pageIndex和position的值不相等，
                 * 所以要改变viewPager的当前item。
                 */
                if (position != mCurrentPageIndex) {
                    /**
                     * 第二参数必须设置为false，意思是立即过渡，不给用户视觉上的效果。
                     */
                    mViewPager.setCurrentItem(mCurrentPageIndex, false);
                    return;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mLoopViewPager = new LoopViewPagerAdapter(mImageList, mImageIds);
        mViewPager.setAdapter(mLoopViewPager);
        /**
         * 因为第0个item是另外添加的那张图片，所以初始化时要把当前item设置为1。
         */
        mViewPager.setCurrentItem(1);
    }

    @Override
    protected int getContentLayout() {
        return R.layout.act_loopviewpager;
    }
}
