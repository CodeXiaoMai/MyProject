
package com.xiaomai.view.eCanvasDrawBezierView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by XiaoMai on 2017/1/11 17:44.
 */
public class CubicBezierView extends View {

    private Paint mPaint;

    private int centerX;

    private int centerY;

    private PointF start, end, contralOne, contralTwo;

    private boolean mode = true;

    public CubicBezierView(Context context) {
        super(context);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(8);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setTextSize(20);

        start = new PointF(0, 0);
        end = new PointF(0, 0);
        contralOne = new PointF(0, 0);
        contralTwo = new PointF(0, 0);
    }

    public void setMode(boolean mode) {
        this.mode = mode;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w / 2;
        centerY = h / 2;

        // 初始化数据点和控制点的位置
        start.x = centerX - 200;
        start.y = centerY;
        end.x = centerX + 200;
        end.y = centerY;

        contralOne.x = centerX;
        contralOne.y = centerY - 100;
        contralTwo.x = centerX;
        contralTwo.y = centerY - 100;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 根据触摸位置更新控制点，并提示重绘
        if (mode) {
            contralOne.x = event.getX();
            contralOne.y = event.getY();
        } else {
            contralTwo.x = event.getX();
            contralTwo.y = event.getY();
        }
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 绘制数据点和控制点
        mPaint.setColor(Color.BLACK);
        canvas.drawPoint(start.x, start.y, mPaint);
        canvas.drawPoint(end.x, end.y, mPaint);
        canvas.drawPoint(contralOne.x, contralOne.y, mPaint);
        canvas.drawPoint(contralTwo.x, contralTwo.y, mPaint);

        // 绘制辅助线
        mPaint.setColor(Color.GRAY);
        canvas.drawLine(start.x, start.y, contralOne.x, contralOne.y, mPaint);
        canvas.drawLine(contralOne.x, contralOne.y, contralTwo.x, contralTwo.y, mPaint);
        canvas.drawLine(contralTwo.x, contralTwo.y, end.x, end.y, mPaint);

        // 绘制贝塞尔曲线
        mPaint.setColor(Color.RED);
        Path path = new Path();
        path.moveTo(start.x, start.y);
        // [ˈkjubɪk] 三次曲线
        path.cubicTo(contralOne.x, contralOne.y, contralTwo.x, contralTwo.y, end.x, end.y);

        canvas.drawPath(path, mPaint);
    }
}
