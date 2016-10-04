package com.xiaomai.myproject.demo;

import android.app.DownloadManager;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.base.BaseActivity;
import com.xiaomai.myproject.utils.MyLog;

/**
 * Created by XiaoMai on 2016/9/19.
 * 需要加入 WRITE_EXTERNAL_STORAGE permission
 */
public class MyDownloadDemoActivity extends BaseActivity {

    /**
     * 开始按钮
     */
    private Button mBtStart;

    /**
     * 进度条
     */
    private ProgressBar mProgressBar;

    /**
     * 显示进度的TextView
     */
    private TextView mTvProgress;

    /**
     * Android系统提供的下载管理器
     */
    private DownloadManager mDownloadManager;

    /**
     * 文件的下载路径
     */
    private Uri mUri;

    /**
     * 开始下载后返回的唯一标识
     */
    private long mDownloadId;

    /**
     * 下载状态改变的内容观察者
     */
    private DownloadChangeObserver mDownloadChangeObserver;

    public static final Uri CONTENT_URI = Uri.parse("content://downloads/my_downloads");

    private static Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("下载管理");
    }

    @Override
    protected void onResume() {
        super.onResume();
        getContentResolver().registerContentObserver(CONTENT_URI, true, mDownloadChangeObserver);
    }

    @Override
    protected void initVariables() {
        mDownloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        String downloadUri = "http://www.ipmsg.org.cn/downloads/feige2016_for_Windows.exe";
        mUri = Uri.parse(downloadUri);
        mDownloadChangeObserver = new DownloadChangeObserver(null);
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                mTvProgress.setText(msg.arg1 + "%");
                mProgressBar.setProgress(msg.arg1);
            }
        };
    }

    @Override
    protected void initViews() {
        mBtStart = (Button) findViewById(R.id.activity_download_bt_start);
        mBtStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startDownLoad();
            }
        });
        mProgressBar = (ProgressBar) findViewById(R.id.activity_download_pb);
        mTvProgress = (TextView) findViewById(R.id.activity_download_tv_progress);
    }

    /**
     * 开始下载
     */
    private void startDownLoad() {
        DownloadManager.Request request = new DownloadManager.Request(mUri);
        String title = "标题.exe";
        request.setDestinationInExternalFilesDir(this, Environment.DIRECTORY_DOWNLOADS, title);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setTitle(title);
        request.setDescription("下载完成后可点击打开");
        //enqueue入队
        mDownloadId = mDownloadManager.enqueue(request);
//        registerReceiver(receiver);
    }

    @Override
    protected int getContentLayout() {
        return R.layout.act_download;
    }

/*    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
         //这里可以取得下载的id，这样就可以知道哪个文件下载完成了。适用与多个下载任务的监听
                Log.v("tag", ""+intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, 0));
                queryDownloadStatus();
        }
    };*/

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unregisterReceiver(receiver);
        getContentResolver().unregisterContentObserver(mDownloadChangeObserver);
    }

    class DownloadChangeObserver extends ContentObserver {

        public DownloadChangeObserver(Handler handler) {
            super(handler);
        }

        @Override
        public void onChange(boolean selfChange) {
            queryDownloadStatus();
        }

        private void queryDownloadStatus() {
            DownloadManager.Query query = new DownloadManager.Query();
            /**
             * 如果什么都不传，返回所有的下载
             */
            //根据下载状态查询下载列表
//            query.setFilterByStatus(DownloadManager.STATUS_RUNNING);
            //根据下载的ID查询下载列表
//            query.setFilterById(mDownloadId);
            Cursor cursor = mDownloadManager.query(query);
            if (cursor != null && cursor.moveToFirst()) {
                /**
                 * 当前下载的状态
                 */
                int status = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS));
                /**
                 * 标题
                 */
                String title = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_TITLE));
                /**
                 * 文件总大小
                 */
                long fileSize = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));
                /**
                 * 当前已经下载的大小
                 */
                long soForSize = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
                /**
                 *
                 */
                int reason = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_REASON));

                Message message = new Message();
                switch (status) {
                    /**
                     * 暂停
                     */
                    case DownloadManager.STATUS_PAUSED:
                        MyLog.e("down", "STATUS_PAUSED");
                        break;
                    /**
                     * 等待下载中
                     */
                    case DownloadManager.STATUS_PENDING:
                        MyLog.e("down", "STATUS_PENDING");
                        break;
                    /**
                     * 正在下载
                     */
                    case DownloadManager.STATUS_RUNNING:
                        MyLog.e("down", "STATUS_RUNNING");
                        message.arg1 = (int) (soForSize * 100 / fileSize);
                        mHandler.sendMessage(message);
                        break;
                    /**
                     * 下载成功
                     */
                    case DownloadManager.STATUS_SUCCESSFUL:
                        MyLog.e("down", "STATUS_SUCCESSFUL");
                        message.arg1 = (int) (soForSize * 100 / fileSize);
                        mHandler.sendMessage(message);
                        break;
                    /**
                     * 下载失败
                     */
                    case DownloadManager.STATUS_FAILED:
                        MyLog.e("down", "STATUS_FAILED");
                        break;
                }
                cursor.close();
            }
        }
    }

}
