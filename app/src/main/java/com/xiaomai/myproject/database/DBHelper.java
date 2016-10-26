package com.xiaomai.myproject.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by XiaoMai on 2016/9/7.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "xiaomai.db";

    public static final int DATABASE_VERSION = 1;

    public static class Tables {

        public static final String DOWNLOAD = "download";
    }

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        createTables(sqLiteDatabase);
    }

    /**
     * 创建数据表
     *
     * @param sqLiteDatabase
     */
    private void createTables(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "CREATE TABLE " + Tables.DOWNLOAD + " ("
                        + TableColumns.DownloadColumns._ID + " INTEGER PRIMARY KEY, "
                        + TableColumns.DownloadColumns.FILE_NAME + " VARCHAR NOT NULL,"
                        + TableColumns.DownloadColumns.PROGRESS + " INTEGER,"
                        + TableColumns.DownloadColumns.URL + " VARCHAR NOT NULL"
                        + ");"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        //如果旧版本号比新版本号小,升级
        if (oldVersion < newVersion) {
            createTables(sqLiteDatabase);
        }
    }
}
