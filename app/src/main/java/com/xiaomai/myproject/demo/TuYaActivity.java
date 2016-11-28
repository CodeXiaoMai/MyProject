
package com.xiaomai.myproject.demo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.xiaomai.myproject.R;

/**
 * Created by XiaoMai on 2016/11/20.
 */

public class TuYaActivity extends Activity {

    private Button button_save, button_resume;

    private ImageView iv_canvas;

    private Bitmap baseBitmap;

    private Canvas canvas;

    // 声明画笔
    private Paint mPaint;

    // 手势监听器
    private View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        // 表示手指在屏幕上X轴Y轴的坐标点
        float startX;

        float startY;

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    // No.1
                    // 开始写代码，请用代码实现初始化Bitmap对象、定义画布颜色（请定义为白色）、
                    // 记录用户手指在屏幕上的触摸点坐标 将坐标存入到startX和startY变量中。

                    startX = event.getRawX();
                    startY = event.getRawY()  - 40;
                    // end_code
                    break;
                case MotionEvent.ACTION_MOVE:
                    // No.2
                    // 开始写代码，根据手指在屏幕滑动的轨迹实现绘制轨迹的线条，并更新手指在屏幕上的坐标点（startX和startY变量），
                    // 将绘制好的图像展示在ImageView中。
                    float stopX = event.getRawX();
                    float stopY = event.getRawY() - 40;
                    canvas.drawLine(startX, startY, stopX, stopY, mPaint);
                    iv_canvas.invalidate();
                    startX = stopX;
                    startY = stopY;
                    // end_code
                    break;
                case MotionEvent.ACTION_UP:

                    break;

                default:
                    break;
            }
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuya);

        iv_canvas = (ImageView) findViewById(R.id.iv_canvas);
        button_save = (Button) findViewById(R.id.button_save);
        button_resume = (Button) findViewById(R.id.button_resume);
        // 保存按钮的监听器
        button_save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                saveBitmap();
            }
        });
        // 清除按钮的监听器
        button_resume.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                resumeCanvas();
            }
        });
        iv_canvas.setOnTouchListener(onTouchListener);

        // 初始化画笔 设置画笔的宽度和颜色
        mPaint = new Paint();
        mPaint.setStrokeWidth(5);
        mPaint.setColor(Color.RED);

        baseBitmap = Bitmap.createBitmap(480, 800, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(baseBitmap);
        canvas.drawColor(Color.WHITE);

        iv_canvas.setImageBitmap(baseBitmap);

    }

    // 保存图片的方法
    protected void saveBitmap() {
        try {
            // 以png的格式保存的手机内存中
            // No.3
            // 开始写代码，请在这里实现文件存储的方法，将手指在屏幕上涂鸦的图形以PNG图片的形式保存到SD卡中；并发送广播进行扫描SD卡。

            // File file

            // end_code
        } catch (Exception e) {
            Toast.makeText(TuYaActivity.this, "保存图片失败", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    // 清除画布的方法
    protected void resumeCanvas() {
        if (baseBitmap != null) {
            baseBitmap = Bitmap.createBitmap(iv_canvas.getWidth(), iv_canvas.getHeight(),
                    Bitmap.Config.ARGB_8888);
            canvas = new Canvas(baseBitmap);
            canvas.drawColor(Color.WHITE);
            iv_canvas.setImageBitmap(baseBitmap);
            Toast.makeText(TuYaActivity.this, "清除画板成功，可以重新开始绘图", Toast.LENGTH_LONG).show();
        }
    }
}
