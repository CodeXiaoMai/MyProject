package com.xiaomai.view.ajibenxingzhuang;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by XiaoMai on 2017/1/5 14:44.
 */
public class DrawPointView extends View {

    private Paint mPaint = new Paint();

    private void initPaint() {
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);  //设置画笔模式为填充
        mPaint.setStrokeWidth(10f);         //设置画笔宽度为10px
    }

    public DrawPointView(Context context) {
        super(context);
        initPaint();
    }

    public DrawPointView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawPointView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPoint(100, 100, mPaint);     //在坐标（200,200）位置绘制一个点
        canvas.drawPoints(new float[]{          //绘制一组点
                200, 500,
                200, 600,
                200, 700
        }, mPaint);
    }
}
