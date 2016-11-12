package com.xiaomai.myproject.view;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

/**
 * Created by XiaoMai on 2016/11/7 16:53.
 */
public class MyBehavior extends CoordinatorLayout.Behavior<Button>{

    private int width;

    public MyBehavior(Context context, AttributeSet attrs){
        super(context, attrs);
        width = context.getResources().getDisplayMetrics().widthPixels;
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, Button child, View dependency) {
        return dependency instanceof TempView;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, Button child, View dependency) {
        int top = dependency.getTop();
        int left = dependency.getLeft();
        int x = width - left - child.getWidth();
        int y = top;
        setPosition(child, x, y);
        return true;
    }

    private void setPosition(Button child, int x, int y) {
        CoordinatorLayout.MarginLayoutParams layoutParams = (CoordinatorLayout.MarginLayoutParams) child.getLayoutParams();
        layoutParams.leftMargin = x;
        layoutParams.topMargin = y;
        child.setLayoutParams(layoutParams);
    }
}