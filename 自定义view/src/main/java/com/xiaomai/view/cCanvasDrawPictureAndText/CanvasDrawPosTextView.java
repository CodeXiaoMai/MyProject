
package com.xiaomai.view.cCanvasDrawPictureAndText;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by XiaoMai on 2017/1/6 15:32.
 */
public class CanvasDrawPosTextView extends View {

    private Paint mPaint;

    public CanvasDrawPosTextView(Context context) {
        super(context);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setTextSize(20);
        mPaint.setColor(Color.BLACK);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        String src = "XIAOMAI";
        canvas.drawPosText(src, new float[] {
                100, 100, 120, 120, 140, 100, 160, 110, 180, 90, 200, 140, 220, 130
        }, mPaint);
    }
}
