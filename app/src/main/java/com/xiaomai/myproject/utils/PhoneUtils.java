package com.xiaomai.myproject.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by XiaoMai on 2016/11/30 17:18.
 */
public class PhoneUtils {

    /**
     * 获取屏幕的宽度（像素）
     *
     * @param context
     * @return int
     */
    public static int getScreenWidth(Context context) {
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    /**
     * 获取屏幕的高度（像素）
     *
     * @param context
     * @return int
     */
    public static int getScreenHeight(Context context) {
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

}
