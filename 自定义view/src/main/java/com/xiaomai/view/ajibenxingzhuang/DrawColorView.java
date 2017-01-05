
package com.xiaomai.view.ajibenxingzhuang;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by XiaoMai on 2017/1/5 14:53.
 */
public class DrawColorView extends View {
    public DrawColorView(Context context) {
        super(context);
    }

    public DrawColorView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawColorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLUE);
    }
}
