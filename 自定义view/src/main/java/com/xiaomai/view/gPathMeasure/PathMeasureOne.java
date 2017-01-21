
package com.xiaomai.view.gPathMeasure;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.Log;
import android.view.View;

/**
 * Created by XiaoMai on 2017/1/17 9:28.
 */
public class PathMeasureOne extends View {

    private static final String TAG = "PathMeasureOne";

    private Paint mPaint;

    public PathMeasureOne(Context context) {
        super(context);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(getWidth() / 2, getHeight() / 2);
        canvas.scale(1, -1);

        Path path = new Path();
        path.lineTo(0, 200);
        path.lineTo(200, 200);
        path.lineTo(200, 0);

        PathMeasure measure1 = new PathMeasure(path, false);
        PathMeasure measure2 = new PathMeasure(path, true);

        Log.e(TAG, "forceClosed = false: " + measure1.getLength());
        Log.e(TAG, "forceClosed = true : " + measure2.getLength());

        /**
         * 1.我们将 Path 与两个的 PathMeasure 进行关联，并给 forceClosed 设置了不同的状态，之后绘制再绘制出来的
         * Path 没有任何变化，所以与 Path 与 PathMeasure进行关联并不会影响 Path 状态。
         * 2.我们可以看到，设置forceClosed 为 true 的方法比设置为 false 的方法测量出来的长度要长一点，这是由于 Path
         * 没有闭合的缘故，多出来的距离正是 Path 最后一个点与最开始一个点之间点距离。forceClosed 为 false 测量的是当前
         * Path 状态的长度， forceClosed 为 true，则不论Path是否闭合测量的都是 Path 的闭合长度。
         */
        canvas.drawPath(path, mPaint);
    }
}
