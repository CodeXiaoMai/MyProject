package com.xiaomai.myproject.demo;

import android.annotation.SuppressLint;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.base.BaseActivity;
import com.xiaomai.myproject.jsinterface.MyJavaScriptInterface;

/**
 * Created by XiaoMai on 2016/10/21 17:54.
 */
public class WebViewJsDemoActivity extends BaseActivity {

    private WebView mWebView;

    private Button mButton;

    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface"})
    @Override
    protected void initViews() {
        mWebView = (WebView) findViewById(R.id.activity_webview);
        WebSettings settings = mWebView.getSettings();
        //  支付JavaScript
        settings.setJavaScriptEnabled(true);
        //  设置编码
        settings.setDefaultTextEncodingName("utf-8");
        //  设置本地调用对象及其接口
        mWebView.addJavascriptInterface(new MyJavaScriptInterface(mContext), "Android");
        //  载入js
        mWebView.loadUrl("file:///android_asset/js.html");
        mButton = (Button) findViewById(R.id.bt);
        mWebView.setWebChromeClient(new WebChromeClient());
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  调用js中的方法
                mWebView.loadUrl("javascript:fromJs()");
            }
        });
    }

    @Override
    protected int getContentLayout() {
        return R.layout.act_webview;
    }
}
