
package com.xiaomai.view.ajibenxingzhuang;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by XiaoMai on 2017/1/5 15:31.
 */
public class DrawCircleView extends View {

    private Paint mPaint = new Paint();

    private void initPaint() {
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(10f);
        mPaint.setStyle(Paint.Style.FILL);
    }

    public DrawCircleView(Context context) {
        super(context);
        initPaint();
    }

    public DrawCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 绘制一个圆心坐标在(200,200)，半径为200 的圆。
        canvas.drawCircle(200f, 200f, 200f, mPaint);
    }
}
