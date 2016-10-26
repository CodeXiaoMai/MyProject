package com.xiaomai.myproject.demo;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.base.BaseActivity;
import com.xiaomai.myproject.database.TableColumns;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XiaoMai on 2016/9/7.
 */
public class DataBaseDemoActivity extends BaseActivity {

    private List<String> mList;

    private ContentResolver contentResolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contentResolver = getContentResolver();
        ContentValues values = new ContentValues();
        values.put(TableColumns.DownloadColumns.FILE_NAME, "1");
        //插入
        contentResolver.insert(TableColumns.DownloadColumns.CONTENT_URI, values);
        //查询 select * from tableName
        Cursor cursor = contentResolver.query(TableColumns.DownloadColumns.CONTENT_URI, null, null, null, null);
        mList = new ArrayList<>();
        if (cursor != null) {
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex(TableColumns.DownloadColumns._ID));
                String content = cursor.getString(cursor.getColumnIndex(TableColumns.DownloadColumns.FILE_NAME));
                String str = id + " " + content;
                mList.add(str);
            }
            cursor.close();
        }

        //修改
        ContentValues contentValues = new ContentValues();
        contentValues.put(TableColumns.DownloadColumns.FILE_NAME, "数据更新了");
        String where = "_id = ?";
        contentResolver.update(TableColumns.DownloadColumns.CONTENT_URI, contentValues, where, new String[]{"1"});

        //删除
        where = "content = ?";
        String[] selectionArgs = {"1"};
        contentResolver.delete(TableColumns.DownloadColumns.CONTENT_URI, where, selectionArgs);
    }

    @Override
    protected void initViews() {
    }

    @Override
    protected int getContentLayout() {
        return R.layout.act_main;
    }
}
