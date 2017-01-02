
package com.xiaomai.myproject.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import com.xiaomai.myproject.BuildConfig;
import com.xiaomai.myproject.MyApplication;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.Date;

/**
 * Created by XiaoMai on 2017/1/2 10:44.
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private static final String TAG = "CrashHandler";

    private static final String LOG_PATH = FileUtils.getCacheRootPath() + "log/";

    private Thread.UncaughtExceptionHandler mDefaultCrashHandler;

    private static CrashHandler sInstance;

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        dumpException2SDCard(e);
        uploadExceptionToServer();
        if (mDefaultCrashHandler != null) {
            mDefaultCrashHandler.uncaughtException(t, e);
        } else {
            // 不加这句，App会重新启动
            // Process.killProcess(Process.myPid());
        }
    }

    public static CrashHandler getInstance() {
        if (sInstance == null) {
            sInstance = new CrashHandler();
        }
        return sInstance;
    }

    /**
     * 上传日志到服务器
     */
    private void uploadExceptionToServer() {
    }

    public void init() {
        mDefaultCrashHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 导出异常信息到SD卡中
     *
     * @param throwable
     */
    private void dumpException2SDCard(Throwable throwable) {
        if (!FileUtils.isSdCardAvailable() || FileUtils.getSDCardAvailableSize() < 1000) {
            if (BuildConfig.DEBUG) {
                Log.e(TAG, "dumpException2SDCard: SD卡不可写");
            }
            return;
        }
        File destDir = new File(LOG_PATH);
        FileUtils.checkDirs(LOG_PATH);
        long currentTimeMillis = System.currentTimeMillis();
        String time = TimeUtils.getDate(new Date(currentTimeMillis), "yyyy_MM_dd_HH_mm_ss");
        File file = new File(destDir.getAbsolutePath() + File.separator + time + ".trace");
        try {
            PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            printWriter.println(time);
            printWriter.println(getMobileInfo());
            dumpPhoneInfo(printWriter);
            printWriter.println();
            throwable.printStackTrace(printWriter);
            printWriter.close();
        } catch (Exception e) {
            if (BuildConfig.DEBUG) {
                Log.e(TAG, "dumpException2SDCard: 日志记录失败");
            }
        }
    }

    private void dumpPhoneInfo(PrintWriter printWriter)
            throws PackageManager.NameNotFoundException {
        Context context = MyApplication.getContext();
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(),
                PackageManager.GET_ACTIVITIES);
        // 应用版本号
        printWriter.println("App Version: " + packageInfo.versionName);
        printWriter.println("App VersionCode: " + packageInfo.versionCode);
        // Android系统版本号
        printWriter.println("OS Version: " + Build.VERSION.RELEASE);
        printWriter.println("OS API: " + Build.VERSION.SDK_INT);
        // 手机制造商
        printWriter.println("Vendor: " + Build.MANUFACTURER);
        // 手机型号
        printWriter.println("Model: " + Build.MODEL);
        // CPU架构
        printWriter.println("CPU ABI " + Build.CPU_ABI);
    }

    private String getMobileInfo() {
        StringBuilder stringBuilder = new StringBuilder();
        // 通过反射获取系统的硬件信息
        Field[] fields = Build.class.getDeclaredFields();
        try {
            for (Field field : fields) {
                // 暴力反射，获取私有的信息
                field.setAccessible(true);
                String name = field.getName();
                String value = field.get(null).toString();
                stringBuilder.append(name + " : " + value + "\n");
            }
        } catch (IllegalAccessException e) {
            return "";
        }
        return stringBuilder.toString();
    }
}
