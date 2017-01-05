
package com.xiaomai.view.ajibenxingzhuang;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by XiaoMai on 2017/1/5 16:02.
 */
public class PaintTest extends View {

    private Paint mPaint = new Paint();

    private void initPaint() {
        mPaint.setColor(Color.BLACK);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(20f); // 描边线的宽度是要画的区域内外各占一半
    }

    public PaintTest(Context context) {
        super(context);
        initPaint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 描边
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(200, 200, 50, mPaint);

        // 填充加描边
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setColor(0xaa00ff00);
        canvas.drawCircle(200, 300, 50, mPaint);

        // 填充
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(0xaaff0000);
        canvas.drawCircle(200, 400, 50, mPaint);

        mPaint.setStrokeWidth(1f);
        canvas.drawLines(new float[] {
                150, 0, 150, 800, 250, 0, 250, 800
        }, mPaint);
    }
}
