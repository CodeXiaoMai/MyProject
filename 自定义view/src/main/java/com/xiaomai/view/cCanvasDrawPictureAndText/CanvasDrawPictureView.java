
package com.xiaomai.view.cCanvasDrawPictureAndText;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;

import com.xiaomai.view.R;

/**
 * Created by XiaoMai on 2017/1/6 10:50.</br>
 * 使用Picture前请关闭硬件加速，以免引起不必要的问题！</br>
 * 使用Picture前请关闭硬件加速，以免引起不必要的问题！</br>
 * 使用Picture前请关闭硬件加速，以免引起不必要的问题！
 */
public class CanvasDrawPictureView extends View {

    private Picture mPicture = new Picture();

    private Context mContext;

    public CanvasDrawPictureView(Context context) {
        super(context);
        mContext = context;
        recording();
    }

    /**
     * 录制内容方法
     */
    private void recording() {
        // 开始录制
        Canvas canvas = mPicture.beginRecording(getWidth(), getHeight());
        // 创建一个画笔
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);

        // 在Canvas中具体操作
        canvas.drawCircle(0, 0, 200, paint);
        mPicture.endRecording();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(getWidth() / 2, getHeight() / 2);
        // 这种方法在比较低版本的系统上绘制后可能会影响Canvas状态，所以这种方法一般不会使用
        // mPicture.draw(canvas);
        // PictureDrawable pictureDrawable = new PictureDrawable(mPicture);
        // pictureDrawable.setBounds(0, -100, 200, 200);
        // pictureDrawable.draw(canvas);
        // canvas.drawPicture(mPicture, new RectF(-10, -10, 10, 10));

        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_launcher);
        // canvas.drawBitmap(bitmap, new Matrix(), new Paint());
        canvas.drawBitmap(bitmap, new Rect(0, 0, bitmap.getWidth() / 2, bitmap.getHeight() / 2),
                new RectF(0, 0, 200, 200), null);
    }
}
