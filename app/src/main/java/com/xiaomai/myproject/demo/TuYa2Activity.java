package com.xiaomai.myproject.demo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.view.TuYaView;

/**
 * Created by XiaoMai on 2017/2/12 10:59.
 */
public class TuYa2Activity extends Activity {
    /** Called when the activity is first created. */
    private TuYaView handWrite = null;
    private Button clear = null;
    private Button save = null;
    private ImageView saveImage = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuya2);

        handWrite = (TuYaView) findViewById(R.id.handwriteview);
        save = (Button) findViewById(R.id.save);
        saveImage = (ImageView) findViewById(R.id.saveimage);
        clear = (Button) findViewById(R.id.clear);
        clear.setOnClickListener(new clearListener());
        save.setOnClickListener(new saveListener());
        handerl();
    }

    private class clearListener implements View.OnClickListener {

        public void onClick(View v) {
            handWrite.clear();
        }
    }

    public static Handler hh;

    public void handerl() {
        hh = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                int what = msg.what;
                if (what == 3) {
                    saveImage.setVisibility(View.VISIBLE);
                    saveImage.setImageBitmap(handWrite.saveImage());
                    handWrite.setImge();
                }
                super.handleMessage(msg);
            }

        };
    }

    private class saveListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            Message msg = new Message();
            msg = Message.obtain(handWrite.handler1, 2);
            handWrite.handler1.sendMessage(msg);

            if (handWrite.saveImage() != null) {
                Log.i("RG", "111111111111111111111");

            } else {
                saveImage.setVisibility(View.GONE);
            }
        }

    }
}
