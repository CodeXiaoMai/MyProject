package com.xiaomai.myproject.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by XiaoMai on 2016/8/29.
 */
public abstract class BaseFragment extends Fragment {

    protected Context mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initVariables();
        loadData();
    }

    /**
     * 初始化数据
     */
    protected void initVariables(){}

    /**
     * 初始化布局
     */
    protected abstract View initView();

    /**
     * 联网加载数据，展示数据等等
     */
    protected void loadData(){}
}
