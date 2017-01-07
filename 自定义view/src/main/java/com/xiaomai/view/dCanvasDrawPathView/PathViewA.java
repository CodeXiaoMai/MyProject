
package com.xiaomai.view.dCanvasDrawPathView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

/**
 * Created by XiaoMai on 2017/1/6 18:33.
 */
public class PathViewA extends View {

    private Paint mPaint;

    public PathViewA(Context context) {
        super(context);
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(getWidth() / 2, getHeight() / 2);
        // 创建Path
        Path path = new Path();
        path.lineTo(100, 100);
        // moveTo只改变下次操作的起点，在执行完第一次LineTo的时候，本来的默认点位置是A(100,100),但是moveTo将其改变成为了C(200,100),
        // 所以在第二次调用lineTo的时候就是连接C(200,100)到 B(100,0) 之间的直线
        path.moveTo(200, 100);
        path.lineTo(100, 0);
        /**
         * setLastPoint是重置上一次操作的最后一个点，在执行完第一次的lineTo的时候，
         * 最后一个点是A(100,0),而setLastPoint更改最后一个点为C(100,50),所以在实际执行的时候，
         * 第一次的lineTo就不是从(200, 100)到A(100,0)的连线了，而变成了从(200, 100)到C(100,50)之间的连线了。
         * 在执行完第一次lineTo和setLastPoint后，最后一个点的位置是C(100,50),
         * 所以在第二次调用lineTo的时候就是C(100,50)到 B(150,100) 之间的连线
         */
        path.setLastPoint(100, 50);
        path.lineTo(150, 100);

        path.close();
        canvas.drawPath(path, mPaint);
    }
}
