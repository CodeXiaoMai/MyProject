package com.xiaomai.myproject.view;

import android.app.Activity;
import android.content.Context;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.utils.TimeUtils;

import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

/**
 * Created by XiaoMai on 2016/10/15 10:56.
 */
public class MyMediaController extends MediaController {

    private Context mContext;
    /**
     * 对视屏进行控制
     */
    private VideoView mVideoView;
    /**
     * 用于退出视频
     */
    private Activity mActivity;
    /**
     * 手势管理器
     */
    public GestureDetector mGestureDetector;
    /**
     * 返回
     */
    private View mButtonBack;
    /**
     * 视频的名字
     */
    private TextView mVideoName;
    /**
     * 当前电量
     */
    private TextView mBatteryNum;
    /**
     * 当前时间
     */
    private TextView mTime;
    /**
     * 播放暂停按钮
     */
    private ImageView mButtonPlay;
    private ImageView mButtonPlayAll;
    /**
     * 亮度和音量
     */
    private LinearLayout mVolumeAndLight;
    private ImageView mImageViewVolumeAndLight;
    private TextView mVolumeAndLightProgress;

    /**
     * @param context
     * @param videoView
     * @param activity
     */
    public MyMediaController(Context context, VideoView videoView, Activity activity) {
        super(context);
        this.mContext = context;
        this.mVideoView = videoView;
        this.mActivity = activity;
//        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        mGestureDetector = new GestureDetector(mContext, new MyGestureListener());
    }

    @Override
    protected View makeControllerView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.my_media_controller, null);
//        view.setMinimumHeight();
        mButtonBack = view.findViewById(R.id.iv_back);
        mButtonBack.setOnClickListener(backListener);
        mVideoName = (TextView) view.findViewById(R.id.mediacontroller_file_name);
        mBatteryNum = (TextView) view.findViewById(R.id.tv_battery);
        mTime = (TextView) view.findViewById(R.id.tv_time);
        mVolumeAndLight = (LinearLayout) view.findViewById(R.id.linear_volume_light);
        mImageViewVolumeAndLight = (ImageView) view.findViewById(R.id.iv_volume_light);
        mVolumeAndLightProgress = (TextView) view.findViewById(R.id.tv_volume_light_progress);
        return view;
    }

    /**
     * 返回的监听
     */
    private OnClickListener backListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mActivity != null) {
                mActivity.finish();
            }
        }
    };

    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        /**
         * 单击
         *
         * @param e
         * @return
         */
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            toggleMediaControlsVisibility();
            return super.onSingleTapConfirmed(e);
        }

    }

    /**
     * 切换播放状态
     */
    private void togglePlayStatus() {
        if (mVideoView != null) {
            if (mVideoView.isPlaying()) {
                mVideoView.pause();
            } else {
                mVideoView.start();
            }
        } else {
            MyToast.show(mContext, "没有正在播放的视频");
        }
    }

    /**
     * 切换控制器的状态
     */
    private void toggleMediaControlsVisibility() {
        if (isShowing()) {
            hide();
        } else {
            show();
            setTime();
        }
    }

    /**
     * 设置时间
     */
    public void setTime() {
        if (mTime != null) {
            mTime.setText(TimeUtils.getTime());
        }
    }

    /**
     * 设置电量
     *
     * @param battery
     */
    public void setBattery(int battery) {
        if (mBatteryNum != null) {
            mBatteryNum.setText(battery + "%");
        }
    }

}
