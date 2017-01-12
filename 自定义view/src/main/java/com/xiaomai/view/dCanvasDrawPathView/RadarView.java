
package com.xiaomai.view.dCanvasDrawPathView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

/**
 * Created by XiaoMai on 2017/1/7 17:32.</br>
 * 雷达视图
 */
public class RadarView extends View {

    // 数据个数
    private int count = 5;

    private float angle = (float) (Math.PI * 2 / count);

    // 网格最大半径
    private float radius;

    private int centerX;

    private int centerY;

    private String[] titles = {
            "a", "b", "c", "d", "e", "f","g"
    };

    // 各个维度的值
    private double[] datas = {
            70, 50, 30, 60, 20, 22,33
    };

    private float maxValue = 100;

    // 雷达区画笔
    private Paint mainPaint;

    // 数据区画笔
    private Paint valuePaint;

    // 文本画笔
    private Paint textPaint;

    public RadarView(Context context) {
        super(context);
        init();
    }

    private void init() {
        mainPaint = new Paint();
        mainPaint.setAntiAlias(true);
        mainPaint.setColor(Color.BLACK);
        mainPaint.setStyle(Paint.Style.STROKE);

        textPaint = new Paint();
        textPaint.setColor(Color.BLACK);

        valuePaint = new Paint();
        valuePaint.setAntiAlias(true);
        valuePaint.setColor(Color.BLACK);
        valuePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        valuePaint.setAlpha(255);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        radius = Math.min(h, w) / 2 * 0.9f;
        centerX = w / 2;
        centerY = h / 2;
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(centerX, centerY);
        drawPolygon(canvas);
        drawLines(canvas);
        drawText(canvas);
        drawRegion(canvas);
    }

    /**
     * 绘制覆盖区域
     * 
     * @param canvas
     */
    private void drawRegion(Canvas canvas) {
        Path path = new Path();
        for (int i = 0; i < count; i++) {
            double percent = datas[i] / maxValue;
            float x = (float) (radius * Math.cos(angle * i) * percent);
            float y = (float) (radius * Math.sin(angle * i) * percent);
            if (i == 0) {
                path.moveTo(x, 0);
            } else if (i < count) {
                path.lineTo(x, y);
            }
            // 绘制小圆点
            canvas.drawCircle(x, y, 5, valuePaint);
        }
        path.close();
        valuePaint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, valuePaint);
        // 绘制填充区域
        valuePaint.setAlpha(127);
        valuePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawPath(path, valuePaint);
    }

    /**
     * 绘制文本</br>
     * 对于文本的绘制，首先要找到末端的坐标，由于末端和文本有一定距离，给每个末端加上这个距离以后，再绘制文本。
     * 另外，当文本在左边时，由于不希望文本和蜘蛛网交叉，我们可以先计算出文本的长度，然后使起始绘制坐标向左偏移这个长度。
     * 
     * @param canvas
     */
    private void drawText(Canvas canvas) {
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float fontHeight = fontMetrics.descent - fontMetrics.ascent;
        for (int i = 0; i < count; i++) {
            float x = (float) ((radius + fontHeight / 2) * Math.cos(angle * i));
            float y = (float) ((radius + fontHeight / 2) * Math.sin(angle * i));
            if (x < 0) {
                float dis = textPaint.measureText(titles[i]);
                canvas.drawText(titles[i], x - dis, y, textPaint);
            } else {
                canvas.drawText(titles[i], x, y, textPaint);
            }
        }
    }

    /**
     * 绘制从中心到末端的直线
     * 
     * @param canvas
     */
    private void drawLines(Canvas canvas) {
        Path path = new Path();
        for (int i = 0; i < count; i++) {
            path.reset();
            float x = (float) (radius * Math.cos(angle * i));
            float y = (float) (radius * Math.sin(angle * i));
            path.lineTo(x, y);
            canvas.drawPath(path, mainPaint);
        }
    }

    /**
     * 绘制多边形
     * 
     * @param canvas
     */
    private void drawPolygon(Canvas canvas) {
        Path path = new Path();
        // 每个多边形之间的距离
        float r = radius / (count - 1);
        for (int i = 1; i < count; i++) {
            float currentR = r * i;
            // path.reset();
            for (int j = 0; j < count; j++) {
                if (j == 0) {
                    path.moveTo(currentR, 0);
                } else {
                    float x = (float) (currentR * Math.cos(angle * j));
                    float y = (float) (currentR * Math.sin(angle * j));
                    path.lineTo(x, y);
                }
            }
            path.close();
            canvas.drawPath(path, mainPaint);
        }
    }
}
