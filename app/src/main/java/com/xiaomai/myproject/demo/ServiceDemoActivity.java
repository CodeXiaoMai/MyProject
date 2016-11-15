
package com.xiaomai.myproject.demo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.base.BaseActivity;
import com.xiaomai.myproject.service.ServiceDemo;

/**
 * Created by XiaoMai on 2016/11/14 10:51.
 */
public class ServiceDemoActivity extends BaseActivity implements View.OnClickListener {

    private Button mStartService;

    private Button mStopService;

    private Button mBindService;

    private Button mUnbindService;

    private ServiceDemo.MyBinder mBinder;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBinder = (ServiceDemo.MyBinder) service;
            mBinder.startDownload();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void initViews() {
        mStartService = (Button) findViewById(R.id.bt_start_service);
        mStopService = (Button) findViewById(R.id.bt_stop_service);
        mBindService = (Button) findViewById(R.id.bt_bind_service);
        mUnbindService = (Button) findViewById(R.id.bt_unbind_service);

        mStartService.setOnClickListener(this);
        mStopService.setOnClickListener(this);
        mBindService.setOnClickListener(this);
        mUnbindService.setOnClickListener(this);
    }

    @Override
    protected int getContentLayout() {
        return R.layout.act_service;
    }

    @Override
    public void onClick(View v) {
        if (v == mStartService) {
            Intent startIntent = new Intent(this, ServiceDemo.class);
            startService(startIntent);
        } else if (v == mStopService) {
            Intent stopIntent = new Intent(this, ServiceDemo.class);
            stopService(stopIntent);
        } else if (v == mBindService) {
            Intent bindIntent = new Intent(this, ServiceDemo.class);
            bindService(bindIntent, mConnection, BIND_AUTO_CREATE);
        } else if (v == mUnbindService) {
            Intent unbindService = new Intent(this, ServiceDemo.class);
            unbindService(mConnection);
        }
    }
}
