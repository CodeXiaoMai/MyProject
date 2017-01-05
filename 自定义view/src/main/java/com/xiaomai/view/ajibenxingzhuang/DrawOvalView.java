
package com.xiaomai.view.ajibenxingzhuang;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by XiaoMai on 2017/1/5 15:24.
 */
public class DrawOvalView extends View {

    private Paint mPaint = new Paint();

    private void initPaint() {
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(10f);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.GREEN);
    }

    public DrawOvalView(Context context) {
        super(context);
        initPaint();
    }

    public DrawOvalView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawOvalView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 第一种
        RectF rectF = new RectF(100f, 100f, 400f, 300f);
        canvas.drawOval(rectF, mPaint);
        // 第二种 Api21新添加的方法
        // canvas.drawOval(100f, 400f, 400f, 600f, mPaint);
    }
}
