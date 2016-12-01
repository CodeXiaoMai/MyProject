
package com.xiaomai.myproject.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;

/**
 * Created by XiaoMai on 2016/12/1 9:53.
 */
public class CircleTextView extends TextView {

    /**
     * 文字默认的对其方式
     */
    private static final int GRAVITY = Gravity.CENTER;

    private int radius;

    public CircleTextView(Context context) {
        this(context, null);
    }

    public CircleTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        int max = Math.max(measuredWidth, measuredHeight);
        setMeasuredDimension(max, max);
    }

    private void init() {
        setGravity(GRAVITY);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        // 半径
        radius = Math.max(width, height) / 2;
        canvas.drawCircle(width / 2, height / 2, radius, new Paint());
        super.onDraw(canvas);
    }

}
