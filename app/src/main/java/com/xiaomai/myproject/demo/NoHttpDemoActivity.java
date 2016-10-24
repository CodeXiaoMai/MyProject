package com.xiaomai.myproject.demo;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.base.BaseActivity;
import com.xiaomai.myproject.utils.MyLog;
import com.yolanda.nohttp.Headers;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.download.DownloadListener;
import com.yolanda.nohttp.download.DownloadQueue;
import com.yolanda.nohttp.download.DownloadRequest;

/**
 * Created by XiaoMai on 2016/10/24 13:03.
 */
public class NoHttpDemoActivity extends BaseActivity {

    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mButton = (Button) findViewById(R.id.activity_nohttp_bt_start);
        mButton.setOnClickListener(new View.OnClickListener() {
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
        });

    }

    @Override
    protected void initViews() {
    }

    @Override
    protected int getContentLayout() {
        return R.layout.act_nohttp;
    }
}
