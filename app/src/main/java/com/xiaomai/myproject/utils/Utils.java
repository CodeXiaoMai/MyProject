package com.xiaomai.myproject.utils;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.xiaomai.myproject.MyApplication;
import com.xiaomai.myproject.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by XiaoMai on 2016/9/2.
 */
public class Utils {

    /**
     * 获取屏幕的宽度（像素）
     *
     * @param context
     * @return int
     */
    public static int getScreenWidth(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
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
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    /**
     * 将TextView的关键词设置为特殊的颜色
     *
     * @param src
     * @param keyWords
     * @param color
     * @return
     */
    public static Spanned setKeyWordsHighLight(String src, String keyWords, String color) {
        /**
         * 截取出src中keyWords的字符串用Html标签请你包裹，
         * 再和keyWords之前和之后的字符串进行拼接
         */
        int index = src.indexOf(keyWords);
        int length = keyWords.length();
        src =
                src.substring(0, index)                     //keyWords之前的
                        + "<font color=" + color + ">"
                        + src.substring(index, index + length)
                        + "</font>"
                        + src.substring(index + length, src.length());//keyWords之后的
        return Html.fromHtml(src);
    }

    /**
     * 把Object转换为int型
     *
     * @param obj
     * @return
     */
    public static int parseToIntger(Object obj) {
        if (obj == null) {
            return 0;
        } else {
            try {
                return Integer.parseInt(obj.toString());
            } catch (Exception e) {
                return 0;
            }
        }

    }

    public static String getDate() {
        return getDate(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    public static String getDate(Date date) {
        return getDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 按指定的格式返回日期
     *
     * @param date
     * @param format
     * @return
     */
    public static String getDate(Date date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    /**
     * 获取days天的日期，并按"yyyy-MM-dd HH:mm:ss"格式返回
     *
     * @param date
     * @param days
     * @return
     */
    public static String getDateBefore(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return getDate(calendar.getTime());
    }

    /**
     * 获取days天的日期，并按"yyyy-MM-dd HH:mm:ss"格式返回
     *
     * @param days
     * @return
     */
    public static String getDateBefore(int days) {
        return getDateBefore(new Date(), days);
    }

    /**
     * 获取months个月之前的日期，并按"yyyy-MM-dd HH:mm:ss"格式返回
     *
     * @param date
     * @param months
     * @return
     */
    public static String getMonthBefore(Date date, int months) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, months);
        return getDate(calendar.getTime());
    }

    /**
     * 获取months个月之前的日期，并按"yyyy-MM-dd HH:mm:ss"格式返回
     *
     * @param months
     * @return
     */
    public static String getMonthBefore(int months) {
        return getMonthBefore(new Date(), months);
    }

    /**
     * 获取years年之前的日期，并按"yyyy-MM-dd HH:mm:ss"格式返回
     *
     * @param date
     * @param years
     * @return
     */
    public static String getYearBefore(Date date, int years) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, years);
        return getDate(calendar.getTime());
    }

    /**
     * 获取years年之前的日期，并按"yyyy-MM-dd HH:mm:ss"格式返回
     *
     * @param years
     * @return
     */
    public static String getYearBefore(int years) {
        return getYearBefore(new Date(), years);
    }

    /**
     * 获取短时间：eg：刚刚、n分钟前，n小时前，n天前。。。
     *
     * @param date
     * @return
     */
    public static String getShortTime(Date date) {
        return getShortTime(getDate(date));
    }

    /**
     * 获取短时间：eg：刚刚、n分钟前，n小时前，n天前。。。
     *
     * @param date
     * @return
     */
    public static String getShortTime(String date) {
        Date descDate = new Date();
        return getShortTime(date, getDate(descDate));
    }

    /**
     * 获取短时间：eg：刚刚、n分钟前，n小时前，n天前。。。
     * @param srcDateStr
     * @param descDateStr
     * @return
     */
    public static String getShortTime(String srcDateStr, String descDateStr) {
        //时间差单位分钟
        long timeDiff = -1;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        try {
            Date srcDate = dateFormat.parse(srcDateStr);
            Date descDate = dateFormat.parse(descDateStr);
            timeDiff = (descDate.getTime() - srcDate.getTime()) / (1000 * 60);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (timeDiff < 0) {
            return srcDateStr;
        } else if (timeDiff == 0) {
            return MyApplication.getContext().getResources().getString(R.string.time_now);
        } else if (timeDiff <= 59) {
            return timeDiff + MyApplication.getContext().getResources().getString(R.string.time_minute_before);
        } else if (timeDiff < (60 * 24)) {
            return timeDiff / 60 + MyApplication.getContext().getResources().getString(R.string.time_hour_before);
        } else if (timeDiff < (60 * 24 * 2)) {
            return MyApplication.getContext().getResources().getString(R.string.time_yesterday);
        } else if (timeDiff < (60 * 24 * 3)) {
            return MyApplication.getContext().getResources().getString(R.string.time_theDayBeforeYesterday);
        } else if (timeDiff < (60 * 24 * 30)) {
            return timeDiff / 60 / 24 + MyApplication.getContext().getResources().getString(R.string.time_day_before);
        } else {
            return srcDateStr;
        }
    }
}
