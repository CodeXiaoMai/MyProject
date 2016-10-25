package com.xiaomai.myproject.demo;

import android.os.Bundle;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.xiaomai.myproject.R;
import com.xiaomai.myproject.base.BaseActivity;
import com.xiaomai.myproject.utils.BitmapCache;
import com.xiaomai.myproject.utils.MyLog;

/**
 * Created by XiaoMai on 2016/10/11 12:47.
 */
public class VolleyAndImageLoaderDemoActivity extends BaseActivity{

    private NetworkImageView mNetworkImageView;

    private ImageView mImageView;

    private RequestQueue mRequestQueue;

    private ImageLoader mImageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("这是标题");
//        startActivity(new Intent(this, DragListViewDemoActivity.class));
    }

    @Override
    protected void initVariables() {

        mRequestQueue = Volley.newRequestQueue(this);

        mImageLoader = new ImageLoader(mRequestQueue, new BitmapCache());

    }

    @Override
    protected void initViews() {

        mNetworkImageView = (NetworkImageView) findViewById(R.id.activity_main_iv_networkImageView);

        mImageView = (ImageView)findViewById(R.id.activity_main_iv_imageView);

        ImageLoader.ImageListener imageListener = ImageLoader.getImageListener(mImageView, R.mipmap.ic_launcher, R.mipmap.ic_launcher);

        String uri = "https://www.baidu.com/img/bd_logo1.png";

        mImageLoader.get(uri, imageListener);

        mNetworkImageView.setErrorImageResId(R.mipmap.ic_launcher);

        mNetworkImageView.setDefaultImageResId(R.mipmap.ic_launcher);

        mNetworkImageView.setImageUrl(uri, mImageLoader);

        StringRequest stringRequest = new StringRequest("http://www.baidu.com", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                MyLog.e("onResponse1");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        stringRequest.setTag("1");
        mRequestQueue.add(stringRequest);
        stringRequest = new StringRequest("http://www.csdn.net", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                MyLog.e("onResponse2");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        stringRequest.setTag("2");
        mRequestQueue.add(stringRequest);
        mRequestQueue.start();
        mRequestQueue.cancelAll(new RequestQueue.RequestFilter() {
            @Override
            public boolean apply(Request<?> request) {
                return true;
            }
        });
    }

    @Override
    protected int getContentLayout() {
        return R.layout.act_volley_imageloader;
    }
}
