package com.xiaomai.myproject.demo;

import android.os.Environment;
import android.widget.TextView;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.base.BaseActivity;
import com.yolanda.nohttp.Headers;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.download.DownloadListener;
import com.yolanda.nohttp.download.DownloadQueue;
import com.yolanda.nohttp.download.DownloadRequest;

import java.io.File;
import java.io.IOException;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

/**
 * Created by XiaoMai on 2016/10/20 12:52.
 */
public class GifDemoActivity extends BaseActivity {

    private static final int DOWNLOAD = 1;
    private GifImageView mGifImageView;
    private GifImageView mGifImageView2;

    private DownloadQueue mDownloadQueue;

    private TextView mTextView;

    @Override
    protected void initViews() {
        mDownloadQueue = NoHttp.newDownloadQueue();
        mTextView = (TextView) findViewById(R.id.tv_progress);
        mGifImageView = (GifImageView) findViewById(R.id.activity_gif_giv);
        mGifImageView2 = (GifImageView) findViewById(R.id.activity_gif_giv2);
        DownloadRequest downloadRequest = NoHttp.createDownloadRequest("http://s1.dwstatic.com/group1/M00/1A/8D/aa8f5271bf870ebb9da9e4782ef2e7a6.gif"
                , Environment.getExternalStorageDirectory().getAbsolutePath(), "test",true, false);
        mDownloadQueue.add(DOWNLOAD, downloadRequest, new DownloadListener() {
            @Override
            public void onDownloadError(int what, Exception exception) {

            }

            @Override
            public void onStart(int what, boolean isResume, long rangeSize, Headers responseHeaders, long allCount) {
                mGifImageView.setImageResource(R.drawable.ic_launcher);
            }

            @Override
            public void onProgress(int what, int progress, long fileCount) {
               mTextView.setText(progress + "");
            }

            @Override
            public void onFinish(int what, String filePath) {
                try {
                    GifDrawable gifDrawable = new GifDrawable(new File(filePath));
                    mGifImageView.setImageDrawable(gifDrawable);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancel(int what) {

            }
        });

       /* try {
            GifDrawable gifDrawable = new GifDrawable(getAssets(), "anim.gif");
            mGifImageView.setImageDrawable(gifDrawable);
            final MediaController mediaController = new MediaController(this);
            mediaController.setMediaPlayer((GifDrawable) mGifImageView.getDrawable());
            mediaController.setAnchorView(mGifImageView);
            mGifImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mediaController.show();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }*/

//        MultiCallback multiCallback = new MultiCallback();
      /*  try {
            final GifDrawable gifDrawable = new GifDrawable(getAssets(), "anim.gif");
//            multiCallback.scheduleDrawable(gifDrawable, new Runnable() {
//                @Override
//                public void run() {
//                    gifDrawable.stop();
//                }
//            }, 5 * 1000);
            mGifImageView.setImageDrawable(gifDrawable);
//            multiCallback.addView(mGifImageView);

            mGifImageView2.setImageDrawable(gifDrawable);
//            multiCallback.addView(mGifImageView2);
//            gifDrawable.setCallback(multiCallback);

        } catch (IOException e) {
            e.printStackTrace();
        }*/


    }

    @Override
    protected int getContentLayout() {
        return R.layout.act_gif_imageview;
    }
}
