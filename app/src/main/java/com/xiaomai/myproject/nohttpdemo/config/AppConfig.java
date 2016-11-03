package com.xiaomai.myproject.nohttpdemo.config;

import android.content.Context;
import android.content.SharedPreferences;

import com.xiaomai.myproject.nohttpdemo.Application;
import com.xiaomai.myproject.nohttpdemo.util.FileUtil;

import java.io.File;

/**
 * Created by XiaoMai on 2016/11/2 18:48.
 */
public class AppConfig {

    private static AppConfig appConfig;

    private SharedPreferences sharedPreferences;

    public static final boolean DEBUG = false;//是否是测试环境

    public String APP_PATH_ROOT;//App跟目录

    private AppConfig() {
        sharedPreferences = Application.getInstance().getSharedPreferences("nohttp_sample", Context.MODE_PRIVATE);

        APP_PATH_ROOT = FileUtil.getRootPath().getAbsolutePath() + File.separator + "NoHttpSample";

        FileUtil.initDirectory(APP_PATH_ROOT);
    }

    public static AppConfig getInstance() {
        if (appConfig == null){
            appConfig = new AppConfig();
        }
        return appConfig;
    }
}
