
package com.xiaomai.myproject.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.base.BaseActivity;
import com.xiaomai.myproject.utils.Const;

public class WebViewActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    private WebView web_view;

    private ProgressBar pb_web_view;

    private SwipeRefreshLayout srf_web_view;

    private String mUrl;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_web_view;
    }

    @Override
    protected int getCodeResId() {
        return R.string.code_web_view;
    }

    @Override
    protected void initVariables() {
        super.initVariables();
        Intent intent = getIntent();
        if (null != intent) {
            mUrl = intent.getStringExtra(Const.URL);
        }
    }

    @Override
    protected void initViews() {
        super.initViews();
        setTitle("正在加载中...");
        web_view = (WebView) findViewById(R.id.web_view);
        pb_web_view = (ProgressBar) findViewById(R.id.pb_web_view);
        srf_web_view = (SwipeRefreshLayout) findViewById(R.id.srf_web_view);
        srf_web_view.setOnRefreshListener(this);
        initSettings();
        initWebViewClient();
        srf_web_view.setRefreshing(true);
        onRefresh(false);
    }

    private void initWebViewClient() {
        WebViewClient webViewClient = new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                setTitle("正在加载中...");
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                setTitle("正在加载中...");
                mUrl = url;
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                String title = view.getTitle();
                if (!TextUtils.isEmpty(title)) {
                    setTitle(title);
                }
                srf_web_view.setRefreshing(false);
                super.onPageFinished(view, url);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description,
                    String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                setTitle("加载失败");
                srf_web_view.setRefreshing(false);
            }
        };
        WebChromeClient webChromeClient = new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress >= 100) {
                    pb_web_view.setVisibility(View.INVISIBLE);
                } else {
                    pb_web_view.setVisibility(View.VISIBLE);
                    pb_web_view.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }
        };
        web_view.setWebViewClient(webViewClient);
        web_view.setWebChromeClient(webChromeClient);
    }

    /**
     * 配置WebView
     */
    @SuppressLint("SetJavaScriptEnabled")
    private void initSettings() {
        WebSettings settings = web_view.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDefaultTextEncodingName("utf-8");
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setDomStorageEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
    }

    @Override
    public void onBackPressed() {
        if (web_view.canGoBack()) {
            web_view.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onRefresh() {
        web_view.clearCache(true);
        web_view.loadUrl(mUrl);
    }

    private void onRefresh(boolean forceRefresh) {
        if (forceRefresh)
            onRefresh();
        else
            web_view.loadUrl(mUrl);

    }
}
