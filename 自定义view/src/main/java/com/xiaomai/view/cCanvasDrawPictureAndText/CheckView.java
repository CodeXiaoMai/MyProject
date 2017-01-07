
package com.xiaomai.view.cCanvasDrawPictureAndText;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import com.xiaomai.view.R;

/**
 * Created by XiaoMai on 2017/1/6 13:36.
 */
public class CheckView extends View {

    // 动画状态-没有
    private static final int ANIM_NULL = 0;

    // 动画状态-开启
    private static final int ANIM_CHECK = 1;

    // 动画状态-结束
    private static final int ANIM_UNCHECK = 2;

    private Context mContext;

    private int mWidth;

    private int mHeight;

    private Handler mHandler;

    private Paint mPaint;

    private Bitmap mBitmap;

    private int mCurrentPage = -1;

    private int mPageCounts = 13;

    private int mDuration = 500;

    private int mState = ANIM_NULL;

    private boolean isCheck = false;

    public CheckView(Context context) {
        this(context, null);
    }

    public CheckView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CheckView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mPaint = new Paint();
        mPaint.setColor(0xffFF5317);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);

        mBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.checkmark);
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (mCurrentPage < mPageCounts && mCurrentPage >= 0) {
                    invalidate();
                    if (mState == ANIM_NULL) {
                        return;
                    }
                    if (mState == ANIM_CHECK) {
                        mCurrentPage++;
                    } else if (mState == ANIM_UNCHECK) {
                        mCurrentPage--;
                    }
                    sendEmptyMessageDelayed(0, mDuration / mPageCounts);
                } else {
                    if (isCheck) {
                        mCurrentPage = mPageCounts - 1;
                    } else {
                        mCurrentPage = -1;
                    }
                    invalidate();
                    mState = ANIM_NULL;
                }
            }
        };
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);
        // 绘制背景圆形
        canvas.drawCircle(0, 0, 240, mPaint);
        // 图像的边长
        int sideLength = mBitmap.getHeight();
        // 得到图像选区和实际绘制位置
        Rect rect = new Rect(sideLength * mCurrentPage, 0, sideLength * (mCurrentPage + 1),
                sideLength);
        Rect des = new Rect(-100, -100, 100, 100);
        canvas.drawBitmap(mBitmap, rect, des, null);
    }

    public void check() {
        if (mState != ANIM_NULL || isCheck) {
            return;
        }
        mState = ANIM_CHECK;
        mCurrentPage = 0;
        mHandler.sendEmptyMessageDelayed(0, mDuration / mPageCounts);
        isCheck = true;
    }

    public void unCheck() {
        if (mState != ANIM_NULL || !isCheck) {
            return;
        }
        mState = ANIM_UNCHECK;
        mCurrentPage = mPageCounts - 1;
        mHandler.sendEmptyMessageDelayed(0, mDuration / mPageCounts);
        isCheck = false;
    }
}
