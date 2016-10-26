package com.xiaomai.myproject.demo;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.xiaomai.myproject.R;
import com.xiaomai.myproject.base.BaseActivity;
import com.xiaomai.myproject.utils.BitmapCache;

import pl.droidsonroids.gif.GifImageView;

/**
 * Created by XiaoMai on 2016/10/20 12:52.
 */
public class GifDemoActivity extends BaseActivity {

    private GifImageView mGifImageView;
    private GifImageView mGifImageView2;

    private RequestQueue mRequestQueue;

    private ImageLoader mImageLoader;

    @Override
    protected void initVariables() {
        super.initVariables();
        mRequestQueue = Volley.newRequestQueue(mContext);
        mImageLoader = new ImageLoader(mRequestQueue, new BitmapCache());
    }

    @Override
    protected void initViews() {
        mGifImageView = (GifImageView) findViewById(R.id.activity_gif_giv);
        mGifImageView2 = (GifImageView) findViewById(R.id.activity_gif_giv2);

//        mGifImageView.setImageURI(Uri.parse("http://img.lanrentuku.com/img/allimg/1407/5-140FGZ246-50.gif"));

        mImageLoader.get("http://img.lanrentuku.com/img/allimg/1407/5-140FGZ246-50.gif", new ImageLoader.ImageListener() {
                    @Override
                    public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                        if (!isImmediate) {
                            Bitmap bitmap = response.getBitmap();
                            BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmap);
                            mGifImageView.setImageBitmap(bitmap);
                        }
                    }

                    @Override
                    public void onErrorResponse(VolleyError error) {

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
//        try {
//            final GifDrawable gifDrawable = new GifDrawable(getAssets(), "anim.gif");
//            multiCallback.scheduleDrawable(gifDrawable, new Runnable() {
//                @Override
//                public void run() {
//                    gifDrawable.stop();
//                }
//            }, 5 * 1000);
//            mGifImageView.setImageDrawable(gifDrawable);
//            multiCallback.addView(mGifImageView);

//            mGifImageView2.setImageDrawable(gifDrawable);
//            multiCallback.addView(mGifImageView2);
//            gifDrawable.setCallback(multiCallback);

       /* } catch (IOException e) {
            e.printStackTrace();
        }*/


    }

    @Override
    protected int getContentLayout() {
        return R.layout.act_gif_imageview;
    }
}
