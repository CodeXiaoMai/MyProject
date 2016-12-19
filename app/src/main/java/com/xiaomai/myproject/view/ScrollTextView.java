
package com.xiaomai.myproject.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Scroller;
import android.widget.TextView;

/**
 * Created by XiaoMai on 2016/12/16 11:51.
 */
public class ScrollTextView extends TextView {

    private Context mContent;

    private Scroller mScroller;

    public ScrollTextView(Context context) {
        this(context, null);
    }

    public ScrollTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScrollTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContent = context;
        mScroller = new Scroller(mContent);
    }

    public void smoothScrollTo(int detX, int detY, int duration) {
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int deltaX = detX - scrollX;
        int deltaY = detY - scrollY;
        mScroller.startScroll(scrollX, scrollY, deltaX, deltaY, duration);
        // invalidate()会触发onDraw()方法，onDraw()方法会调用computeScroll()方法。
        invalidate();
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }
}
