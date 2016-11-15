
package com.xiaomai.myproject.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.xiaomai.myproject.R;
import com.xiaomai.myproject.utils.Utils;

import java.util.List;

/**
 * Created by XiaoMai on 2016/11/15 13:37.
 */
public class BannerView extends RelativeLayout {

    private Context context;

    private Handler handler;

    private ImageLoader imageLoader;

    // 控件Start
    private ViewPager viewPager;

    private LinearLayout indicator;// 指示器
    // 控件End

    // 自定义属性Start
    private float mAspectRatio; // 宽高比

    private int defaultImageResource; // 默认占位图

    private int updateTime; // 图片切换的时间间隔,默认3秒

    private boolean showIndicator; // 是否显示指示器,默认显示

    private int indicatorHeight;// 指示器的高度,默认35dp

    private int indicatorBackground; // 指示器的背景颜色

    // 自定义属性End

    // 数据Start
    private int imageCount;

    private int lastPosition;

    private int currentPosition;

    private List<Integer> imageResources;

    private List<String> imageUrls;
    // 数据End

    private Runnable updateRunnable = new Runnable() {
        @Override
        public void run() {

        }
    };

    public BannerView(Context context) {
        this(context, null);
    }

    public BannerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BannerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        parseCustomAttributes(context, attrs);
        this.context = context;
        handler = new Handler();
        imageLoader = ImageLoader.getInstance();
        initViews();
    }

    /**
     * 解析自定义属性
     *
     * @param context
     * @param attrs
     */
    private void parseCustomAttributes(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BannerView);
        mAspectRatio = typedArray.getFloat(R.styleable.BannerView_aspectRatio, 0f);
        defaultImageResource = typedArray.getResourceId(R.styleable.BannerView_defaultSrc,
                R.mipmap.ic_launcher);
        updateTime = typedArray.getInt(R.styleable.BannerView_updateTime, 3000);
        showIndicator = typedArray.getBoolean(R.styleable.BannerView_indicatorVisible, true);
        indicatorHeight = (int) (typedArray.getDimension(R.styleable.BannerView_indicatorHeight,
                Utils.dip2px(context, 35)));
        indicatorBackground = typedArray.getResourceId(R.styleable.BannerView_indicatorBackground,
                R.color.mediacontroller_bg_pressed);
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mAspectRatio > 0) {
            // 如果mAspectRatio大于0表示用户设置了宽高比，否则直接调用父类的onMeasureSpec。
            int width = MeasureSpec.getSize(widthMeasureSpec);
            int height = (int) (width / mAspectRatio);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void initViews() {
        viewPager = new ViewPager(context);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset,
                    int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (showIndicator) {
                    for (int i = 0; i < indicator.getChildCount(); i++) {
                        indicator.getChildAt(i).setSelected(false);
                    }
                    indicator.getChildAt(position % imageCount).setSelected(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        addView(viewPager, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        if (showIndicator) {
            indicator = new LinearLayout(context);
            indicator.setOrientation(LinearLayout.HORIZONTAL);
            indicator.setGravity(Gravity.CENTER);
            indicator.setBackgroundResource(indicatorBackground);
            RelativeLayout.LayoutParams layoutParams = new LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, indicatorHeight);
            layoutParams.addRule(ALIGN_PARENT_BOTTOM);
            addView(indicator, layoutParams);
        }
    }

    public void setImageResources(List<Integer> imageResources) {
        if (imageResources == null || imageResources.size() == 0) {
            throw new RuntimeException("图片资源为空");
        }
        this.imageResources = imageResources;
        imageCount = imageResources.size();
    }

    public void setImageUrls(List<String> imageUrls) {
        if (imageUrls == null || imageUrls.size() == 0) {
            throw new RuntimeException("图片地址资源为空");
        }
        this.imageUrls = imageUrls;
        imageCount = imageUrls.size();
        loadImages();
    }

    private void loadImages() {
        if (showIndicator) {
            addIndicationPoint();
        }
        viewPager.setAdapter(new MyViewPageAdapter());
        handler.removeCallbacks(updateRunnable);
        handler.postDelayed(updateRunnable, updateTime);
    }

    /**
     * 添加指示点到指示器中
     */
    private void addIndicationPoint() {
        // 防止刷新重复添加
        if (indicator.getChildCount() > 0) {
            indicator.removeAllViews();
        }
        View pointView;
        // 圆点的直径
        int diameter = Utils.dip2px(context, 10f);
        int margin = Utils.dip2px(context, 5f);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(diameter, diameter);
        layoutParams.setMargins(margin, margin, margin, margin);

        for (int i = 0; i < imageCount; i++) {
            pointView = new View(context);
            pointView.setBackgroundResource(R.drawable.indicator_selector);
            if (i == lastPosition) {
                pointView.setSelected(true);
            } else {
                pointView.setSelected(false);
            }
            indicator.addView(pointView, layoutParams);
        }
    }

    private class MyViewPageAdapter extends PagerAdapter {
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
            ImageView imageView = new ImageView(container.getContext());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageLoader.displayImage(imageUrls.get(position % imageCount), imageView);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
