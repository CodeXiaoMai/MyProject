package com.xiaomai.myproject.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.provider.ContactsContract;

import java.io.File;
import java.util.List;

/**
 * Created by XiaoMai on 2016/11/30 17:20.
 */
public class AppUtils {

    /**
     * 判断某软件是否已经安装
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean hasInstall(Context context, String packageName) {
        List<PackageInfo> packageInfoList = context.getPackageManager().getInstalledPackages(0);
        if (packageInfoList != null) {
            for (int i = 0; i < packageInfoList.size(); i++) {
                if (packageInfoList.get(i).packageName.equals(packageName)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 打开QQ
     *
     * @param context
     */
    public static void openQQ(Context context) {
        if (hasInstall(context, "com.tencent.mobileqq")) {
            String qqNumber = "10000";
            String url = "mqqwpa://im/chat?chat_type=wpa&uin=" + qqNumber;
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        }
    }

    /**
     * 打开某个App
     *
     * @param context
     * @param packageName
     * @param url
     */
    public static void openApp(Context context, String packageName, String url) {
        if (hasInstall(context, packageName)) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        }
    }

    /**
     * 卸载某个应用
     *
     * @param context
     * @param packageName
     */
    public static void unInstallApp(Context context, String packageName) {
        Uri uri = Uri.fromParts("package", packageName, null);
        // 或者Uri uri = Uri.parse("package:" + packageName);
        Intent intent = new Intent(Intent.ACTION_DELETE, uri);
        context.startActivity(intent);
    }

    /**
     * 打开微信
     *
     * @param context
     * @param id
     */
    public static void openWeiXin(Context context, String id) {
        String WEIXIN_CHATTING_MIMETYPE = "vnd.android.cursor.item/vnd.com.tencent.mm.chatting.profile";// 微信聊天
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(
                Uri.withAppendedPath(ContactsContract.Data.CONTENT_URI, String.valueOf(id)),
                WEIXIN_CHATTING_MIMETYPE);
        context.startActivity(intent);
    }

    /**
     * 在应用商店中根据AppId显示应用的相关信息
     *
     * @param context
     * @param appId
     */
    public static void searchDetails(Context context, String appId) {
        Uri uri = Uri.parse("market://details?id=" + appId);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        context.startActivity(intent);
    }

    /**
     * 安装App
     *
     * @param context
     * @param apkPath
     */
    public static void installApp(Context context, String apkPath) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(apkPath)),
                "application/vnd.android.package-archive");

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        android.os.Process.killProcess(android.os.Process.myPid());
        // 如果不加上这句的话在apk安装完成之后点击单开会崩溃
        context.startActivity(intent);
    }

    /**
     * 打开地图 geo:latitude,longitude 纬度、经度 geo:latitude,longitude?z=zoom，
     * 纬度、经度，z表示zoom级别，值为数字1到23 geo:0,0?q=my+street+address
     * geo:0,0?q=business+near+city
     *
     * @param context
     * @param uri
     */
    public static void openMap(Context context, String uri) {
        Uri mUri = Uri.parse(uri);
        Intent mIntent = new Intent(Intent.ACTION_VIEW, mUri);
        context.startActivity(mIntent);
    }

    /**
     * 根据关键词到应用商店搜索应用
     *
     * @param context
     * @param packageName
     */
    public static void searchApp(Context context, String packageName) {
        Uri uri = Uri.parse("market://search?q=" + packageName);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        context.startActivity(intent);
    }


}
