package com.xiaomai.myproject.view;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by XiaoMai on 2016/11/9 11:33.
 */
public class ListRecyclerCardItemDecortation extends RecyclerView.ItemDecoration {

    public ListRecyclerCardItemDecortation() {
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(10, 10 ,10 ,10);
    }
}
