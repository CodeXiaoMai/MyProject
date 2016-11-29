
package com.xiaomai.myproject.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XiaoMai on 2016/11/29 14:01.
 */
public class ActivityCollector {

    private static List<Activity> activities;

    /**
     * 启动Activity后加入集合
     * 
     * @param activity
     */
    public static void addActivity(Activity activity) {
        if (activity == null) {
            return;
        }
        if (activities == null) {
            activities = new ArrayList<>();
        }
        if (activities.contains(activity)) {
            return;
        }
        activities.add(activity);
    }

    /**
     * Activity销毁后移出集合
     * 
     * @param activity
     */
    public static void removeActivity(Activity activity) {
        if (activity == null || activities == null || activities.size() <= 0) {
            return;
        }
        activities.remove(activity);
    }

    /**
     * 退出所有的Activity
     */
    public static void finishAll() {
        if (activities == null || activities.size() <= 0) {
            return;
        }
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    public static List<Activity> getActivities() {
        return activities;
    }
}
