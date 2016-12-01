
package com.xiaomai.myproject.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.view.DrawView;

/**
 * Created by XiaoMai on 2016/11/30 11:28.
 */
public class CustomViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        final DrawView drawView = new DrawView(this);
        drawView.setMinimumHeight(300);
        drawView.setMinimumWidth(500);
        linearLayout.addView(drawView);

        setContentView(R.layout.act_text_image_view);
    }
}
