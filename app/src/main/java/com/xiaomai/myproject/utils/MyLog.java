package com.xiaomai.myproject.utils;

import android.util.Log;

/**
 * 这是Log工具类
 * Created by XiaoMai on 2016/9/4.
 */
public class MyLog {

    private static final String TAG = "xiaomai";

    public static void i(String message) {
        if (Const.IS_SHOW_LOG) {
            Log.i(TAG, message);
        }
    }

    public static void v(String message) {
        if (Const.IS_SHOW_LOG) {
            Log.v(TAG, message);
        }
    }

    public static void d(String message) {
        if (Const.IS_SHOW_LOG) {
            Log.d(TAG, message);
        }
    }

    public static void e(String message) {
        if (Const.IS_SHOW_LOG) {
            Log.e(TAG, message);
        }
    }

    public static void e(String tag, String message) {
        if (Const.IS_SHOW_LOG) {
            Log.e(tag, message);
        }
    }
}
