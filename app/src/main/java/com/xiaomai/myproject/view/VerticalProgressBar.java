
package com.xiaomai.myproject.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ProgressBar;

/**
 * Created by XiaoMai on 2017/1/13 10:29.
 */
public class VerticalProgressBar extends ProgressBar {
    public VerticalProgressBar(Context context) {
        super(context);
    }

    public VerticalProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VerticalProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        // 反转90度，将ProgressBar竖起来
        canvas.rotate(-90);
        super.onDraw(canvas);
    }
}
