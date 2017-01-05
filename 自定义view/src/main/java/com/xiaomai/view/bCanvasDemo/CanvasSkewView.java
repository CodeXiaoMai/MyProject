
package com.xiaomai.view.bCanvasDemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

/**
 * Created by XiaoMai on 2017/1/5 18:19.
 */
public class CanvasSkewView extends View {

    private Paint mPaint = new Paint();

    private void initPaint() {
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    public CanvasSkewView(Context context) {
        super(context);
        initPaint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(getWidth() / 2, getHeight() / 2);
        RectF rectF = new RectF(0, 0, 100, 100);
        canvas.drawRect(rectF, mPaint);

        canvas.skew(1, 0);
        canvas.skew(0, 1);
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(rectF, mPaint);
    }
}
