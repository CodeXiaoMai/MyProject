package com.xiaomai.myproject.demo;

import android.os.Environment;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.xiaomai.myproject.R;
import com.xiaomai.myproject.base.BaseActivity;
import com.xiaomai.myproject.utils.MyLog;

/**
 * Created by XiaoMai on 2016/10/26 18:31.
 *
 * compile 'com.liulishuo.filedownloader:library:1.2.2'
 * 依赖okhttp、okio
 */
public class FileDownloaderDemoActivity extends BaseActivity {
    @Override
    protected void initViews() {

        FileDownloader.getImpl().create("http://music.163.com/api/android/download/latest2")
                .setPath(Environment.getExternalStorageDirectory().getAbsolutePath(),true)
                .setListener(new FileDownloadListener() {

                    @Override
                    protected void started(BaseDownloadTask task) {
                        super.started(task);
                        MyLog.e("开始下载");
                    }

                    @Override
                    protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {

                        MyLog.e("pending");
                    }

                    @Override
                    protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {

                        MyLog.e("progress:" + soFarBytes * 100 / totalBytes);
                    }

                    @Override
                    protected void completed(BaseDownloadTask task) {
                        MyLog.e("completed");
                    }

                    @Override
                    protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                        MyLog.e("paused");
                    }

                    @Override
                    protected void error(BaseDownloadTask task, Throwable e) {
                        MyLog.e("error");
                    }

                    @Override
                    protected void warn(BaseDownloadTask task) {
                        MyLog.e("warn");
                    }
                }).start();
    }

    @Override
    protected int getContentLayout() {
        return R.layout.act_gif_imageview;
    }
}
