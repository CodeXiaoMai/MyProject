
package com.xiaomai.view.dCanvasDrawPathView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

/**
 * Created by XiaoMai on 2017/1/7 16:49.
 */
public class PathViewC extends View {

    private Paint mPaint;

    public PathViewC(Context context) {
        super(context);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(getWidth() / 2, getHeight() / 2);
        // 翻转Y轴
        canvas.scale(1, -1);
        Path path = new Path();
        Path src = new Path();

        path.addRect(-200, -200, 200, 200, Path.Direction.CW);
        src.addCircle(0, 0, 100, Path.Direction.CW);

        path.addPath(src, 0, 200);
        canvas.drawPath(path, mPaint);


    }
}
