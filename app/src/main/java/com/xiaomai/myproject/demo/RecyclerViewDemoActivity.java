package com.xiaomai.myproject.demo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.adapter.RecyclerAdapter;
import com.xiaomai.myproject.base.BaseActivity;
import com.xiaomai.myproject.view.DividerGridItemDecoration;
import com.xiaomai.myproject.view.MyToast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XiaoMai on 2016/10/13 17:13.
 */
public class RecyclerViewDemoActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    private List<String> mList;
    private Handler mHandler;
    private RecyclerAdapter myAdapter;
    private int i = 'A';
    private boolean add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initVariables() {
        super.initVariables();
        mList = new ArrayList<>();
        for (; i <= 'Z'; i++) {
            mList.add(String.valueOf((char) i));
        }
        mHandler = new Handler();
        myAdapter = new RecyclerAdapter(mContext, mList);
        myAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                MyToast.show(mContext, String.valueOf(position));
            }

            @Override
            public void onItemLongClick(View view, int position) {
                MyToast.show(mContext, "changan" + String.valueOf(position));
            }
        });
        add = true;
        mHandler.postDelayed(mRunnable, 1000);
    }

    Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            if (add) {
                mList.remove(0);
                //删除时调用此方法才能显示动画
                myAdapter.notifyItemRemoved(0);
                if (mList.size() <= 0){
                    add = !add;
                }
            } else {
                if (mList.size() == 0) {
                    i = 'A';
                }
                mList.add(String.valueOf((char) (i++)));
                //增加时调用此方法才会显示动画
                myAdapter.notifyItemInserted(i);
                if (mList.size() >= 26){
                    add = !add;
                }
            }
            mHandler.postDelayed(mRunnable, 500);
        }
    };

    @Override
    protected void initViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.activity_recyclerView);
        //设置动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //垂直排列
        /*mRecyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));*/
        //水平排列跨屏
       /* mRecyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.HORIZONTAL_LIST));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(
                mContext,
                LinearLayoutManager.HORIZONTAL,
                false   //默认传false，若为true则顺序反转
        ));*/
        //网格排列
       /* mRecyclerView.addItemDecoration(new DividerGridItemDecoration(mContext));
        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));*/
        //瀑布流式
        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(mContext));
        //垂直排列4列
//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
        //水平排列4行
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.HORIZONTAL));
        mRecyclerView.setAdapter(myAdapter);
    }

    @Override
    protected int getContentLayout() {
        return R.layout.act_recyclerview;
    }


}
