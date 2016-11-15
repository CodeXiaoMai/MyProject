package com.xiaomai.myproject;

import android.app.Application;
import android.content.Context;

import com.liulishuo.filedownloader.FileDownloader;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
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
