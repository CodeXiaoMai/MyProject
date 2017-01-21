
package com.xiaomai.view.fCanvasDrawPathFillType;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

/**
 * Created by XiaoMai on 2017/1/16 18:15.
 */
public class BooleanOp extends View {

    private Paint mPaint;

    public BooleanOp(Context context) {
        super(context);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(20);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int x = 40;
        int r = 50;
        canvas.translate(250, 0);
        Path path1 = new Path();
        Path path2 = new Path();
        Path pathOpResult = new Path();

        path1.addCircle(-x, 0, r, Path.Direction.CW);
        path2.addCircle(x, 0, r, Path.Direction.CW);

        pathOpResult.op(path1, path2, Path.Op.DIFFERENCE);// 差集
                                                          // Path1中减去Path2后剩下的部分
        canvas.translate(0, 60);
        canvas.drawText("DIFFERENCE", 240, 0, mPaint);
        canvas.drawPath(pathOpResult, mPaint);

        pathOpResult.op(path1, path2, Path.Op.REVERSE_DIFFERENCE);// 差集
                                                                  // Path2中减去Path1后剩下的部分
        canvas.translate(0, 120);
        canvas.drawText("REVERSE_DIFFERENCE", 240, 0, mPaint);
        canvas.drawPath(pathOpResult, mPaint);

        pathOpResult.op(path1, path2, Path.Op.INTERSECT);// 交集 Path1与Path2相交的部分
        canvas.translate(0, 120);
        canvas.drawText("INTERSECT", 240, 0, mPaint);
        canvas.drawPath(pathOpResult, mPaint);

        pathOpResult.op(path1, path2, Path.Op.UNION);// 并集 包含全部Path1和Path2
        canvas.translate(0, 120);
        canvas.drawText("UNION", 240, 0, mPaint);
        canvas.drawPath(pathOpResult, mPaint);

        pathOpResult.op(path1, path2, Path.Op.XOR);// 异或
                                                   // 包含Path1与Path2但不包括两者相交的部分
        canvas.translate(0, 120);
        canvas.drawText("XOR", 240, 0, mPaint);
        canvas.drawPath(pathOpResult, mPaint);

    }
}
