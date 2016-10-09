
package com.xiaomai.myproject.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.adapter.DragListAdapter;


public class DragListView extends ListView {

    private static final int ANIMATION_DURATION = 200;

    private final static int SCROLL_STEP = 1;

    private WindowManager mWindowManager;

    private WindowManager.LayoutParams mWindowParams;

    private ImageView mDragView;

    private int mStartPosition;

    private int mDragPosition;

    private int mLastPosition;

    private int mDragPoint;

    private int mDragOffset;

    private int mUpScrollBounce;

    private int mDownScrollBounce;

    private int mCurrentStep;

    private boolean mIsItemMoving = false;

    private int mItemVerticalSpacing = 0;

    private boolean mHasGetSapcing = false;

    private boolean mIsSameDirection = true;

    private int mLastFlag = -1; // -1,0 == down,1== up

    private int mHoldPosition;

    public DragListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setLayerType(View.LAYER_TYPE_HARDWARE, null);
        mWindowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
    }

    private void getItemVerticalSpacing() {
        mHasGetSapcing = true;
        mUpScrollBounce = getHeight() / 3;
        mDownScrollBounce = getHeight() * 2 / 3;
        int[] tempLocation0 = new int[2];
        int[] tempLocation1 = new int[2];
        ViewGroup itemView0 = (ViewGroup) getChildAt(0);
        ViewGroup itemView1 = (ViewGroup) getChildAt(1);
        if (itemView0 == null) {
            return;
        }
        itemView0.getLocationOnScreen(tempLocation0);
        if (itemView1 == null) {
            return;
        }
        itemView1.getLocationOnScreen(tempLocation1);
        mItemVerticalSpacing = Math.abs(tempLocation1[1] - tempLocation0[1]);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN && !mIsItemMoving) {
            int x = (int) ev.getX();
            int y = (int) ev.getY();
            mLastPosition = mStartPosition = mDragPosition = pointToPosition(x, y);
            if (mDragPosition == AdapterView.INVALID_POSITION) {
                return super.onInterceptTouchEvent(ev);
            }
            if (!mHasGetSapcing) {
                getItemVerticalSpacing();
            }
            ViewGroup dragger = (ViewGroup) getChildAt(mDragPosition - getFirstVisiblePosition());
            DragListAdapter adapter = (DragListAdapter) getAdapter();
            mDragPoint = y - dragger.getTop();
            mDragOffset = (int) (ev.getRawY() - y);
            View draggerIcon = dragger.findViewById(R.id.drag_list_item_image);
            if (draggerIcon != null && x > draggerIcon.getLeft() - 20) {
                dragger.destroyDrawingCache();
                dragger.setDrawingCacheEnabled(true);
                dragger.setBackgroundColor(0x55555555);
                Bitmap bm = Bitmap.createBitmap(dragger.getDrawingCache(true));
                adapter.setInvisiblePosition(mStartPosition);
                adapter.showDropItem(false);
                adapter.copyList();
                adapter.notifyDataSetChanged();
                startDrag(bm, y);
                mIsItemMoving = false;
            }
            return false;
        }
        return super.onInterceptTouchEvent(ev);
    }

    public Animation getScaleAnimation() {
        Animation scaleAnimation = new ScaleAnimation(0.0f, 0.0f, 0.0f, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setFillAfter(true);
        return scaleAnimation;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (mDragView != null && mDragPosition != INVALID_POSITION) {
            int action = ev.getAction();
            switch (action) {
                case MotionEvent.ACTION_UP:
                    int upY = (int) ev.getY();
                    stopDrag();
                    onDrop(upY);
                    break;
                case MotionEvent.ACTION_MOVE:
                    int moveY = (int) ev.getY();
                    onDrag(moveY);
                    itemMoveAnimation(moveY);
                    break;
                case MotionEvent.ACTION_DOWN:
                    onDrag((int) ev.getY());
                    break;
                default:
                    break;
            }
            return true;
        }
        return super.onTouchEvent(ev);
    }

    private void onChangeCopy(int last, int current) {
        DragListAdapter adapter = (DragListAdapter) getAdapter();
        if (last != current) {
            adapter.exchangeCopy(last, current);
        }
    }

    private void itemMoveAnimation(int y) {
        final DragListAdapter adapter = (DragListAdapter) getAdapter();
        int tempPosition = pointToPosition(0, y);
        if (tempPosition == INVALID_POSITION || tempPosition == mLastPosition) {
            return;
        }
        mDragPosition = tempPosition;
        onChangeCopy(mLastPosition, mDragPosition);
        int MoveNum = tempPosition - mLastPosition;
        int count = Math.abs(MoveNum);
        for (int i = 1; i <= count; i++) {
            int xAbsOffset, yAbsOffset;
            if (MoveNum > 0) {
                if (mLastFlag == -1) {
                    mLastFlag = 0;
                    mIsSameDirection = true;
                }
                if (mLastFlag == 1) {
                    mLastFlag = 0;
                    mIsSameDirection = !mIsSameDirection;
                }
                if (mIsSameDirection) {
                    mHoldPosition = mLastPosition + 1;
                } else {
                    if (mStartPosition < tempPosition) {
                        mHoldPosition = mLastPosition + 1;
                        mIsSameDirection = !mIsSameDirection;
                    } else {
                        mHoldPosition = mLastPosition;
                    }
                }
                xAbsOffset = 0;
                yAbsOffset = -mItemVerticalSpacing;
                mLastPosition++;
            } else {
                if (mLastFlag == -1) {
                    mLastFlag = 1;
                    mIsSameDirection = true;
                }
                if (mLastFlag == 0) {
                    mLastFlag = 1;
                    mIsSameDirection = !mIsSameDirection;
                }
                if (mIsSameDirection) {
                    mHoldPosition = mLastPosition - 1;
                } else {
                    if (mStartPosition > tempPosition) {
                        mHoldPosition = mLastPosition - 1;
                        mIsSameDirection = !mIsSameDirection;
                    } else {
                        mHoldPosition = mLastPosition;
                    }
                }
                xAbsOffset = 0;
                yAbsOffset = mItemVerticalSpacing;
                mLastPosition--;
            }

            adapter.setHeight(mItemVerticalSpacing);
            adapter.setIsSameDragDirection(mIsSameDirection);
            adapter.setLastFlag(mLastFlag);
            ViewGroup moveView = (ViewGroup) getChildAt(mHoldPosition - getFirstVisiblePosition());

            Animation animation;
            if (mIsSameDirection) {
                animation = getFromSelfAnimation(xAbsOffset, yAbsOffset);
            } else {
                animation = getToSelfAnimation(xAbsOffset, -yAbsOffset);
            }
            moveView.startAnimation(animation);
        }
    }

    private void onDrop(int x, int y) {
        final DragListAdapter adapter = (DragListAdapter) getAdapter();
        adapter.setInvisiblePosition(-1);
        adapter.showDropItem(true);
        adapter.notifyDataSetChanged();
    }

    private void startDrag(Bitmap bm, int y) {
        mWindowParams = new WindowManager.LayoutParams();
        mWindowParams.gravity = Gravity.TOP;
        mWindowParams.x = 0;
        mWindowParams.y = y - mDragPoint + mDragOffset;
        mWindowParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        mWindowParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        mWindowParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
        mWindowParams.windowAnimations = 0;
        mWindowParams.alpha = 0.8f;
        mWindowParams.format = PixelFormat.TRANSLUCENT;
        ImageView imageView = new ImageView(getContext());
        imageView.setImageBitmap(bm);
        mWindowManager.addView(imageView, mWindowParams);
        mDragView = imageView;
    }

    public void onDrag(int y) {
        int drag_top = y - mDragPoint;
        if (mDragView != null && drag_top >= 0) {
            mWindowParams.alpha = 1.0f;
            mWindowParams.y = y - mDragPoint + mDragOffset;
            mWindowManager.updateViewLayout(mDragView, mWindowParams);
        }
        if (pointToPosition(0, y) != INVALID_POSITION) {
            mDragPosition = pointToPosition(0, y);
        }
        doScroller(y);
    }

    public void doScroller(int y) {
        if (y < mUpScrollBounce) {
            mCurrentStep = SCROLL_STEP + (mUpScrollBounce - y) / 10;
        } else if (y > mDownScrollBounce) {
            mCurrentStep = -(SCROLL_STEP + (y - mDownScrollBounce)) / 10;
        } else {
            mCurrentStep = 0;
        }
        View view = getChildAt(mDragPosition - getFirstVisiblePosition());
        setSelectionFromTop(mDragPosition, view.getTop() + mCurrentStep);
    }

    public void stopDrag() {
        mIsItemMoving = false;
        if (mDragView != null) {
            mWindowManager.removeView(mDragView);
            mDragView = null;
        }
        mIsSameDirection = true;
        mLastFlag = -1; // -1,0 == down,1== up
        DragListAdapter adapter = (DragListAdapter) getAdapter();
        adapter.setLastFlag(mLastFlag);
        adapter.pastList();
    }

    public void onDrop(int y) {
        onDrop(0, y);
    }

    public Animation getFromSelfAnimation(int x, int y) {
        TranslateAnimation go = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0,
                Animation.ABSOLUTE, x, Animation.RELATIVE_TO_SELF, 0, Animation.ABSOLUTE, y);
        go.setInterpolator(new AccelerateDecelerateInterpolator());
        go.setFillAfter(true);
        go.setDuration(ANIMATION_DURATION);
        go.setInterpolator(new AccelerateInterpolator());
        return go;
    }

    public Animation getToSelfAnimation(int x, int y) {
        TranslateAnimation go = new TranslateAnimation(Animation.ABSOLUTE, x,
                Animation.RELATIVE_TO_SELF, 0, Animation.ABSOLUTE, y, Animation.RELATIVE_TO_SELF,
                0);
        go.setInterpolator(new AccelerateDecelerateInterpolator());
        go.setFillAfter(true);
        go.setDuration(ANIMATION_DURATION);
        go.setInterpolator(new AccelerateInterpolator());
        return go;
    }
}
