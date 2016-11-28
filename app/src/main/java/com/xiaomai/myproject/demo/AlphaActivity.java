
package com.xiaomai.myproject.demo;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by XiaoMai on 2016/11/22 17:50.
 */
public class AlphaActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.TYPE_APPLICATION_ATTACHED_DIALOG,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSPARENT);
        WindowManager windowManager = getWindowManager();
        View view = new View(this);
        view.setBackgroundColor(Color.parseColor("#60ff00ff"));
        windowManager.addView(view, layoutParams);
    }
}
