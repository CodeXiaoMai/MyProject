
package com.xiaomai.myproject.demo;

import android.app.Activity;
import android.app.AlarmManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;

import com.xiaomai.myproject.service.LongRunningService;

/**
 * Created by XiaoMai on 2016/11/29 10:11.
 */
public class AlarmDemoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        /**
         * 使用 SystemClock.elapsedRealtime()方法可以获取到系统开机至今所经历时间的毫秒数， 使用
         * System.currentTimeMillis()方法可以获取到 1970 年 1 月 1 日 0 点至今所经历时间的毫秒数。
         */
        long triggerAtTime = SystemClock.elapsedRealtime() + 10 * 1000;
        /**
         * 第一个参数是一个整型参数，用于指定 AlarmManager 的 工作类型，有四种值可选，分别是
         * ELAPSED_REALTIME、ELAPSED_REALTIME_WAKEUP、 RTC 和 RTC_WAKEUP。其中
         * ELAPSED_REALTIME 表示让定时任务的触发时间从系统开 机开始算起，但不会唤醒
         * CPU。ELAPSED_REALTIME_WAKEUP 同样表示让定时任务的触 发时间从系统开机开始算起，但会唤醒 CPU。RTC
         * 表示让定时任务的触发时间从 1970 年 1 月 1 日 0 点开始算起，但不会唤醒 CPU。RTC_WAKEUP
         * 同样表示让定时任务的触发时间从 1970 年 1 月 1 日 0 点开始算起，但会唤醒 CPU。
         * 第二个参数，就是定时任务触发的时间，以毫秒为单位。如果第一个参数使用的是 ELAPSED_REALTIME 或
         * ELAPSED_REALTIME_WAKEUP，则这里传入开机至今的时间 再加上延迟执行的时间。如果第一个参数使用的是 RTC
         * 或RTC_WAKEUP， 则这里传入 1970 年 1 月 1 日 0 点至今的时间再加上延迟执行的时间。 第三个参数是一个
         * PendingIntent，对于它你应该已经不会陌生了吧。这里我们一般会调 用 getBroadcast()方法来获取一个能够执行广播的
         * PendingIntent。这样当定时任务被触发的时 候，广播接收器的 onReceive()方法就可以得到执行。
         */

        /**
         * 从 Android 4.4 版本开始，Alarm 任务的触发时间将会变得不准确，
         有可能会延迟一段时间后任务才能得到执行。这并不是个 bug，而是系统在耗电性方面进行
         的优化。系统会自动检测目前有多少 Alarm 任务存在，然后将触发时间将近的几个任务放在
         一起执行，这就可以大幅度地减少 CPU 被唤醒的次数，从而有效延长电池的使用时间。
         当然，如果你要求 Alarm 任务的执行时间必须准备无误，Android 仍然提供了解决方案。
         使用 AlarmManager 的 setExact()方法来替代 set()方法，就可以保证任务准时执行了。
         */

        // manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, );
        startService(new Intent(this, LongRunningService.class));
    }
}
