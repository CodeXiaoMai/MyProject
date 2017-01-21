
package com.xiaomai.view.fCanvasDrawPathFillType;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

/**
 * Created by XiaoMai on 2017/1/16 17:37.
 */
public class TaiJi extends View {

    private Paint mPaint;

    public TaiJi(Context context) {
        super(context);
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        // mPaint.setColor(Color.BLACK);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(getWidth() / 2, getHeight() / 2);
        Path path1 = new Path();
        Path path2 = new Path();
        Path path3 = new Path();
        Path path4 = new Path();

        path1.addCircle(0, 0, 200, Path.Direction.CW);
        path2.addRect(0, -200, 200, 200, Path.Direction.CW);
        path3.addCircle(0, -100, 100, Path.Direction.CW);
        path4.addCircle(0, 100, 100, Path.Direction.CW);

        path1.op(path2, Path.Op.DIFFERENCE);// 差集 Path1中减去Path2后剩下的部分
//        path1.op(path2, Path.Op.REVERSE_DIFFERENCE); // 差集 Path2中减去Path1后剩下的部分
        path1.op(path3, Path.Op.UNION);// 并集 包含全部Path1和Path2
        path1.op(path4, Path.Op.DIFFERENCE);
        // path1.op()
        canvas.drawPath(path1, mPaint);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(0, 0, 200, mPaint);
    }
}
