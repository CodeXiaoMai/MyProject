
package com.xiaomai.myproject.largeimage.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import com.orhanobut.logger.Logger;
import com.xiaomai.myproject.largeimage.listener.MoveGestureDetector;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by HSEDU on 2017/3/13 11:11.
 */

public class LargeImageView extends View {

    private BitmapRegionDecoder mDecoder;

    /**
     * 图片的高度和宽度
     */
    private int mImageWidth, mImageHeight;

    /**
     * 绘制的区域
     */
    private volatile Rect mRect = new Rect();

    private MoveGestureDetector mDetector;

    private ScaleGestureDetector mScaleGestureDetector;

    private static final BitmapFactory.Options OPTIONS = new BitmapFactory.Options();

    private float mCurrentScale = 1f;

    static {
        OPTIONS.inPreferredConfig = Bitmap.Config.RGB_565;
    }

    public LargeImageView(Context context) {
        this(context, null);
    }

    public LargeImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LargeImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mDetector = new MoveGestureDetector(getContext(),
                new MoveGestureDetector.SimpleMoveGestureDetector() {
                    @Override
                    public boolean onMove(MoveGestureDetector detector) {
                        int moveX = (int) detector.getMoveX();
                        int moveY = (int) detector.getMoveY();
                        if (mImageWidth > getWidth()) {
                            mRect.offset(-moveX, 0);
                            checkWidth();
                            invalidate();
                        }
                        if (mImageHeight > getHeight()) {
                            mRect.offset(0, -moveY);
                            checkHeight();
                            invalidate();
                        }
                        return true;
                    }
                });

        /*mScaleGestureDetector = new ScaleGestureDetector(getContext(),
                new ScaleGestureDetector.OnScaleGestureListener() {
                    @Override
                    public boolean onScale(ScaleGestureDetector detector) {
                        float focusX = detector.getFocusX();
                        float focusY = detector.getFocusY();
                        float scaleFactor = detector.getScaleFactor();

                        float lastScale = mCurrentScale;
                        float newScale = lastScale * scaleFactor;

                        mCurrentScale = newScale;
                        if (mImageWidth * newScale > getWidth()) {
                            mRect.left /= newScale;
                            mRect.right /= newScale;
                            mRect.top /= newScale;
                            mRect.bottom /= newScale;
                            checkWidth();
                            invalidate();
                        }
                        if (mImageHeight * newScale > getHeight()) {
                            mRect.left /= newScale;
                            mRect.right /= newScale;
                            mRect.top /= newScale;
                            mRect.bottom /= newScale;
                            checkHeight();
                            invalidate();
                        }
                        return true;
                    }

                    @Override
                    public boolean onScaleBegin(ScaleGestureDetector detector) {
                        return true;
                    }

                    @Override
                    public void onScaleEnd(ScaleGestureDetector detector) {

                    }
                });*/
    }

    private void checkWidth() {
        Rect rect = mRect;
        int imageWidth = (int) (mImageWidth * mCurrentScale);

        if (rect.right > imageWidth) {
            rect.right = imageWidth;
            rect.left = imageWidth - getWidth();
        }

        if (rect.left < 0) {
            rect.left = 0;
            rect.right = getWidth();
        }
    }

    private void checkHeight() {
        Rect rect = mRect;
        int imageHeight =  (int) (mImageWidth * mCurrentScale);

        if (rect.bottom > imageHeight) {
            rect.bottom = imageHeight;
            rect.top = imageHeight - getHeight();
        }

        if (rect.top < 0) {
            rect.top = 0;
            rect.bottom = getHeight();
        }
    }

    /**
     * 为我们的显示区域的rect赋值，大小为view的尺寸
     * 
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        int imageWidth = mImageWidth;
        int imageHeight = mImageHeight;

        // 默认直接显示图上的中心区域
        mRect.left = imageWidth / 2 - width / 2;
        mRect.top = imageHeight / 2 - height / 2;
        mRect.right = mRect.left + width;
        mRect.bottom = mRect.top + height;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Bitmap bitmap = mDecoder.decodeRegion(mRect, OPTIONS);
        canvas.drawBitmap(bitmap, 0, 0, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Logger.e(String.valueOf(event.getPointerCount()));
        /*if (event.getPointerCount() > 1) {
            mScaleGestureDetector.onTouchEvent(event);
        } else {
        }*/
        mDetector.onTouchEvent(event);
        return true;
    }

    /**
     * 设置输入流，获得图片的真实的宽度和高度，以及初始化我们的mDecoder
     * 
     * @param inputStream
     */
    public void setInputStream(InputStream inputStream) {
        try {
            mDecoder = BitmapRegionDecoder.newInstance(inputStream, false);
            BitmapFactory.Options tmpOptions = new BitmapFactory.Options();
            tmpOptions.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(inputStream, null, tmpOptions);
            mImageWidth = tmpOptions.outWidth;
            mImageHeight = tmpOptions.outHeight;

            requestLayout();
            invalidate();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null)
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}
