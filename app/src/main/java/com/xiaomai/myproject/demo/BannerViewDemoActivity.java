
package com.xiaomai.myproject.demo;

import android.view.View;
import android.view.ViewGroup;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.base.BaseActivity;
import com.xiaomai.myproject.utils.MyLog;
import com.xiaomai.myproject.view.BannerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XiaoMai on 2016/11/15 15:28.
 */
public class BannerViewDemoActivity extends BaseActivity {

    BannerView bannerView;

    List<String> imageUrls;

    @Override
    protected void initViews() {
        bannerView = new BannerView(this, 1.8f);
        imageUrls = new ArrayList<>();
        imageUrls.add("https://www.baidu.com/img/bd_logo1.png");
        imageUrls.add("https://www.baidu.com/img/bd_logo1.png");
        imageUrls.add("https://www.baidu.com/img/bd_logo1.png");
        imageUrls.add("https://www.baidu.com/img/bd_logo1.png");
        bannerView.setImageUrls(imageUrls);
        bannerView.setOnItemClickListener(new BannerView.onItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                MyLog.e("position: " + position);
            }
        });

        addContentView(bannerView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    @Override
    protected int getContentLayout() {
        return R.layout.banner_view_demo;
    }
}
