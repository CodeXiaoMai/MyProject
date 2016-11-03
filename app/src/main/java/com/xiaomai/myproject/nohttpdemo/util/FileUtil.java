package com.xiaomai.myproject.nohttpdemo.util;

import android.os.Environment;

import java.io.File;


/**
 * Created by XiaoMai on 2016/11/2 18:53.
 */
public class FileUtil {


    /**
     * 获取SD卡根目录
     * @return
     */
    public static File getRootPath() {
        File path = null;
        if (sdCardIsAvailable()){
            path = Environment.getExternalStorageDirectory();
        }else {
            path = Environment.getDataDirectory();
        }
        return path;
    }

    /**
     * SD卡是否可用
     * @return
     */
    public static boolean sdCardIsAvailable() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            File sd = new File(Environment.getExternalStorageDirectory().getPath());
            return sd.canWrite();
        }else {
            return false;
        }
    }

    /**
     * 创建一个文件夹
     * @param path
     * @return
     */
    public static boolean initDirectory(String path){
        boolean result = false;
        File file = new File(path);
        if (!file.exists()){
            result = file.mkdir();
        } else if (!file.isDirectory()){
            file.delete();
            result = file.mkdir();
        } else if (file.exists()){
            result = true;
        }
        return result;
    }
}
