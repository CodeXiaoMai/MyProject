package com.xiaomai.myproject;

import com.xiaomai.myproject.nohttpdemo.activity.StartActivity;
import com.xiaomai.myproject.utils.ActivityCollector;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {

    @Test
    public void testAddActivity() throws Exception{
        assertEquals(null, ActivityCollector.getActivities());
        StartActivity startActivity = new StartActivity();
        ActivityCollector.addActivity(startActivity);
        assertEquals(1, ActivityCollector.getActivities().size());
        ActivityCollector.addActivity(startActivity);
        assertEquals(1, ActivityCollector.getActivities().size());
    }
}