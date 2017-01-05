
package com.xiaomai.view.ajibenxingzhuang;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by XiaoMai on 2017/1/5 15:02.
 */
public class DrawRectView extends View {

    private Paint mPaint = new Paint();

    private void initPaint() {
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(10f);
        mPaint.setAntiAlias(true);
    }

    public DrawRectView(Context context) {
        super(context);
        initPaint();
    }

    public DrawRectView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawRectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 第一种
        canvas.drawRect(100, 10, 400, 50, mPaint);
        // 第二种
        Rect rect = new Rect(100, 60, 400, 100);
        canvas.drawRect(rect, mPaint);
        // 第三种
        RectF rectF = new RectF(100f, 110f, 400f, 150f);
        canvas.drawRect(rectF, mPaint);
        // Rect和Rectf两者最大的区别就是精度不同，Rect是int(整形)的，而RectF是float(单精度浮点型)的釦

        // 绘制圆角矩形,这种方法在Api21才可以使用
        // canvas.drawRoundRect(100f, 160f, 400f, 200f, 30f, 30f, mPaint);
        // 通用方法
        canvas.drawRoundRect(new RectF(100f, 210f, 400f, 250f), 10f, 10f, mPaint);
    }
}
