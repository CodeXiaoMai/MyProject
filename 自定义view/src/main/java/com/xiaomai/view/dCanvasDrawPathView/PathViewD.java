
package com.xiaomai.view.dCanvasDrawPathView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.View;

/**
 * Created by XiaoMai on 2017/1/7 16:49.
 */
public class PathViewD extends View {

    private Paint mPaint;

    public PathViewD(Context context) {
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
        path.lineTo(100, 100);

        RectF oval = new RectF(0, 0, 200, 200);
        // path.addArc(oval, 0, 270);
        // 将上次绘制的最后一个点移动到圆弧起点，即不连接最后一个点与圆弧起点
        // path.arcTo(oval, 0, 270, true);

        // 不移动，而是连接最后一个点与圆弧起点
        path.arcTo(oval, 0, 270, false);
        // 默认为false
        path.arcTo(oval, 0, 270);
        canvas.drawPath(path, mPaint);
    }
}
