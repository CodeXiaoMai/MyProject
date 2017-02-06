package com.xiaomai.myproject.demo;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.base.BaseActivity;
import com.xiaomai.myproject.utils.TimeUtils;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.utils.StringUtils;
import io.vov.vitamio.widget.VideoView;

/**
 * Created by XiaoMai on 2016/10/17 11:24.
 */
public class VitamioWithoutControllerDemoActivity extends BaseActivity implements MediaPlayer.OnPreparedListener,
        MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener {

    private static final int BATTERY = 1;
    /**
     * 顶部控件
     */
    private View mViewTop;
    /**
     * 底部控件
     */
    private View mViewBottom;
    /**
     * 顶部和底部控件是否在显示
     */
    private boolean mIsShow;
    /**
     * 返回按钮
     */
    private View mViewBack;
    /**
     * 文件名
     */
    private TextView mTextViewFileName;
    /**
     * 电池电量
     */
    private TextView mTextViewBattery;
    /**
     * 系统时间
     */
    private TextView mTextViewSystemTime;
    /**
     * 底部播放暂停按钮
     */
    private ImageView mImageViewPlayOrPause;
    /**
     * 当前播放的时间
     */
    private TextView mTextViewCurrentPlayTime;
    /**
     * 进度条
     */
    private SeekBar mSeekBar;
    /**
     * 滚动条是否在滑动
     */
    private boolean mIsDrag;
    /**
     * 视屏总时间
     */
    private TextView mTextViewTotalTime;
    private long mTotalTime;
    /**
     * 屏幕中间的播放暂停按钮
     */
    private ImageView mImageViewAllPlayOrPause;
    /**
     * 重播
     */
    private ImageView mImageViewRePlay;
    /**
     *
     */
    private View mViewLightOrVolume;
    /**
     * 亮度或者音量
     */
    private ImageView mImageViewLightOrVolume;
    /**
     * 亮度或者音量的值
     */
    private TextView mTextViewLightOrColumn;
    /**
     * 视屏播放控件
     */
    private VideoView mVideoView;
    /**
     * 播放地址
     */
    private String mVideoUrl;
    /**
     * 是否是播放完毕
     */
    private boolean mIsComplete;

    /**
     * 当前声音
     */
    private int mVolume = -1;
    /**
     * 最大声音
     */
    private int mMaxVolume;
    /**
     * 音频管理器
     */
    private AudioManager mAudioManager;
    /**
     * 当前亮度
     */
    private float mBrightness = -1f;
    /**
     * 锁屏
     */
    private ImageView mImageViewLock;
    /**
     * 当前锁定状态
     */
    private boolean mIsLock;
    /**
     * 全屏
     */
    private ImageView mImageViewFullScreen;

    private Handler mHandler;

    private GestureDetector mGestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //定义全屏参数
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setFlags(flag, flag);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(mBatteryBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mBatteryBroadcastReceiver);
    }

    @Override
    protected boolean isWithoutToolbar() {
        return true;
    }

    @Override
    protected void initVariables() {
        super.initVariables();
        //一定要初始化
        Vitamio.isInitialized(mContext);
        mVideoUrl = "http://uploads2.vyanke.com/Media/2016/0715/37a75a68a1a515807fea9542be4ee4bd.mp4?key=a9aaec19577a203b4fedb8951be8eae4&st=zarOg5adEjn0xWG_c3b38Q&e=1476773795";
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case BATTERY:
                        mTextViewBattery.setText(msg.obj.toString() + "%");
                        break;
                }
            }
        };
        mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        //获取系统的最大声音
        mMaxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
    }

    @Override
    protected void initViews() {
        mViewBack = findViewById(R.id.iv_back);
        mViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTextViewFileName = (TextView) findViewById(R.id.tv_file_name);
        mTextViewBattery = (TextView) findViewById(R.id.tv_battery);
        mTextViewSystemTime = (TextView) findViewById(R.id.tv_time);
        mImageViewPlayOrPause = (ImageView) findViewById(R.id.iv_play_pause);
        mImageViewPlayOrPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePlayOrPause();
            }
        });
        mTextViewCurrentPlayTime = (TextView) findViewById(R.id.tv_time_current);
        mSeekBar = (SeekBar) findViewById(R.id.seekbar);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    long newProgress = mTotalTime * progress / 1000;
                    mVideoView.seekTo(newProgress);
                    mTextViewCurrentPlayTime.setText(StringUtils.generateTime(newProgress));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                mIsDrag = true;
                stop();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mIsDrag = false;
                play();
            }
        });
        mTextViewTotalTime = (TextView) findViewById(R.id.tv_time_total);
        mImageViewAllPlayOrPause = (ImageView) findViewById(R.id.iv_play_pause_all);
        mImageViewAllPlayOrPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePlayOrPause();
            }
        });
        mImageViewRePlay = (ImageView) findViewById(R.id.iv_replay);
        mImageViewRePlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVideoView.seekTo(0);
                play();
                mIsComplete = false;
            }
        });
        mImageViewLock = (ImageView) findViewById(R.id.iv_lock);
        mImageViewLock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsLock) {
                    mImageViewLock.setImageResource(R.drawable.unlock);
                    showTopAndBottom();
                } else {
                    mImageViewLock.setImageResource(R.drawable.lock);
                    hiddenTopAndBottom();
                }
                mIsLock = !mIsLock;
            }
        });
        mImageViewFullScreen = (ImageView) findViewById(R.id.iv_fullscreen);
        mImageViewFullScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                    //变为横屏
                    mImageViewFullScreen.setImageResource(R.drawable.fullscreen_exit);
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                } else {
                    //变为竖屏
                    mImageViewFullScreen.setImageResource(R.drawable.fullscreen);
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                }
            }
        });
        mViewLightOrVolume = findViewById(R.id.linear_volume_light);
        mImageViewLightOrVolume = (ImageView) findViewById(R.id.iv_volume_light);
        mTextViewLightOrColumn = (TextView) findViewById(R.id.tv_volume_light_progress);
        mVideoView = (VideoView) findViewById(R.id.activity_vitamio_videoView);
        //播放网络视频
        mVideoView.setVideoURI(Uri.parse(mVideoUrl));
        //准备就绪
        mVideoView.setOnPreparedListener(this);
        //播放完成
        mVideoView.setOnCompletionListener(this);
        //播放出错
        mVideoView.setOnErrorListener(this);
        mViewTop = findViewById(R.id.ll_top);
        mViewBottom = findViewById(R.id.ll_bottom);
        mHandler.postDelayed(mToggleControllerRunnable, 0);
        mGestureDetector = new GestureDetector(mContext, new MyGestureListener());
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            if (mIsLock) {
                mHandler.removeCallbacks(mToggleControllerRunnable);
                int width = mImageViewLock.getWidth();
                ObjectAnimator show = ObjectAnimator.ofFloat(mImageViewLock, "translationX", -width, 0);
                ObjectAnimator rotation = ObjectAnimator.ofFloat(mImageViewLock, "rotation", 30, -30, 30, -30, 0);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.play(rotation).after(show);
                animatorSet.start();
                mHandler.postDelayed(mToggleControllerRunnable, 5 * 1000);
            } else {
                toggleTopAndBottomStatus();
            }
            return false;
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        //滑动
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            if (mIsLock) {
                return super.onScroll(e1, e2, distanceX, distanceY);
            }
            float beginX = e1.getRawX();
            float beginY = e1.getRawY();
            float endX = e2.getRawX();
            float endY = e2.getRawY();
            Display disp = getWindowManager().getDefaultDisplay();
            int windowWidth = disp.getWidth();
            int windowHeight = disp.getHeight();
            if (Math.abs(endX - beginX) < Math.abs(beginY - endY)) {//上下滑动
                if (beginX > windowWidth / 2)// 右边滑动
                {
                    onVolumeSlide((beginY - endY) / windowHeight);
                } else if (beginX < windowWidth / 2)// 左边滑动
                {
                    onBrightnessSlide((beginY - endY) / windowHeight);
                }
            }
            return super.onScroll(e1, e2, distanceX, distanceY);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mGestureDetector.onTouchEvent(event)) {
            return true;
        }
        // 处理手势结束
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP:
                endGesture();
                break;
        }
        return super.onTouchEvent(event);
    }

    /**
     * 手势结束
     */
    private void endGesture() {
        mVolume = -1;
        mBrightness = -1f;
        // 隐藏
        mHandler.removeMessages(2);
        mHandler.sendEmptyMessageDelayed(2, 600);
    }

    /**
     * 改变音量
     *
     * @param percent
     */
    private void onVolumeSlide(float percent) {
        mHandler.removeCallbacks(mHiddenLightOrVolumeRunnable);
        if (mVolume == -1) {
            mVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
            if (mVolume < 0) {
                mVolume = 0;
            }
        }
        int index = (int) ((percent * mMaxVolume) + mVolume);
        if (index > mMaxVolume) {
            index = mMaxVolume;
        } else if (index < 0) {
            index = 0;
        }
        mViewLightOrVolume.setVisibility(View.VISIBLE);
        mTextViewLightOrColumn.setText((int) (((double) index / mMaxVolume) * 100) + "%");
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, index, 0);
        if (index >= 10) {
            mImageViewLightOrVolume.setImageResource(R.drawable.volmn_100);
        } else if (index >= 5 && index < 10) {
            mImageViewLightOrVolume.setImageResource(R.drawable.volmn_60);
        } else if (index > 0 && index < 5) {
            mImageViewLightOrVolume.setImageResource(R.drawable.volmn_30);
        } else {
            mImageViewLightOrVolume.setImageResource(R.drawable.volmn_no);
        }
        mHandler.postDelayed(mHiddenLightOrVolumeRunnable, 3 * 1000);
    }

    Runnable mHiddenLightOrVolumeRunnable = new Runnable() {
        @Override
        public void run() {
            mViewLightOrVolume.setVisibility(View.GONE);
        }
    };

    /**
     * 滑动改变亮度
     *
     * @param percent
     */
    private void onBrightnessSlide(float percent) {
        mHandler.removeCallbacks(mHiddenLightOrVolumeRunnable);
        if (mBrightness < 0) {
            mBrightness = getWindow().getAttributes().screenBrightness;
            if (mBrightness <= 0.00f)
                mBrightness = 0.50f;
            if (mBrightness < 0.01f)
                mBrightness = 0.01f;
        }
        WindowManager.LayoutParams lpa = getWindow().getAttributes();
        lpa.screenBrightness = mBrightness + percent;
        if (lpa.screenBrightness > 1.0f)
            lpa.screenBrightness = 1.0f;
        else if (lpa.screenBrightness < 0.01f)
            lpa.screenBrightness = 0.01f;
        getWindow().setAttributes(lpa);
        mViewLightOrVolume.setVisibility(View.VISIBLE);
        mTextViewLightOrColumn.setText((int) (lpa.screenBrightness * 100) + "%");
        if (lpa.screenBrightness * 100 >= 90) {
            mImageViewLightOrVolume.setImageResource(R.drawable.light_100);
        } else if (lpa.screenBrightness * 100 >= 80 && lpa.screenBrightness * 100 < 90) {
            mImageViewLightOrVolume.setImageResource(R.drawable.light_90);
        } else if (lpa.screenBrightness * 100 >= 70 && lpa.screenBrightness * 100 < 80) {
            mImageViewLightOrVolume.setImageResource(R.drawable.light_80);
        } else if (lpa.screenBrightness * 100 >= 60 && lpa.screenBrightness * 100 < 70) {
            mImageViewLightOrVolume.setImageResource(R.drawable.light_70);
        } else if (lpa.screenBrightness * 100 >= 50 && lpa.screenBrightness * 100 < 60) {
            mImageViewLightOrVolume.setImageResource(R.drawable.light_60);
        } else if (lpa.screenBrightness * 100 >= 40 && lpa.screenBrightness * 100 < 50) {
            mImageViewLightOrVolume.setImageResource(R.drawable.light_50);
        } else if (lpa.screenBrightness * 100 >= 30 && lpa.screenBrightness * 100 < 40) {
            mImageViewLightOrVolume.setImageResource(R.drawable.light_40);
        } else if (lpa.screenBrightness * 100 >= 20 && lpa.screenBrightness * 100 < 30) {
            mImageViewLightOrVolume.setImageResource(R.drawable.light_30);
        } else if (lpa.screenBrightness * 100 >= 10 && lpa.screenBrightness * 100 < 20) {
            mImageViewLightOrVolume.setImageResource(R.drawable.light_20);
        }
        mHandler.postDelayed(mHiddenLightOrVolumeRunnable, 3 * 1000);
    }

    Runnable mToggleControllerRunnable = new Runnable() {
        @Override
        public void run() {
            toggleTopAndBottomStatus();
        }
    };

    Runnable mUpdateProgressRunnable = new Runnable() {
        @Override
        public void run() {
            //当前播放的时间毫秒
            long position = mVideoView.getCurrentPosition();
            //视频的总时间毫秒
            long duration = mVideoView.getDuration();
            mTotalTime = duration;
            if (mSeekBar != null) {
                if (duration > 0) {
                    long pos = 1000L * position / duration;
                    mSeekBar.setProgress((int) pos);
                }
                //第二进度
                int percent = mVideoView.getBufferPercentage();
                mSeekBar.setSecondaryProgress(percent * 10);
            }
            if (mTextViewCurrentPlayTime != null) {
                mTextViewCurrentPlayTime.setText(StringUtils.generateTime(position));
            }
            if (mTextViewTotalTime != null) {
                mTextViewTotalTime.setText(StringUtils.generateTime(duration));
            }
            mHandler.postDelayed(mUpdateProgressRunnable, 500);
        }
    };

    /**
     * 切换顶部和底部控件的显示状态
     */

    private void toggleTopAndBottomStatus() {
        mHandler.removeCallbacks(mToggleControllerRunnable);
        if (mIsShow && !mIsDrag) {
            hiddenTopAndBottom();
        } else {
            showTopAndBottom();
        }
        mIsShow = !mIsShow;
    }

    private void hiddenTopAndBottom() {
        int height = mViewTop.getHeight();
        int width = mImageViewFullScreen.getWidth();

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mViewTop, "translationY", 0, -height);
        objectAnimator.start();

        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(mViewBottom, "translationY", 0, height);
        objectAnimator2.start();

        ObjectAnimator objectAnimator3 = ObjectAnimator.ofFloat(mImageViewLock, "translationX", 0, -width);
        objectAnimator3.start();

        ObjectAnimator objectAnimator4 = ObjectAnimator.ofFloat(mImageViewFullScreen, "translationX", 0, width);
        objectAnimator4.start();
    }

    private void showTopAndBottom() {
        int height = mViewTop.getHeight();
        int width = mImageViewFullScreen.getWidth();
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mViewTop, "translationY", -height, 0);
        objectAnimator.start();

        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(mViewBottom, "translationY", height, 0);
        objectAnimator2.start();

        ObjectAnimator objectAnimator3 = ObjectAnimator.ofFloat(mImageViewLock, "translationX", -width, 0);
        objectAnimator3.start();

        ObjectAnimator objectAnimator4 = ObjectAnimator.ofFloat(mImageViewFullScreen, "translationX", width, 0);
        objectAnimator4.start();

        mTextViewSystemTime.setText(TimeUtils.getTime());
        mHandler.postDelayed(mToggleControllerRunnable, 5 * 1000);
    }

    /**
     * 切换播放暂停状态
     */
    private void togglePlayOrPause() {
        if (mVideoView.isPlaying() || mIsComplete) {
            stop();
        } else {
            play();
        }
    }

    private void play() {
        if (!mVideoView.isPlaying()) {
            mVideoView.start();
        }
        mImageViewRePlay.setVisibility(View.GONE);
        mImageViewPlayOrPause.setImageResource(R.drawable.video_pause);
        mImageViewAllPlayOrPause.setVisibility(View.GONE);
        mHandler.postDelayed(mUpdateProgressRunnable, 0);
    }

    private void stop() {
        mHandler.removeCallbacks(mUpdateProgressRunnable);
        if (mVideoView.isPlaying()) {
            mVideoView.pause();
        }
        if (mIsComplete) {
            mImageViewRePlay.setVisibility(View.VISIBLE);
        } else {
            mImageViewPlayOrPause.setImageResource(R.drawable.video_play);
            mImageViewAllPlayOrPause.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected int getContentLayout() {
        return R.layout.act_vitamio_without_controller;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        play();
        disMissProgressDialog();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        mIsComplete = true;
        stop();
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

    BroadcastReceiver mBatteryBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction())) {
                //获取当前电量
                int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
                //电量的总量
                int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, 100);
                Message message = new Message();
                message.obj = String.valueOf((level * 100) / scale);
                message.what = BATTERY;
                mHandler.sendMessage(message);
            }
        }
    };
}
