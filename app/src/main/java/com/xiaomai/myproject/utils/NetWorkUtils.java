package com.xiaomai.myproject.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

/**
 * Created by HSEDU on 2016/9/6.
 */
public class NetWorkUtils {

    public static final int NETWORK_TYPE_UNKONW = 0;
    public static final int NETWORK_TYPE_1G = 1;
    public static final int NETWORK_TYPE_2G = 2;
    public static final int NETWORK_TYPE_3G = 3;
    public static final int NETWORK_TYPE_4G = 4;
    public static final int NETWORK_TYPE_WIFI = 5;


    /**
     * 不要忘记加网络权限<br>
     * 判断当前是否连接到网络
     *
     * @param context
     * @return boolean
     */
    public static boolean hasNetworkConnected(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info != null && info.isAvailable()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 不要忘记加网络权限<br>
     * 判断当前网络是否为Wifi
     *
     * @param context
     * @return
     */
    public static boolean hasWifiConnected(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info != null && info.getState() == NetworkInfo.State.CONNECTED) {
            if (info.getType() == ConnectivityManager.TYPE_WIFI) {
                return true;
            }
        }
        return false;
    }

    /**
     * 不要忘记加网络权限<br>
     * 判断当前网络是否为移动网络
     *
     * @param context
     * @return
     */
    public static boolean hasMobileConnected(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info != null && info.getState() == NetworkInfo.State.CONNECTED) {
            if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
                return true;
            }
        }
        return false;
    }

    /**
     * 不要忘记加网络权限<br>
     * 获取当前网络类型
     *
     * @param context
     * @return int
     */
    public static int getNetWorkType(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info != null) {
            if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
                int subType = info.getSubtype();
                switch (subType) {
                    case TelephonyManager.NETWORK_TYPE_CDMA://（电信2g）
                    case TelephonyManager.NETWORK_TYPE_GPRS://（联通2g）
                    case TelephonyManager.NETWORK_TYPE_EDGE://（移动2g）
                        return NETWORK_TYPE_2G;
                    case TelephonyManager.NETWORK_TYPE_UMTS://（联通3g）
                    case TelephonyManager.NETWORK_TYPE_HSDPA://（联通3g）
                    case TelephonyManager.NETWORK_TYPE_EVDO_A://（电信3g）
                    case TelephonyManager.NETWORK_TYPE_EVDO_0://（电信3g）
                    case TelephonyManager.NETWORK_TYPE_EVDO_B://（电信3g）
                        return NETWORK_TYPE_3G;
                    case TelephonyManager.NETWORK_TYPE_LTE:// (4g)
                        return NETWORK_TYPE_4G;
                }
            }
            if (info.getType() == ConnectivityManager.TYPE_WIFI) {
                return NETWORK_TYPE_WIFI;
            }
        }
        return NETWORK_TYPE_UNKONW;
    }
}
