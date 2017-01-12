
package com.xiaomai.myproject.demo;

import android.app.Activity;
import android.os.Bundle;

import com.orhanobut.logger.Logger;

/**
 * Created by XiaoMai on 2017/1/10 13:40.
 */
public class MatDemoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LeakClass leakClass = new LeakClass();
        Logger.e("new Object");
        leakClass.start();

    }

    static class LeakClass extends Thread {

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(50 * 50 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
