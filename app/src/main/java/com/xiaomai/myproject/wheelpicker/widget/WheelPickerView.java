
package com.xiaomai.myproject.wheelpicker.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.xiaomai.myproject.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XiaoMai on 2017/2/7 4:42.
 */
public class WheelPickerView extends ScrollView {

    public interface OnScrollListener {

        void onSelected(int selectedIndex, String item);

    }

    public static final int SCROLL_DELAY = 50;

    private Context mContext;

    private LinearLayout mViewContainer;

    private List<String> mItemList = new ArrayList<String>();

    private int mItemOffset = 2;

    private int mVisibleItemCount = 5;

    private int mSelectedIndex = 1;

    private int mInitialY = 0;

    private int mItemHeight = 0;

    private int mCurrentSound = 2;

    private Paint mBgPaint;

    private int mViewWidth;

    private boolean mSoundEnabled = true;

    private OnScrollListener mOnScrollListener;

    private Runnable mScrollerTask;

    public WheelPickerView(Context context) {
        super(context);
        init(context);
    }

    public WheelPickerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public WheelPickerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    @SuppressWarnings("deprecation")
    private void init(Context context) {
        mContext = context;
        setVerticalScrollBarEnabled(false);

        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        mViewWidth = wm.getDefaultDisplay().getWidth();
        mBgPaint = new Paint();
        mBgPaint.setColor(getResources().getColor(R.color.wheel_select_text_color));
        mBgPaint.setStrokeWidth(2);

        mViewContainer = new LinearLayout(context);
        mViewContainer.setOrientation(LinearLayout.VERTICAL);
        addView(mViewContainer);

        mScrollerTask = new Runnable() {
            public void run() {
                int newY = getScrollY();
                if (mInitialY - newY != 0) {
                    startScrollerTask();
                    return;
                }

                final int remainder = mInitialY % mItemHeight;
                final int divided = mInitialY / mItemHeight;
                if (remainder == 0) {
                    mSelectedIndex = divided + mItemOffset;
                    onWheelItemSeleted();
                } else {
                    if (remainder > mItemHeight / 2) {
                        post(new Runnable() {
                            @Override
                            public void run() {
                                smoothScrollTo(0, mInitialY - remainder + mItemHeight);
                                mSelectedIndex = divided + mItemOffset + 1;
                                onWheelItemSeleted();
                            }
                        });
                    } else {
                        post(new Runnable() {
                            @Override
                            public void run() {
                                smoothScrollTo(0, mInitialY - remainder);
                                mSelectedIndex = divided + mItemOffset;
                                onWheelItemSeleted();
                            }
                        });
                    }
                }
            }
        };
    }

    private void startScrollerTask() {
        mInitialY = getScrollY();
        postDelayed(mScrollerTask, SCROLL_DELAY);
    }

    public void setItems(List<String> list) {
        mItemList.clear();
        mItemList.addAll(list);
        for (int i = 0; i < mItemOffset; i++) {
            mItemList.add(0, "");
            mItemList.add("");
        }

        for (String item : mItemList) {
            mViewContainer.addView(createWheelItem(item));
        }
        refreshItemView(0);
    }

    public void setItemOffset(int offset) {
        mItemOffset = offset;
        mVisibleItemCount = offset * 2 + 1;
    }

    public void setSelectedItem(int position) {
        final int p = position;
        mSelectedIndex = p + mItemOffset;
        post(new Runnable() {
            @Override
            public void run() {
                mSoundEnabled = false;
                scrollTo(0, p * mItemHeight);
                mSoundEnabled = true;
                onWheelItemSeleted();
            }
        });
    }

    public void setSelectedItem(String item) {
        setSelectedItem(0);
        if (!TextUtils.isEmpty(item)) {
            for (int i = 0; i < mItemList.size() - mItemOffset * 2; i++) {
                if (item.endsWith(mItemList.get(i + mItemOffset))) {
                    setSelectedItem(i);
                    break;
                }
            }
        }
    }

    public void setOnScrollListener(OnScrollListener listener) {
        mOnScrollListener = listener;
    }

    private TextView createWheelItem(String item) {
        TextView textView = new TextView(mContext);
        textView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        textView.setSingleLine(true);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        textView.setText(item);
        textView.setGravity(Gravity.CENTER);
        int padding = getResources().getDimensionPixelSize(R.dimen.margin_large);
        textView.setPadding(padding, padding, padding, padding);
        if (mItemHeight == 0) {
            mItemHeight = getViewMeasuredHeight(textView);
            mViewContainer.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    mItemHeight * mVisibleItemCount));
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) getLayoutParams();
            setLayoutParams(
                    new LinearLayout.LayoutParams(lp.width, mItemHeight * mVisibleItemCount));
        }
        return textView;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        refreshItemView(t);
    }

    private void refreshItemView(int y) {
        int position = y / mItemHeight + mItemOffset;
        int remainder = y % mItemHeight;
        int divided = y / mItemHeight;

        if (remainder == 0) {
            position = divided + mItemOffset;
        } else if (remainder > mItemHeight / 2) {
            position = divided + mItemOffset + 1;
        }

        int childSize = mViewContainer.getChildCount();
        for (int i = 0; i < childSize; i++) {
            TextView itemView = (TextView) mViewContainer.getChildAt(i);
            if (itemView == null) {
                return;
            }
            if (position == i) {
                if (mSoundEnabled && mCurrentSound != position) {
                    mCurrentSound = position;
                    playSoundEffect(SoundEffectConstants.NAVIGATION_DOWN);
                }
                itemView.setTextColor(getResources().getColor(R.color.wheel_select_text_color));
            } else {
                itemView.setTextColor(getResources().getColor(R.color.wheel_normal_text_color));
            }
        }
        onWheelItemSeleted();
    }

    @SuppressWarnings("deprecation")
    @Override
    public void setBackgroundDrawable(Drawable background) {
        background = new Drawable() {
            @Override
            public void draw(Canvas canvas) {
                int startY = mItemHeight * mItemOffset;
                int endY = mItemHeight * (mItemOffset + 1);
                canvas.drawLine(mViewWidth * 1 / 6, startY, mViewWidth * 5 / 6, startY, mBgPaint);
                canvas.drawLine(mViewWidth * 1 / 6, endY, mViewWidth * 5 / 6, endY, mBgPaint);
            }

            @Override
            public void setAlpha(int alpha) {
            }

            @Override
            public void setColorFilter(ColorFilter cf) {
            }

            @Override
            public int getOpacity() {
                return 0;
            }
        };
        super.setBackgroundDrawable(background);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = w;
        setBackgroundDrawable(null);
    }

    private void onWheelItemSeleted() {
        if (mOnScrollListener != null) {
            mOnScrollListener.onSelected(mSelectedIndex - mItemOffset,
                    mItemList.get(mSelectedIndex));
        }
    }

    @Override
    public void fling(int velocityY) {
        super.fling(velocityY / 2);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            startScrollerTask();
        }
        return super.onTouchEvent(ev);
    }

    private int getViewMeasuredHeight(View view) {
        int width = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        view.measure(width, expandSpec);
        return view.getMeasuredHeight();
    }
}
