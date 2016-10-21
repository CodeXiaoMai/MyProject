package com.xiaomai.myproject.jsinterface;

import android.content.Context;
import android.webkit.JavascriptInterface;

import com.xiaomai.myproject.view.MyToast;

/**
 * Created by XiaoMai on 2016/10/21 17:53.
 */
public class MyJavaScriptInterface {

    private Context mContext;

    public MyJavaScriptInterface(Context mContext) {
        this.mContext = mContext;
    }

    @JavascriptInterface
    public void showToast(String toast){
        MyToast.show(mContext, toast);
    }
}
