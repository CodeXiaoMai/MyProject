package com.xiaomai.myproject.demo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.base.BaseActivity;
import com.xiaomai.myproject.utils.MyLog;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.rest.OnResponseListener;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.RequestQueue;
import com.yolanda.nohttp.rest.Response;

/**
 * Created by XiaoMai on 2016/10/24 13:03.
 */
public class NoHttpDemoActivity extends BaseActivity implements OnResponseListener {

    private static final int WHAT_STRING_REQUEST1 = 2;
    private Button mButton1;

    private Button mButton2;

    private Button mButton3;

    private Button mButton4;

    private TextView mTextView;

    //请求队列
    private RequestQueue mRequestQueue;

    public static final int WHAT_STRING_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        /*mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DownloadRequest downloadRequest = NoHttp.createDownloadRequest(
                        "http://music.163.com/api/android/download/latest2",
                        Environment.getExternalStorageDirectory().getAbsolutePath(),
                        "test",
                        true,
                        false
                );
                DownloadQueue downloadQueue = NoHttp.newDownloadQueue();
                downloadQueue.add(0, downloadRequest, new DownloadListener() {
                    @Override
                    public void onDownloadError(int what, Exception exception) {

                    }

                    @Override
                    public void onStart(int what, boolean isResume, long rangeSize, Headers responseHeaders, long allCount) {
                        MyLog.e("what: " + what + ",isResume:" + isResume + ",rangeSize:" + rangeSize + ",allCount:" + allCount);
                    }

                    @Override
                    public void onProgress(int what, int progress, long fileCount) {
                        MyLog.e("what: " + what + "progress:" + progress + ",fileCount:" + fileCount);
                    }

                    @Override
                    public void onFinish(int what, String filePath) {
                        MyLog.e("what: " + what + ",filePath" + filePath);
                    }

                    @Override
                    public void onCancel(int what) {

                    }
                });
            }
        });*/

    }

    @Override
    protected void initVariables() {
        super.initVariables();
        mRequestQueue = NoHttp.newRequestQueue();
    }

    @Override
    protected void initViews() {
        mButton1 = (Button) findViewById(R.id.activity_nohttp_bt_jiami);
        mButton2 = (Button) findViewById(R.id.activity_nohttp_bt_image);
        mButton3 = (Button) findViewById(R.id.activity_nohttp_bt_upload);
        mButton4 = (Button) findViewById(R.id.activity_nohttp_bt_download);
        mTextView = (TextView) findViewById(R.id.activity_nohttp_textview);
        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Request<String> stringRequest = NoHttp.createStringRequest("https://www.baidu.com");
                Request<String> stringRequest1 = NoHttp.createStringRequest("http://www.csdn.net");
                mRequestQueue.add(WHAT_STRING_REQUEST, stringRequest, NoHttpDemoActivity.this);
                mRequestQueue.add(WHAT_STRING_REQUEST1, stringRequest1, NoHttpDemoActivity.this);
                mRequestQueue.start();
                mRequestQueue.cancelAll();
                //请求队列停止轮询，但不会被取消
//                mRequestQueue.stop();
                stringRequest.cancel();
            }
        });

    }

    @Override
    protected int getContentLayout() {
        return R.layout.act_nohttp;
    }

    @Override
    public void onStart(int what) {
        MyLog.e("开始请求：" + what);
}

    @Override
    public void onSucceed(int what, Response response) {
        MyLog.e("请求结束：" + what);
        mTextView.setText(response.get().toString());
    }

    @Override
    public void onFailed(int what, Response response) {
        MyLog.e("请求失败：" + what);
    }

    @Override
    public void onFinish(int what) {
        MyLog.e("请求完毕：" + what);
    }
}
