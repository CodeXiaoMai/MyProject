package com.xiaomai.myproject.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.Uri;
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
import java.util.List;
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
    public static int parseToInteger(Object obj) {
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

    /**
     * 把Object转换喂long型
     *
     * @param obj
     * @return
     */
    public static long parseToLong(Object obj) {
        if (obj == null) {
            return 0;
        } else {
            try {
                return Long.parseLong(obj.toString());
            } catch (Exception e) {
                return 0;
            }
        }
    }

    public static String getTime() {
        return getDate(new Date(), "HH:mm");
    }

    public static String getTime(long millionSecond) {
        return getDate(new Date(millionSecond), "HH:mm");
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
     *
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

    /**
     * 将时间戳装换为时间
     *
     * @param milliSecond
     * @return
     */
    public static String parseTimeStamp(String milliSecond) {
        return parseTimeStamp(parseToLong(milliSecond));
    }

    /**
     * 将时间戳装换为时间
     *
     * @param milliSecond
     * @return
     */
    public static String parseTimeStamp(long milliSecond) {
        if (milliSecond <= 0) {
            return "milliSecond不能小于0";
        }
        Date date = new Date(milliSecond * 1000);
        return getDate(date);
    }

    /**
     * Dip转换为像素
     *
     * @param context
     * @param dpValue
     * @return
     */
    public static int dip2px(Context context, float dpValue) {
        /**
         * 获取屏幕的像素密度
         */
        final float scale = context.getResources().getDisplayMetrics().density;
        if (dpValue > 0) {
            return (int) (dpValue * scale + 0.5f);
        } else {
            return (int) (dpValue * scale - 0.5f);
        }
    }

    /**
     * 判断某软件是否已经安装
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean hasInstall(Context context, String packageName) {
        List<PackageInfo> packageInfoList = context.getPackageManager().getInstalledPackages(0);
        if (packageInfoList != null) {
            for (int i = 0; i < packageInfoList.size(); i++) {
                if (packageInfoList.get(i).packageName.equals(packageName)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 打开QQ
     *
     * @param context
     */
    public static void openQQ(Context context) {
        if (hasInstall(context, "com.tencent.mobileqq")) {
            String qqNumber = "10000";
            String url = "mqqwpa://im/chat?chat_type=wpa&uin=" + qqNumber;
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        }
    }

    /**
     * 打开某个App
     *
     * @param context
     * @param packageName
     * @param url
     */
    public static void openApp(Context context, String packageName, String url) {
        if (hasInstall(context, packageName)) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        }
    }

    /**
     * 打开拨号键盘，不拨打电话
     *
     * @param context
     * @param phoneNumber
     */
    public static void openDial(Context context, String phoneNumber) {
        openDial(context, phoneNumber, false);
    }

    /**
     * 直接拨打电话
     *
     * @param context
     * @param phoneNumber
     */
    public static void call(Context context, String phoneNumber) {
        openDial(context, phoneNumber, true);
    }

    /**
     * @param context
     * @param phoneNumber
     * @param call        true：直接拨打电话,false：打开拨号界面
     */
    public static void openDial(Context context, String phoneNumber, boolean call) {
        Intent intent = new Intent();
        if (call) {
            intent.setAction(Intent.ACTION_CALL);
        } else {
            intent.setAction(Intent.ACTION_DIAL);
        }
        intent.setData(Uri.parse("tel:" + phoneNumber));
        context.startActivity(intent);
    }

    /**
     * 调起系统发送短信的功能
     * @param context
     * @param phoneNumber   接收号码
     * @param content       短信内容
     */
    public static void sendSMS(Context context, String phoneNumber, String content){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:" + phoneNumber));
        intent.putExtra("sms_body", content);
        context.startActivity(intent);
    }

    /**
     * 调起系统发送短信的功能
     * @param context
     * @param phoneNumber    接收号码
     */
    public static void sendSMS(Context context, String phoneNumber){
        sendSMS(context, phoneNumber, "");
    }

    /**
     * 打开浏览器
     * @param context
     * @param url
     */
    public static void openBrowser(Context context, String url){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        context.startActivity(intent);
    }

    /**
     * 打开地图
     * geo:latitude,longitude   纬度、经度
     * geo:latitude,longitude?z=zoom，       纬度、经度，z表示zoom级别，值为数字1到23
     * geo:0,0?q=my+street+address
     * geo:0,0?q=business+near+city
     * @param context
     * @param uri
     */
    public static void openMap(Context context, String uri){
        Uri mUri = Uri.parse(uri);
        Intent mIntent = new Intent(Intent.ACTION_VIEW,mUri);
        context.startActivity(mIntent);
    }

    /**
     * 根据关键词到应用商店搜索应用
     * @param context
     * @param packageName
     */
    public static void searchApp(Context context, String packageName){
        Uri uri = Uri.parse("market://search?q=" + packageName);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        context.startActivity(intent);
    }

    /**
     * 在应用商店中根据AppId显示应用的相关信息
     * @param context
     * @param appId
     */
    public static void searchDetails(Context context,String appId){
        Uri uri = Uri.parse("market://details?id=" + appId);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        context.startActivity(intent);
    }

    /**
     * 卸载某个应用
     * @param context
     * @param packageName
     */
    public static void unInstallApp(Context context,String packageName){
        Uri uri = Uri.fromParts("package", packageName, null);
        Intent intent = new Intent(Intent.ACTION_DELETE, uri);
        context.startActivity(intent);
    }
}
