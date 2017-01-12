
package com.xiaomai.view.eCanvasDrawBezierView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.view.View;

/**
 * Created by XiaoMai on 2017/1/11 18:14.
 */
public class CircleToHeartBezierView extends View {

    private static final float C = 0.551915024494f; // 一个常量，用来计算绘制圆形贝塞尔曲线控制点的位置

    private Paint mPaint;

    private int mCenterX, mCenterY;

    private PointF mCenter = new PointF(0, 0);

    private float mCircleRadius = 200;// 圆的半径

    private float mDifference = mCircleRadius * C;// 圆形的控制点与数据点的差值

    private float[] mData = new float[8];// 顺时针记录绘制圆形的四个数据点

    private float[] mCtrl = new float[16];// 顺时针记录绘制圆形的八个控制点

    private float mDuration = 1000;

    private float mCurrent = 0;

    private float mCount = 100;

    private float mPiece = mDuration / mCount;

    public CircleToHeartBezierView(Context context) {
        super(context);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(8);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setTextSize(10);

        // 初始化数据点
        mData[0] = 0;
        mData[1] = mCircleRadius;

        mData[2] = mCircleRadius;
        mData[3] = 0;

        mData[4] = 0;
        mData[5] = -mCircleRadius;

        mData[6] = -mCircleRadius;
        mData[7] = 0;

        // 初始化控制点

        mCtrl[0] = mData[0] + mDifference;
        mCtrl[1] = mData[1];

        mCtrl[2] = mData[2];
        mCtrl[3] = mData[3] + mDifference;

        mCtrl[4] = mData[2];
        mCtrl[5] = mData[3] - mDifference;

        mCtrl[6] = mData[4] + mDifference;
        mCtrl[7] = mData[5];

        mCtrl[8] = mData[4] - mDifference;
        mCtrl[9] = mData[5];

        mCtrl[10] = mData[6];
        mCtrl[11] = mData[7] - mDifference;

        mCtrl[12] = mData[6];
        mCtrl[13] = mData[7] + mDifference;

        mCtrl[14] = mData[0] - mDifference;
        mCtrl[15] = mData[1];
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCenterX = w / 2;
        mCenterY = h / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCoordinateSystem(canvas);
    }

    private void drawCoordinateSystem(Canvas canvas) {
        canvas.save();

        canvas.translate(mCenterX, mCenterY);
        canvas.scale(1, -1);

        Paint fuzhuPaint = new Paint();
        fuzhuPaint.setColor(Color.RED);
        fuzhuPaint.setStrokeWidth(5);
        fuzhuPaint.setStyle(Paint.Style.STROKE);

        canvas.drawLine(0, -200 , 0 , 200 , fuzhuPaint);
        canvas.drawLine(-200, 0, 200, 0, fuzhuPaint);

        canvas.restore();
    }
}
