
package com.xiaomai.myproject.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.demo.ServiceDemoActivity;
import com.xiaomai.myproject.utils.MyLog;

/**
 * Created by XiaoMai on 2016/11/14 10:41.
 */
public class ServiceDemo extends Service {

    public static final String TAG = "MyService";

    private MyBinder mBinder;

    @Override
    public void onCreate() {
        super.onCreate();
        Intent notificationIntent = new Intent(this, ServiceDemoActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        Notification.Builder builder = new Notification.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("标题");
        builder.setContentText("内容");
        builder.setWhen(System.currentTimeMillis());
        builder.setTicker("new message");   //显示在通知栏的提示信息
        builder.setContentIntent(pendingIntent);
        Notification notification = builder.getNotification();
        startForeground(1, notification);
        MyLog.e(TAG, "onCreate() executed");
    }

    /**
     * onStart方法是在Android2.0之前的平台使用的.在2.0及其之后，则需重写onStartCommand方法，
     * 同时，旧的onStart方法则不会再被直接调用（外部调用onStartCommand，而onStartCommand里会再调用 onStart。
     * 在2.0之后，推荐覆盖onStartCommand方法，而为了向前兼容，在onStartCommand依然会调用onStart方法。
     * 
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        MyLog.e(TAG, "onStartCommand() executed");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MyLog.e(TAG, "onDestroy() executed");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        if (mBinder == null) {
            mBinder = new MyBinder();
        }
        return mBinder;
    }

    public class MyBinder extends Binder {
        public void startDownload() {
            MyLog.e(TAG, "startDownload() executed");
        }
    }
}
