package com.xiaomai.myproject.demo;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.adapter.FileDownloadAdapter;
import com.xiaomai.myproject.base.BaseActivity;
import com.xiaomai.myproject.database.TableColumns;
import com.xiaomai.myproject.entity.File;
import com.xiaomai.myproject.utils.MyLog;
import com.yolanda.nohttp.Headers;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.download.DownloadListener;
import com.yolanda.nohttp.download.DownloadQueue;
import com.yolanda.nohttp.download.DownloadRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by XiaoMai on 2016/10/25 19:01.
 */
public class NoHttpDownloadDemoActivity extends BaseActivity implements DownloadListener {

    private Button mButtonAdd;

    private Button mButtonStart;

    private Button mButtonStop;

    private ListView mListView;

    private FileDownloadAdapter mAdapter;

    private List<File> mList;

    private ContentResolver mContentResolver;

    private DownloadQueue mDownloadQueue;

    private List<DownloadRequest> mDownloadRequest;

    private String downloadUrl;

    public static final String[] URL_DOWNLOADS = {
            "http://gdown.baidu.com/data/wisegame/4f45d1baacb6ee7f/baidushoujizhushouyuan91zhu_16789458.apk",
            "http://gdown.baidu.com/data/wisegame/2eeee3831c9dbc42/QQ_374.apk",
            "http://gdown.baidu.com/data/wisegame/8d5889f722f640c8/weixin_800.apk",
            "http://gdown.baidu.com/data/wisegame/4c77c9e0020562ca/baidushipin_1072201074.apk"
    };

    @Override
    protected void initVariables() {
        super.initVariables();
        mDownloadQueue = NoHttp.newDownloadQueue();
        downloadUrl = "http://gdown.baidu.com/data/wisegame/4f45d1baacb6ee7f/baidushoujizhushouyuan91zhu_16789458.apk";
        mContentResolver = getContentResolver();
        Cursor query = mContentResolver.query(TableColumns.DownloadColumns.CONTENT_URI, null, null, null, null);
        if (query != null) {
            mList = new ArrayList<>();
            while (query.moveToNext()) {
                int id = query.getInt(query.getColumnIndex(TableColumns.DownloadColumns._ID));
                String fileName = query.getString(query.getColumnIndex(TableColumns.DownloadColumns.FILE_NAME));
                String url = query.getString(query.getColumnIndex(TableColumns.DownloadColumns.URL));
                int progress = query.getInt(query.getColumnIndex(TableColumns.DownloadColumns.PROGRESS));
                File file = new File(id, fileName, url, progress);
                mList.add(file);
            }
            query.close();

        }

    }

    @Override
    protected void initViews() {
        mButtonAdd = (Button) findViewById(R.id.add);
        mButtonStart = (Button) findViewById(R.id.start);
        mButtonStop = (Button) findViewById(R.id.stop);

        mButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = new File(10000, "test.apk" + new Random().nextDouble(), downloadUrl, 0);
                DownloadRequest downloadRequest = NoHttp.createDownloadRequest(
                        downloadUrl,
                        Environment.getExternalStorageDirectory().getAbsolutePath(),
                        "test.apk",
                        true,
                        false
                );
                ContentValues contentValues = new ContentValues();
                contentValues.put(TableColumns.DownloadColumns.FILE_NAME, file.getTitle());
                contentValues.put(TableColumns.DownloadColumns.URL, file.getUrl());
                contentValues.put(TableColumns.DownloadColumns.PROGRESS, 0);
                mContentResolver.insert(TableColumns.DownloadColumns.CONTENT_URI, contentValues);
                mDownloadRequest.add(downloadRequest);
                mList.add(file);
                mAdapter.setList(mList);
                mAdapter.notifyDataSetChanged();
            }
        });

        mButtonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDownloadRequest = new ArrayList<>();
                for (File file : mList) {
                    DownloadRequest downloadRequest = NoHttp.createDownloadRequest(
                            file.getUrl(), Environment.getExternalStorageDirectory().getAbsolutePath(),
                            file.getTitle(),
                            true,
                            false
                    );
                    mDownloadRequest.add(downloadRequest);
                }
                for (int i = 0; i < mDownloadRequest.size(); i++) {
                    mDownloadQueue.add(i, mDownloadRequest.get(i), NoHttpDownloadDemoActivity.this);
                }
                mDownloadQueue.start();
            }
        });

        mButtonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < mDownloadRequest.size(); i++) {
                    mDownloadRequest.get(i).cancel();
                }
            }
        });

        mListView = (ListView) findViewById(R.id.listview);
        mAdapter = new FileDownloadAdapter(mList, mContext);
        mListView.setAdapter(mAdapter);
    }

    @Override
    protected int getContentLayout() {
        return R.layout.act_nohttp_download;
    }

    @Override
    public void onDownloadError(int what, Exception exception) {

    }

    @Override
    public void onStart(int what, boolean isResume, long rangeSize, Headers responseHeaders, long allCount) {
        MyLog.e(what + "开始下载");
    }

    @Override
    public void onProgress(int what, int progress, long fileCount) {
        mList.get(what).setProgress(progress);
        ContentValues contentValues = new ContentValues();
        contentValues.put(TableColumns.DownloadColumns.PROGRESS, progress);
        mContentResolver.update(TableColumns.DownloadColumns.CONTENT_URI, contentValues, "file_name = ?", new String[]{mList.get(what).getTitle()});
        mAdapter.setList(mList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFinish(int what, String filePath) {

    }

    @Override
    public void onCancel(int what) {

    }
}
