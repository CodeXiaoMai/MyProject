
package com.xiaomai.myproject.nohttpdemo;

import com.yolanda.nohttp.Logger;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.cache.DBCacheStore;
import com.yolanda.nohttp.cookie.DBCookieStore;

/**
 * Created by XiaoMai on 2016/11/2 18:45.
 */
public class Application extends android.app.Application {

    private static Application _instance;

    @Override
    public void onCreate() {
        super.onCreate();
        _instance = this;

        Logger.setDebug(true);// 开启NoHttp的调试模式
        Logger.setTag("NoHttpSample");

        NoHttp.initialize(this,
                new NoHttp.Config().setConnectTimeout(30 * 1000).setReadTimeout(30 * 1000)
                        .setCacheStore(new DBCacheStore(this).setEnable(true))
                        .setCookieStore(new DBCookieStore(this).setEnable(true)));
    }

    public static Application getInstance() {
        return _instance;
    }
}
