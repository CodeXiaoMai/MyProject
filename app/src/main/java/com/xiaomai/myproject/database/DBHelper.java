package com.xiaomai.myproject.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.xiaomai.myproject.database.DBHelper.Tables.PUSH_CACHE;

/**
 * Created by XiaoMai on 2016/9/7.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "xiaomai.db";

    public static final int DATABASE_VERSION = 1;

    public static class Tables {
        public static final String JSON_CACHE = "json_cache";

        public static final String DRAFT_CACHE = "draft_cache";

        public static final String PUSH_CACHE = "push_cache";
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
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + PUSH_CACHE);
        sqLiteDatabase.execSQL(
                "CREATE TABLE " + PUSH_CACHE + " ("
                        + TableColumns.PushColumns._ID + " INTEGER PRIMARY KEY, "
                        + TableColumns.PushColumns.CONTENT + " VARCHAR NOT NULL"
                        + ");"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        //如果旧版本号比新版本号小，就重新创建表
        if (oldVersion < newVersion) {
            createTables(sqLiteDatabase);
        }
    }
}
