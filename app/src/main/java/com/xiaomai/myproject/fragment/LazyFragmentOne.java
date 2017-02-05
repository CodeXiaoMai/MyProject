package com.xiaomai.myproject.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.base.BaseLazyFragment;

/**
 * Created by XiaoMai on 2016/10/4.
 */
public class LazyFragmentOne extends BaseLazyFragment{

    private View view;

    private TextView textView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_one, null);
        isPrepared = true;
        lazyLoad();
        return view;
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected View initView() {
        textView = (TextView) view.findViewById(R.id.fragment_one_tv_one);
        textView.setText("数据加载完毕");
        return textView;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void lazyLoad() {
        if (isPrepared && isFirstTime && isVisible){
            initView();
        }
    }
}
