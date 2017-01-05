
package com.xiaomai.view.bCanvasDemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by XiaoMai on 2017/1/5 17:38.
 */
public class CanvasTranslateView extends View {
    Paint mPaint = new Paint();

    public CanvasTranslateView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setColor(Color.BLACK);
        canvas.translate(200, 200);
        canvas.drawCircle(0, 0, 100, mPaint);

        mPaint.setColor(Color.BLUE);
        canvas.translate(0, 200);
        canvas.drawCircle(0, 0, 100, mPaint);


    }
}
