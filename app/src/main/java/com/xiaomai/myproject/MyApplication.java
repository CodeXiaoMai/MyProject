
package com.xiaomai.myproject;

import android.app.Application;
import android.content.Context;

import com.liulishuo.filedownloader.FileDownloader;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;
import com.xiaomai.myproject.utils.CrashHandler;
import com.yolanda.nohttp.NoHttp;


/**
 * Created by XiaoMai on 2016/9/7.
 */
public class MyApplication extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        /**
         * 使用NoHttp必须先初始化
         */
        NoHttp.initialize(this);
        initFileDownloader();
        initImageLoader();
        initCrashHandler();
        initZXingLibrary();
        initLogger();
    }

    /**
     * 初始化Logger
     */
    private void initLogger() {
        Logger.init("XiaoMai").setLogLevel(LogLevel.FULL).setMethodCount(3);
//        Logger.e("hello");
        // 自定义Tag
//        Logger.t("myTag").e("lsdkfj");
    }

    /**
     * 初始化二维码扫描库
     */
    private void initZXingLibrary() {
        ZXingLibrary.initDisplayOpinion(this);
    }

    private void initCrashHandler() {
        CrashHandler.getInstance().init();
    }

    private void initImageLoader() {
        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(mContext));
    }

    /**
     * 初始化FileDownloader
     */
    private void initFileDownloader() {
        FileDownloader.init(getApplicationContext());
    }

    public static Context getContext() {
        return mContext;
    }

}
