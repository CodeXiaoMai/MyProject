
package com.xiaomai.myproject.touchevent;

import android.util.Log;
import android.view.MotionEvent;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.base.BaseActivity;
import com.xiaomai.myproject.touchevent.view.MyLinearLayout;
import com.xiaomai.myproject.touchevent.view.MyTextView;

public class TouchEventDispatchActivity extends BaseActivity {

    private static final String TAG = "Activity";

    private MyLinearLayout activity_touch_event_dispatch;

    private MyTextView tv_touch_event;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_touch_event_dispatch;
    }

    @Override
    protected void initViews() {
        super.initViews();
        activity_touch_event_dispatch = (MyLinearLayout) findViewById(
                R.id.activity_touch_event_dispatch);
        tv_touch_event = (MyTextView) findViewById(R.id.tv_touch_event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e(TAG, "dispatchTouchEvent: ");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, "onTouchEvent: ");
        return super.onTouchEvent(event);
    }

}
