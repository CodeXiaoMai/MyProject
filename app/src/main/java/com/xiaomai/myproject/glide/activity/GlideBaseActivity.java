
package com.xiaomai.myproject.glide.activity;

import android.net.Uri;
import android.os.Environment;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.xiaomai.myproject.R;
import com.xiaomai.myproject.base.BaseActivity;

import java.io.File;

import butterknife.BindView;

public class GlideBaseActivity extends BaseActivity {

    @BindView(R.id.tv_glide_1)
    TextView tvGlide1;

    @BindView(R.id.iv_glide_1)
    ImageView ivGlide1;

    @BindView(R.id.tv_glide_2)
    TextView tvGlide2;

    @BindView(R.id.iv_glide_2)
    ImageView ivGlide2;

    @BindView(R.id.tv_glide_3)
    TextView tvGlide3;

    @BindView(R.id.iv_glide_3)
    ImageView ivGlide3;

    @BindView(R.id.tv_glide_4)
    TextView tvGlide4;

    @BindView(R.id.iv_glide_4)
    ImageView ivGlide4;

    @BindView(R.id.tv_glide_5)
    TextView tvGlide5;

    @BindView(R.id.iv_glide_5)
    ImageView ivGlide5;

    @BindView(R.id.tv_glide_6)
    TextView tvGlide6;

    @BindView(R.id.iv_glide_6)
    ImageView ivGlide6;

    @BindView(R.id.tv_glide_7)
    TextView tvGlide7;

    @BindView(R.id.iv_glide_7)
    ImageView ivGlide7;

    @BindView(R.id.tv_glide_8)
    TextView tvGlide8;

    @BindView(R.id.iv_glide_8)
    ImageView ivGlide8;

    @BindView(R.id.tv_glide_9)
    TextView tvGlide9;

    @BindView(R.id.iv_glide_9)
    ImageView ivGlide9;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_glide_base;
    }

    @Override
    protected void initViews() {
        super.initViews();
        setTitle("Glide的基本使用");
    }

    @Override
    protected int getCodeResId() {
        return R.string.code_glide_base;
    }

    @Override
    protected void loadData() {
        super.loadData();
        // 1.加载网络图片
        tvGlide1.setText("1.加载网络图片");
        Glide.with(mContext).load("https://www.baidu.com/img/bd_logo1.png").into(ivGlide1);

        // 2.加载资源图片
        tvGlide2.setText("2.加载资源图片");
        Glide.with(mContext).load(R.drawable.ic_launcher).into(ivGlide2);

        // 3.加载本地图片
        tvGlide3.setText("3.加载本地图片");
        String path = Environment.getExternalStorageDirectory() + "/meinv1.jpg";
        File file = new File(path);
        Uri uri = Uri.fromFile(file);
        Glide.with(mContext).load(uri).into(ivGlide3);

        // 4.加载网络gif
        tvGlide4.setText("4.加载网络gif");
        String gifUrl = "http://b.hiphotos.baidu.com/zhidao/pic/item/faedab64034f78f066abccc57b310a55b3191c67.jpg";
        Glide.with(mContext).load(gifUrl).placeholder(R.drawable.ic_launcher).into(ivGlide4);

        // 5.加载资源gif
        tvGlide5.setText("5.加载资源gif");
        Glide.with(mContext).load(R.drawable.anim).asGif().placeholder(R.drawable.ic_launcher)
                .into(ivGlide5);

        // 6.加载本地gif
        tvGlide6.setText("6.加载本地gif");
        String gifPath = Environment.getExternalStorageDirectory() + "/meinv2.jpg";
        File gifFile = new File(gifPath);
        Glide.with(mContext).load(gifFile).placeholder(R.drawable.ic_launcher).into(ivGlide6);

        // 7.加载本地小视频和快照
        tvGlide7.setText("7.加载本地小视频和快照");
        String videoPath = Environment.getExternalStorageDirectory() + "/video.mp4";
        File videoFile = new File(videoPath);
        Glide.with(mContext).load(Uri.fromFile(videoFile)).placeholder(R.drawable.ic_launcher)
                .into(ivGlide7);

        // 8.设置缩略图比例,然后先加载缩略图,再加载原图
        tvGlide8.setText("8.设置缩略图比例,然后先加载缩略图,再加载原图");
        String urlPath = Environment.getExternalStorageDirectory() + "/meinv1.jpg";
        Glide.with(mContext).load(new File(urlPath)).thumbnail(0.1f).centerCrop()
                .placeholder(R.drawable.ic_launcher).into(ivGlide8);

        // 9.建立一个缩略图对象，然后先加载缩略图，再加载原图
        tvGlide9.setText("9.建立一个缩略图对象，然后先加载缩略图，再加载原图");
        DrawableRequestBuilder thumbnailRequest = Glide.with(mContext).load(new File(urlPath));
        Glide.with(mContext).load(gifUrl).thumbnail(thumbnailRequest).centerCrop()
                .placeholder(R.drawable.ic_launcher).into(ivGlide9);
    }

}
