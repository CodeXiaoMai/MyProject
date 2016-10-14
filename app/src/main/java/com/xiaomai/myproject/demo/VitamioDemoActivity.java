package com.xiaomai.myproject.demo;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.base.BaseActivity;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;


/**
 * Created by XiaoMai on 2016/10/14 17:18.
 */
public class VitamioDemoActivity extends BaseActivity implements MediaPlayer.OnPreparedListener,
        MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener {

    private VideoView mVideoView;
    private String mVideoUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initVariables() {
        super.initVariables();
        //一定要初始化
        Vitamio.isInitialized(mContext);
        mVideoUrl = "http://qiubai-video.qiushibaike.com/91B2TEYP9D300XXH_3g.mp4";
    }

    @Override
    protected void initViews() {
        mVideoView = (VideoView) findViewById(R.id.activity_vitamio_videoView);
        //播放网络视频
        mVideoView.setVideoURI(Uri.parse(mVideoUrl));
        //播放本地视频
       /* String path = Environment.getExternalStorageDirectory() + "/test.mp4";
        mVideoView.setVideoPath(path);*/
        MediaController mediaController = new MediaController(mContext);
//        mediaController.show();//默认显示3秒
        mediaController.show(5000);//显示5秒
        mVideoView.setMediaController(mediaController);
        //设置视频的播放质量 高、自动、低
        mVideoView.setVideoQuality(View.DRAWING_CACHE_QUALITY_AUTO);
        //准备就绪
        mVideoView.setOnPreparedListener(this);
        //播放完成
        mVideoView.setOnCompletionListener(this);
        //播放出错
        mVideoView.setOnErrorListener(this);
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

}
