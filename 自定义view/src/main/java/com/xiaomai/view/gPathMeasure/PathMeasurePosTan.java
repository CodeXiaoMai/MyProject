
package com.xiaomai.view.gPathMeasure;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.view.View;

import com.xiaomai.view.R;

/**
 * Created by XiaoMai on 2017/1/17 10:37.
 */
public class PathMeasurePosTan extends View {

    private Paint mPaint;

    // 用于纪录当前的位置,取值范围[0,1]映射Path的整个长度
    private float currentValue = 0;

    // 当前点的实际位置
    private float[] pos;

    // 当前点的tangent值，用于计算图片所需旋转的角度
    private float[] tan;

    private Bitmap mBitmap;

    // 矩阵，用于对图片进行一些操作
    private Matrix mMatrix;

    public PathMeasurePosTan(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);

        pos = new float[2];
        tan = new float[2];
        BitmapFactory.Options options = new BitmapFactory.Options();
        // 缩放图片
        options.inSampleSize = 2;
        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.arrow, options);
        mMatrix = new Matrix();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(getWidth() / 2, getHeight() / 2);

        Path path = new Path();
        path.addCircle(0, 0, 200, Path.Direction.CW);

        PathMeasure pathMeasure = new PathMeasure(path, false);

        currentValue += 0.005;
        if (currentValue >= 1) {
            currentValue = 0;
        }

        pathMeasure.getPosTan(pathMeasure.getLength() * currentValue, pos, tan);
        mMatrix.reset();// 重置Matrix

        // 计算图片旋转角度
        float degrees = (float) (Math.atan2(tan[1], tan[0]) * 180.0 / Math.PI);
        // 旋转图片
        mMatrix.postRotate(degrees, mBitmap.getWidth() / 2, mBitmap.getHeight() / 2);
        // 将图片绘制中心调整到与当前点重合
        mMatrix.postTranslate(pos[0] - mBitmap.getWidth() / 2, pos[1] - mBitmap.getHeight() / 2);

        canvas.drawPath(path, mPaint);
        canvas.drawBitmap(mBitmap, mMatrix, mPaint);

        invalidate();
    }
}
