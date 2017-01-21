
package com.xiaomai.view.gPathMeasure;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.Log;
import android.view.View;

/**
 * Created by XiaoMai on 2017/1/17 10:20.
 */
public class PathMeasureContour extends View {

    private static final String TAG = "PathMeasureContour";

    private Paint mPaint;

    public PathMeasureContour(Context context) {
        super(context);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(getWidth() / 2, getHeight() / 2);

        Path path = new Path();

        path.addRect(-100, -100, 100, 100, Path.Direction.CW);
        path.addRect(-200, -200, 200, 200, Path.Direction.CW);

        canvas.drawPath(path, mPaint);

        PathMeasure pathMeasure = new PathMeasure(path, false);
        float length1 = pathMeasure.getLength(); // 获取第一条路径的长度

        // 跳转到下一条路径
        pathMeasure.nextContour();

        float length2 = pathMeasure.getLength();

        Log.d(TAG, "onDraw: length1 " + length1);
        Log.d(TAG, "onDraw: length2 " + length2);

        /**
         * 1.曲线的顺序与 Path 中添加的顺序有关。 </br>
         * 2.getLength 获取到到是当前一条曲线分长度，而不是整个 Path 的长度。 </br>
         * 3.getLength 等方法是针对当前的曲线
         */
    }
}
