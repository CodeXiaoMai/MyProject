package com.xiaomai.myproject.demo;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.base.BaseActivity;

/**
 * Created by XiaoMai on 2016/11/2 13:31.
 */
public class SwipeRefreshLayoutDemo extends BaseActivity {

    private SwipeRefreshLayout mSwipeRefreshLayout;

    private RecyclerView mRecyclerView;

    @Override
    protected void initViews() {
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        //背景色
        mSwipeRefreshLayout.setProgressBackgroundColorSchemeResource(R.color.black);
        //进度条颜色
        mSwipeRefreshLayout.setColorSchemeResources(
                R.color.colorAccent,
                R.color.colorPrimary,
                R.color.colorPrimaryDark,
                R.color.textBlue,
                R.color.white,
                R.color.toast,
                R.color.gray);
//        mSwipeRefreshLayout.setProgressViewOffset();
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 15 * 1000);
            }
        });
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    }

    @Override
    protected int getContentLayout() {
        return R.layout.act_swiperefreshlayout;
    }
}
