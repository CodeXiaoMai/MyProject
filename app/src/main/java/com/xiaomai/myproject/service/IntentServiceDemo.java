package com.xiaomai.myproject.service;

import android.app.IntentService;
import android.content.Intent;

import com.orhanobut.logger.Logger;

/**
 * Created by XiaoMai on 2017/3/15 13:14.
 *
 * IntentService 默认实现了 OnBind()，返回值为 null。
 * IntentService 必须实现构造方法和 onHandleIntent(Intent intent)。
 */

public class IntentServiceDemo extends IntentService {

    /**
     * IntentService 的构造函数一定是参数为空的构造函数，然后再在其中调用 super(“name”) 这种形式的构造函数。
     * 因为 Service 的实例化是系统来完成的，而且系统是用参数为空的构造函数来实例化Service的。
     * @param name
     */
    public IntentServiceDemo(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            Thread.sleep(3000);
            Logger.e("service");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
