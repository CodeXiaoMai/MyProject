package com.xiaomai.myproject.demo;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.xiaomai.myproject.R;
import com.xiaomai.myproject.base.BaseActivity;

/**
 * Created by XiaoMai on 2016/10/26 11:45.
 *
 * compile 'com.github.bumptech.glide:glide:3.7.0'
 */
public class GlideDemoActivity extends BaseActivity {

    /**
     * 本地图片
     */
    private ImageView mImageViewLocal;

    /**
     * Gif动画
     */
    private ImageView mImageViewGif;

    @Override
    protected void initViews() {
        mImageViewLocal = (ImageView) findViewById(R.id.imageview_local);
        String url_local = "https://www.baidu.com/img/bd_logo1.png";
        Glide
                .with(this)
                .load(url_local)
                .into(mImageViewLocal);

        mImageViewGif = (ImageView) findViewById(R.id.imageview_gif);
        String url_gif = "http://img.lanrentuku.com/img/allimg/1407/5-140FGZ246-50.gif";
        Glide
                .with(this)
                .load(url_gif)
                .placeholder(R.drawable.ic_launcher)
                .error(R.drawable.back)
                .into(mImageViewGif);
    }

    @Override
    protected int getContentLayout() {
        return R.layout.act_glide;
    }
}
