
package com.xiaomai.view.gPathMeasure;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.view.View;

/**
 * Created by XiaoMai on 2017/1/17 9:52.</br>
 * Segment 用于获取Path的一个片段
 */
public class PathMeasureSegment extends View {

    private Paint mPaint;

    public PathMeasureSegment(Context context) {
        super(context);
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(getWidth() / 2, getHeight() / 2);
        Path path = new Path(); // 创建Path并添加了一个矩形

        path.addRect(-200, -200, 200, 200, Path.Direction.CW);

        Path dst = new Path();// 将 Path 与 PathMeasure 关联
        dst.lineTo(-200, -200);

        PathMeasure pathMeasure = new PathMeasure(path, false);
        // 截取一部分存入dst中，并使用 moveTo 保持截取得到的 Path 第一个点的位置不变
        // pathMeasure.getSegment(200, 600, dst, true);
        // 截取一部分 不使用 startMoveTo, 保持 dst 的连续性
        pathMeasure.getSegment(200, 600, dst, false);
        canvas.drawPath(dst, mPaint);
        // 从该示例我们又可以得到一条结论：如果 startWithMoveTo 为 true, 则被截取出来到Path片段保持原状，如果
        // startWithMoveTo 为 false，则会将截取出来的 Path 片段的起始点移动到 dst 的最后一个点，以保证 dst
        // 的连续性。

    }
}
