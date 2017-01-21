
package com.xiaomai.view.fCanvasDrawPathFillType;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

/**
 * Created by XiaoMai on 2017/1/16 16:43.
 */
public class FillTypeWinding extends View {

    private Paint mPaint;

    public FillTypeWinding(Context context) {
        super(context);
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(getWidth() / 2, getHeight() / 2);
        Path path = new Path();
//        path.addRect(-100, -100, 100, 100, Path.Direction.CW);
        path.addRect(-100, -100, 100, 100, Path.Direction.CCW);

        path.addRect(-150, -150, 150, 150, Path.Direction.CCW);

        path.setFillType(Path.FillType.WINDING); // 设置Path填充模式为非零环绕规则
        canvas.drawPath(path, mPaint);
    }
}
