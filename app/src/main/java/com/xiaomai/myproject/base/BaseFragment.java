package com.xiaomai.myproject.base;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by XiaoMai on 2016/8/29.
 */
public abstract class BaseFragment extends Fragment {

    /**
     * 初始化数据
     */
    protected abstract void initVariables();

    /**
     * 初始化布局
     */
    protected abstract void initViews();

    /**
     * 加载数据
     */
    protected abstract void loadData();
}
