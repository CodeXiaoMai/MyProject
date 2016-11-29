package com.xiaomai.myproject;

import android.test.AndroidTestCase;

import com.xiaomai.myproject.nohttpdemo.activity.StartActivity;
import com.xiaomai.myproject.utils.ActivityCollector;

/**
 * Created by XiaoMai on 2016/11/29 14:12.
 */
public class ActivityCollectorTest extends AndroidTestCase {

    public void testAddActivity(){
        assertEquals(null, ActivityCollector.getActivities());
        StartActivity startActivity = new StartActivity();
        ActivityCollector.addActivity(startActivity);
        assertEquals(1, ActivityCollector.getActivities().size());
        ActivityCollector.addActivity(startActivity);
        assertEquals(1, ActivityCollector.getActivities().size());
    }
}
