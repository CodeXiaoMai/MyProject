
package com.xiaomai.view.ajibenxingzhuang;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.xiaomai.view.javabean.PieData;

import java.util.List;

/**
 * Created by XiaoMai on 2017/1/5 16:24.</br>
 * 饼状图
 */
public class PieChartView extends View {

    // 饼状图初始绘制角度
    private float mStartAngle = 0f;

    // 数据
    private List<PieData> mData;

    // 宽高
    private int mWidth;

    private int mHeight;

    private Paint mPaint;

    public PieChartView(Context context) {
        this(context, null);
    }

    public PieChartView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PieChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (null == mData) {
            return;
        }
        float currentStartAngle = mStartAngle;
        // 将画布坐标原点移动到中心位置
        canvas.translate(mWidth / 2, mHeight / 2);
        // 饼状图的半径
        float radius = Math.min(mWidth, mHeight) / 2;
        // 饼状图的绘制区域
        RectF rectF = new RectF(-radius, -radius, radius, radius);

        for (int i = 0; i < mData.size(); i++) {
            PieData pieData = mData.get(i);
            mPaint.setColor(pieData.getColor());
            canvas.drawArc(rectF, currentStartAngle, pieData.getAngle(), true, mPaint);
            currentStartAngle += pieData.getAngle();
        }
    }

    /**
     * 设置起始角度
     * 
     * @param startAngle
     */
    public void setStartAngle(int startAngle) {
        mStartAngle = startAngle;
        invalidate();// 刷新
    }

    public void setData(List<PieData> mData) {
        this.mData = mData;
        initData(mData);
        invalidate();
    }

    private void initData(List<PieData> mData) {
        if (null == mData || mData.size() == 0) {
            return;
        }
        float sumValue = 0;
        for (PieData pieData : mData) {
            sumValue += pieData.getValue();
        }

        for (PieData pieData : mData) {
            // 百分比
            float percentage = pieData.getValue() / sumValue;
            float angle = percentage * 360;
            pieData.setPercentage(percentage);
            pieData.setAngle(angle);
        }
    }
}
