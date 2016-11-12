package com.xiaomai.myproject.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by XiaoMai on 2016/11/9 11:31.
 */
public class ListRecyclerView extends RecyclerView {

    public ListRecyclerView(Context context) {
        this(context, null, 0);
    }

    public ListRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ListRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        setItemAnimator(null);
        addItemDecoration(new ListRecyclerCardItemDecortation());
        setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
