package com.xiaomai.myproject.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.xiaomai.myproject.R;

/**
 * 这是一个自定义的ImageView，它可以按照宽高比动态设置ImagView的大小
 * Created by XiaoMai on 2016/8/29.
 */
public class MyImageView extends ImageView {

    //宽高比
    private float mAspectRatio;

    public MyImageView(Context context) {
        this(context, null);
    }

    public MyImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    /**
     * 获取自定义属性的值
     *
     * @param attrs
     */
    private void init(AttributeSet attrs) {
        //获得自定义样式属性
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.MyImageView);
        //如果没有设置此值默认为-1
        mAspectRatio = typedArray.getFloat(R.styleable.MyImageView_aspectRatio, -1);
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mAspectRatio > 0) {
            //如果mAspectRatio大于0表示用户设置了宽高比，否则直接调用父类的onMeasureSpec。
            int width = MeasureSpec.getSize(widthMeasureSpec);
            int height = (int) (width / mAspectRatio);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
