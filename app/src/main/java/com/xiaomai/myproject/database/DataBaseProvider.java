package com.xiaomai.myproject.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;

/**
 * Created by XiaoMai on 2016/9/7.
 * <p/>
 * 一定要在manifests中注册，并添加authority属性
 */
public class DataBaseProvider extends ContentProvider {

    private DBHelper dbHelper;

    private static final UriMatcher sUriMatcher;

    //默认按id降序排列
    private static final String DEFAUTL_SORT_ORDER = "_id DESC";

    private static final int TABLE_TEST = 1;

    private static final int TABLE_TEST_ITEM = 2;

    static {
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(TableColumns.AUTHORITY, "download", TABLE_TEST);
        sUriMatcher.addURI(TableColumns.AUTHORITY, "download/#", TABLE_TEST_ITEM);
    }

    @Override
    public boolean onCreate() {
        dbHelper = new DBHelper(getContext());
        /**
         *  一定要返回true，否则该ContentProvider不能被加载
         */
        return true;
    }

    /**
     * @param uri
     * @param selection
     * @return
     */
    public String parseSelection(Uri uri, String selection) {
        String extra = null;
        switch (sUriMatcher.match(uri)) {
            case TABLE_TEST_ITEM:
                extra = TableColumns.DownloadColumns._ID + " = " + ContentUris.parseId(uri);
                break;
        }
        if (TextUtils.isEmpty(selection)) {
            return extra;
        }
        if (TextUtils.isEmpty(extra)) {
            return selection;
        }
        return "(" + selection + ") AND (" + extra + ")";
    }

    /**
     * 根据Uri得到表名
     *
     * @param uri
     * @return
     */
    public String getTableName(Uri uri) {
        String tableName;
        switch (sUriMatcher.match(uri)) {
            case TABLE_TEST:
            case TABLE_TEST_ITEM:
                tableName = DBHelper.Tables.DOWNLOAD;
                break;
            default:
                tableName = "";
        }
        return tableName;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] columns, String selection, String[] selectionArgs, String orderBy) {
        selection = parseSelection(uri, selection);
        if (TextUtils.isEmpty(orderBy)) {
            orderBy = DEFAUTL_SORT_ORDER;
        }
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String tableName = getTableName(uri);
        return db.query(tableName, columns, selection, selectionArgs, null, null, orderBy);
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String tableName = getTableName(uri);
        db.insert(tableName, null, contentValues);
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        selection = parseSelection(uri, selection);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String tableName = getTableName(uri);
        return db.delete(tableName, selection, selectionArgs);
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {
        selection = parseSelection(uri, selection);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String tableName = getTableName(uri);
        return db.update(tableName, contentValues, selection, selectionArgs);
    }
}
