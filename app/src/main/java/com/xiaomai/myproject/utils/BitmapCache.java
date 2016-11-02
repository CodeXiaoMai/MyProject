package com.xiaomai.myproject.utils;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by XiaoMai on 2016/9/24.
 */
public class BitmapCache implements ImageLoader.ImageCache {

    private LruCache<String, Bitmap> mLruCache;

    public BitmapCache() {

        int maxSize = 10 * 1024 * 1024;

        mLruCache = new LruCache<String, Bitmap>(maxSize) {

            @Override
            protected int sizeOf(String key, Bitmap bitmap) {

                /**
                 * 1、getRowBytes：Since API Level 1，用于计算位图每一行所占用的内存字节数。
                 * 2、getByteCount：Since API Level 12，用于计算位图所占用的内存字节数。
                 *
                 * getByteCount() = getRowBytes() * getHeight()，
                 * 也就是说位图所占用的内存空间数等于位图的每一行所占用的空间数乘以位图的行数。
                 * 因为getByteCount要求的API版本较高，
                 * 因此对于使用较低版本的开发者，在计算位图所占空间时上面的方法或许有帮助。
                 */

                int rowBytes = bitmap.getRowBytes();

                int height = bitmap.getHeight();

                int byteCount = rowBytes * height;

                return byteCount;

            }
        };
    }

    @Override
    public Bitmap getBitmap(String url) {
        return mLruCache.get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        mLruCache.put(url, bitmap);
    }
}