
package com.xiaomai.myproject.utils;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.widget.TextView;

import com.xiaomai.myproject.R;

/**
 * Created by XiaoMai on 2016/12/5 10:18.
 */
public class ViewUtils {

    /**
     * 设置TextView背景边界的颜色（空心）
     * 
     * @param context
     * @param textView
     * @param normalRes 默认的背景
     * @param selectRes 点击的背景
     * @param backgroundColor 填充颜色
     * @param textColor
     * @param selector 是否可点击
     */
    public static void setStrokeCorner(Context context, TextView textView, int normalRes,
            int selectRes, int backgroundColor, int textColor, boolean selector) {
        if (backgroundColor == 0) {
            backgroundColor = context.getResources().getColor(R.color.textBlue);
        }
        if (textColor == 0) {
            textColor = backgroundColor;
        }
        GradientDrawable normalDrawable = (GradientDrawable) context.getResources()
                .getDrawable(normalRes);
        if (normalDrawable != null)
            normalDrawable.setStroke(context.getResources().getDimensionPixelSize(R.dimen.dp_1),
                    backgroundColor);
        StateListDrawable stateListDrawable = new StateListDrawable();
        if (selector) {
            GradientDrawable pressDrawable = (GradientDrawable) context.getResources()
                    .getDrawable(selectRes);
            stateListDrawable.addState(new int[] {
                    android.R.attr.state_pressed
            }, pressDrawable);

        }
        stateListDrawable.addState(new int[] {}, normalDrawable);
        textView.setBackgroundDrawable(stateListDrawable);
        textView.setTextColor(textColor);
    }

    /**
     * 设置TextView背景边界的颜色（实心）
     * 
     * @param context
     * @param textView
     * @param normalRes
     * @param selectRes
     * @param backgroundColor
     * @param textColor
     * @param selector
     */
    public static void setSolidCorner(Context context, TextView textView, int normalRes,
            int selectRes, int backgroundColor, int textColor, boolean selector) {
        if (backgroundColor == 0) {
            backgroundColor = context.getResources().getColor(R.color.textBlue);
        }
        if (textColor == 0) {
            textColor = context.getResources().getColor(R.color.white);
        }
        GradientDrawable normalDrawable = (GradientDrawable) context.getResources()
                .getDrawable(normalRes);
        if (normalDrawable != null)
            normalDrawable.setColor(backgroundColor);
        StateListDrawable stateListDrawable = new StateListDrawable();
        if (selector) {
            GradientDrawable pressDrawable = (GradientDrawable) context.getResources()
                    .getDrawable(selectRes);
            stateListDrawable.addState(new int[] {
                    android.R.attr.state_pressed
            }, pressDrawable);

        }
        stateListDrawable.addState(new int[] {}, normalDrawable);
        textView.setBackgroundDrawable(stateListDrawable);
        textView.setTextColor(textColor);
    }
}
