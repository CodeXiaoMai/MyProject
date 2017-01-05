
package com.xiaomai.view.bCanvasDemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

/**
 * Created by XiaoMai on 2017/1/5 17:46.
 */
public class CanvasScaleView extends View {

    private Paint mPaint = new Paint();

    public CanvasScaleView(Context context) {
        super(context);
        initPaint();
    }

    private void initPaint() {
        mPaint.setColor(Color.BLACK);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 将坐标系原点移动到画布中心
        canvas.translate(getWidth() / 2, getHeight() / 2);
        RectF rectF = new RectF(0, -200, 200, 0);
        canvas.drawRect(rectF, mPaint);
        // 画布缩放
        canvas.scale(0.5f, 0.5f);
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(rectF, mPaint);
        // 画布缩放，缩放中心向右偏移50单位
        canvas.scale(0.5f, 0.5f, 50, 0);
        mPaint.setColor(Color.GREEN);
        canvas.drawRect(rectF, mPaint);
    }
}
