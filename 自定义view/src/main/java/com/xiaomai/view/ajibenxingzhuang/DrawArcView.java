
package com.xiaomai.view.ajibenxingzhuang;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

/**
 * Created by XiaoMai on 2017/1/5 15:35.
 */
public class DrawArcView extends View {

    private Paint mPaint = new Paint();

    private void initPaint() {
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(10f);
        mPaint.setStyle(Paint.Style.FILL);
    }

    public DrawArcView(Context context) {
        super(context);
        initPaint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        RectF rectF = new RectF(100f, 100f, 300f, 300f);
        // 绘制背景矩形
        canvas.drawRect(rectF, mPaint);

        // 绘制圆弧
        mPaint.setColor(Color.WHITE);
        canvas.drawArc(rectF, 0, // 开始的角度
                90, // 扫过的角度
                false, // 是否使用中心
                mPaint);

        RectF rectF2 = new RectF(100f, 410f, 300f, 610f);
        mPaint.setColor(Color.GRAY);
        canvas.drawRect(rectF2, mPaint);

        mPaint.setColor(Color.WHITE);
        canvas.drawArc(rectF2, 30, 60, true, mPaint);
    }
}
