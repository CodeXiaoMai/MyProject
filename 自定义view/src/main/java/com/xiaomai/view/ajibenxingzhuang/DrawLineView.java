
package com.xiaomai.view.ajibenxingzhuang;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by XiaoMai on 2017/1/5 14:56.</br>
 * 绘制直线需要两个点，初始点和结束点，同样绘制直线也可以绘制一条或者绘制一组：
 */
public class DrawLineView extends View {

    private Paint mPaint = new Paint();

    private void initPaint() {
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(10f);
    }

    public DrawLineView(Context context) {
        super(context);
        initPaint();
    }

    public DrawLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawLineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawLine(200, 300, 400, 500, mPaint); // 在坐标（200，300）（400,500）之间绘制一条直线
        canvas.drawLines(new float[] {               // 绘制一组直线，每四个数字（两个点的坐标）确定一条直线
                100, 200, 200, 200,
                100, 300, 200, 300
        }, mPaint);
    }
}
