package com.xiaomai.myproject.demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.base.BaseActivity;
import com.xiaomai.myproject.utils.ParseUtils;
import com.xiaomai.myproject.view.MyMediaController;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.VideoView;


/**
 * Created by XiaoMai on 2016/10/14 17:18.
 */
public class VitamioDemoActivity extends BaseActivity implements MediaPlayer.OnPreparedListener,
        MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener {

    private static final int TIME = 0;
    private static final int BATTERY = 1;
    private VideoView mVideoView;
    MyMediaController mMediaController;
    private String mVideoUrl;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //定义全屏参数
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setFlags(flag, flag);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initVariables() {
        super.initVariables();
        //一定要初始化
        Vitamio.isInitialized(mContext);
        mVideoUrl = "http://qiubai-video.qiushibaike.com/91B2TEYP9D300XXH_3g.mp4";
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case TIME:
                        mMediaController.setTime();
                        break;
                    case BATTERY:
                        mMediaController.setBattery(ParseUtils.parseToInteger(msg.obj));
                        break;
                }
            }
        };
    }

    @Override
    protected void initViews() {
        mVideoView = (VideoView) findViewById(R.id.activity_vitamio_videoView);
        //播放网络视频
        mVideoView.setVideoURI(Uri.parse(mVideoUrl));
        //播放本地视频
       /* String path = Environment.getExternalStorageDirectory() + "/test.mp4";
        mVideoView.setVideoPath(path);*/
        mMediaController = new MyMediaController(mContext, mVideoView, this);
        mVideoView.setMediaController(mMediaController);
//        mMediaController.show();//默认显示3秒
        mMediaController.show(5000);//显示5秒
        mVideoView.requestFocus();
        //设置视频的播放质量 高、自动、低
        mVideoView.setVideoQuality(View.DRAWING_CACHE_QUALITY_AUTO);
        //准备就绪
        mVideoView.setOnPreparedListener(this);
        //播放完成
        mVideoView.setOnCompletionListener(this);
        //播放出错
        mVideoView.setOnErrorListener(this);
        registerBroadcastReceiver();
        mHandler.postDelayed(mRunnable, 0);
    }

    @Override
    protected int getContentLayout() {
        return R.layout.act_vitamio;
    }

    @Override
    protected boolean isWithoutToolbar() {
        return true;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mVideoView.start();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        mVideoView.seekTo(0);
        mVideoView.start();
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        return false;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (mVideoView != null) {
            mVideoView.setVideoLayout(VideoView.VIDEO_LAYOUT_SCALE, 0);
        }
        super.onConfigurationChanged(newConfig);
    }

    private BroadcastReceiver batteryBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.ACTION_BATTERY_CHANGED.equals(intent.getAction())) {
                //获取当前电量
                int level = intent.getIntExtra("level", 0);
                //电量的总量
                int scale = intent.getIntExtra("scale", 100);
                Message message = new Message();
                message.obj = String.valueOf((level * 100) / scale);
                message.what = BATTERY;
                mHandler.sendMessage(message);
            }
        }
    };

    /**
     * 注册电量广播监听
     */
    public void registerBroadcastReceiver() {
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(batteryBroadcastReceiver, intentFilter);
    }


    Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            mHandler.sendEmptyMessage(TIME);
            mHandler.postDelayed(mRunnable, 1000);
        }
    };

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mMediaController.isShown()) {
            return mMediaController.mGestureDetector.onTouchEvent(event);
        } else {
            return super.onTouchEvent(event);
        }
    }
}
