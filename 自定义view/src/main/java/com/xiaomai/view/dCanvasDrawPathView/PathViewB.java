
package com.xiaomai.view.dCanvasDrawPathView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

/**
 * Created by XiaoMai on 2017/1/6 19:15.
 */
public class PathViewB extends View {

    private Paint mPaint;

    public PathViewB(Context context) {
        super(context);
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(getWidth() / 2, getHeight() / 2);
        Path path = new Path();
        path.addRect(-100, -100, 100, 100, Path.Direction.CCW);
        path.setLastPoint(-200, 200);
        canvas.drawPath(path, mPaint);
    }
}
