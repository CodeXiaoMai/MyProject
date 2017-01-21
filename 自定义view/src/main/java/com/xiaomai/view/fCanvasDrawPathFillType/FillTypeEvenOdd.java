
package com.xiaomai.view.fCanvasDrawPathFillType;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

/**
 * Created by XiaoMai on 2017/1/16 16:36.
 */
public class FillTypeEvenOdd extends View {

    private Paint mPaint;

    public FillTypeEvenOdd(Context context) {
        super(context);
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(getWidth() / 2, getHeight() / 2);
        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD); // 设置Path填充模式为 奇偶规则
        // path.setFillType(Path.FillType.INVERSE_EVEN_ODD); // 反奇偶规则
        path.addRect(-100, -100, 100, 100, Path.Direction.CW);
        canvas.drawPath(path, mPaint);
    }

}
