
package com.xiaomai.myproject.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Scroller;

import com.xiaomai.myproject.R;

/**
 * Created by XiaoMai on 2016/11/22 19:05.
 * <p/>
 * 这是关于View的学习笔记
 */
public class ViewDemoActivity extends Activity {

    private static final String TAG = "ViewDemoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        startActivity(new Intent(this, ListViewDemoActivity.class));
        super.onCreate(savedInstanceState);
        // [ˈdʒɛstʃɚ] [dɪˈtɛktɚ]
        final GestureDetector gestureDetector = new GestureDetector(this, gestureListener);
        // 解决长按屏幕后无法拖动的现象
        gestureDetector.setIsLongpressEnabled(false);
        View view = new View(this);

        view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // 触摸事件是否被消费
                boolean isConsumed = gestureDetector.onTouchEvent(event);
                VelocityTracker velocityTracker = VelocityTracker.obtain();
                // 追加当前事件的速度
                velocityTracker.addMovement(event);
                velocityTracker.computeCurrentVelocity(1000);
                int xVelocity = (int) velocityTracker.getXVelocity();
                int yVelocity = (int) velocityTracker.getYVelocity();
                Log.i(TAG, "onTouch: X = " + xVelocity + ", Y = " + yVelocity);
                velocityTracker.clear();
                velocityTracker.recycle();
                return isConsumed;
            }
        });

        setContentView(R.layout.act_my_circle_imageview);
    }

    private void smoothScrollTo(View view, int destX, int destY) {
        // 获取view的左边距
        int scrollX = view.getScrollX();
        int delta = destX - scrollX;
        Scroller scroller = new Scroller(this);
        // 1000ms内滑动到destX，效果就是慢慢滑动
        scroller.startScroll(scrollX, 0, destX, 0, 1000);
        view.invalidate();
        // 需要重写view的computeScroll()方法
    }

    private GestureDetector.OnGestureListener gestureListener = new GestureDetector.OnGestureListener() {
        @Override
        public boolean onDown(MotionEvent e) {
            Log.i(TAG, "onDown: 手指轻轻触摸屏幕的一瞬间，由1个ACTION_DOWN触发");
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {
            Log.i(TAG, "onShowPress: 手指轻轻触摸屏幕，尚未松开或拖动");
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            Log.i(TAG, "onSingleTapUp: 手指轻轻触摸屏幕后松开");
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.i(TAG, "onScroll: 手指按下屏幕并拖动");
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            Log.i(TAG, "onLongPress: 长按屏幕不放");
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.i(TAG, "onFling: 手指按下屏幕、快速滑动后松开");
            return false;
        }
    };

    private GestureDetector.OnDoubleTapListener doubleTapListener = new GestureDetector.OnDoubleTapListener() {
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            Log.i(TAG, "onSingleTapConfirmed: 严格的单击行为");
            return false;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Log.i(TAG, "onDoubleTap: 双击，由2次连续的点击组成，它不可能和onSingleTapConfirmed共存");
            return false;
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            Log.i(TAG, "onDoubleTapEvent: 发生了双击行为");
            return false;
        }
    };
}
