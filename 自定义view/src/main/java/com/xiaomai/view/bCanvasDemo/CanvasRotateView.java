
package com.xiaomai.view.bCanvasDemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by XiaoMai on 2017/1/5 18:08.
 */
public class CanvasRotateView extends View {

    private Paint mPaint = new Paint();

    private void initPaint() {
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    public CanvasRotateView(Context context) {
        super(context);
        initPaint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(getWidth() / 2, getHeight() / 2);
        /*
         * RectF rectF = new RectF(0, -200, 200, 0); canvas.drawRect(rectF,
         * mPaint); // 旋转50度 canvas.rotate(50); mPaint.setColor(Color.BLUE);
         * canvas.drawRect(rectF, mPaint);
         */

        canvas.drawCircle(0, 0, 200, mPaint);
        canvas.drawCircle(0, 0, 180, mPaint);
        for (int i = 0; i < 360; i += 10) {
            canvas.drawLine(0, 180, 0, 200, mPaint);
            canvas.rotate(10);
        }
    }
}
