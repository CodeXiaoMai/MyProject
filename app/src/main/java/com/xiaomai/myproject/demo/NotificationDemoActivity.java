package com.xiaomai.myproject.demo;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.activity.MainActivity;

import java.util.Date;

/**
 * Created by XiaoMai on 2016/11/9 15:20.
 */
public class NotificationDemoActivity extends Activity {

    private int REQUESTCODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Notification notification = new Notification();
        RemoteViews view = new RemoteViews(getPackageName(), R.layout.notification_view);
        view.setTextViewText(R.id.title, "这是标题");
        view.setTextViewText(R.id.content, "这是内容");
        view.setImageViewResource(R.id.image, R.mipmap.ic_launcher);
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notification.contentView = view;
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                REQUESTCODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        notification.contentIntent = pendingIntent;
        notification.defaults |= Notification.DEFAULT_SOUND;
        notification.icon = R.mipmap.ic_launcher;
        notification.when = new Date().getTime();
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(REQUESTCODE++, notification);
        notificationManager.notify(REQUESTCODE++, notification);
        notificationManager.notify(REQUESTCODE++, notification);
        notificationManager.notify(REQUESTCODE++, notification);
    }
}
