package com.xiaomai.myproject.demo;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.base.BaseActivity;

import java.io.IOException;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

/**
 * Created by XiaoMai on 2016/10/20 12:52.
 */
public class GifDemoActivity extends BaseActivity {

    private GifImageView mGifImageView;
    private GifImageView mGifImageView2;



    @Override
    protected void initViews() {
        mGifImageView = (GifImageView) findViewById(R.id.activity_gif_giv);
        mGifImageView2 = (GifImageView) findViewById(R.id.activity_gif_giv2);
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
        try {
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
        }


    }

    @Override
    protected int getContentLayout() {
        return R.layout.act_gif_imageview;
    }
}
