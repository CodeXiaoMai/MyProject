
package com.xiaomai.view.cCanvasDrawPictureAndText;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by XiaoMai on 2017/1/6 15:17.
 */
public class CanvasDrawTextView extends View {

    private Paint mPaint;

    public CanvasDrawTextView(Context context) {
        super(context);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(50);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        String str = "ABCDEFGHIJK";
        // 参数分别为（文本，基线x，基线y，画笔）
        canvas.drawText(str, 100, 200, mPaint);
        // 参数分别为 (字符串 开始截取位置 结束截取位置 基线x 基线y 画笔)
        canvas.drawText(str, 1, 3, 100, 300, mPaint);
        // 另外，对于字符数组char[]我们截取字符串使用起始位置(index)和长度(count)来确定。
        // 同样，我们指定index为1，count为3，那么最终截取到的字符串是"BCD".
        char[] chars = str.toCharArray();
        // 参数为 (字符数组 起始坐标 截取长度 基线x 基线y 画笔)
        canvas.drawText(chars, 1, 3, 100, 400, mPaint);
    }
}
