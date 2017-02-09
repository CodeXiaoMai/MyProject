
package com.xiaomai.myproject.recyclerview.activity;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.base.BaseActivity;
import com.xiaomai.myproject.recyclerview.adapter.MyRecyclerViewAdapter;
import com.xiaomai.myproject.view.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by XiaoMai on 2017/2/5 18:55.
 */
public class RecyclerViewActivity extends BaseActivity {

    @BindView(R.id.bt_recyclerview_add)
    Button btRecyclerviewAdd;

    @BindView(R.id.bt_recyclerview_delete)
    Button btRecyclerviewDelete;

    @BindView(R.id.bt_recyclerview_list)
    Button btRecyclerviewList;

    @BindView(R.id.bt_recyclerview_grad)
    Button btRecyclerviewGrad;

    @BindView(R.id.bt_recyclerview_flow)
    Button btRecyclerviewFlow;

    @BindView(R.id.rl_recyclerview)
    RecyclerView rlRecyclerView;

    private List<String> datas;

    private MyRecyclerViewAdapter mAdapter;

    @Override
    protected void initViews() {
        super.initViews();
        setTitle("RecyclerView");

        // 设置RecyclerView的适配器
        mAdapter = new MyRecyclerViewAdapter(mContext, datas);
        rlRecyclerView.setAdapter(mAdapter);
        rlRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        rlRecyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        mAdapter.setOnItemClickListener(new MyRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, String data) {
                Toast.makeText(RecyclerViewActivity.this, "data ==" + data, Toast.LENGTH_SHORT)
                        .show();
            }
        });
        rlRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    protected void initVariables() {
        super.initVariables();
        datas = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            datas.add("Content_" + i);
        }
    }

    @Override
    protected int getCodeResId() {
        return R.string.code_recycler;
    }

    @Override
    protected int getContentLayout() {
        return R.layout.activity_recyclerview;
    }

    @OnClick({
            R.id.bt_recyclerview_add, R.id.bt_recyclerview_delete, R.id.bt_recyclerview_list,
            R.id.bt_recyclerview_grad, R.id.bt_recyclerview_flow
    })
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_recyclerview_add:
                mAdapter.addData(0, "new data");
                rlRecyclerView.scrollToPosition(0);
                break;
            case R.id.bt_recyclerview_delete:
                mAdapter.removeData(2);
                break;
            // 设置List类型效果
            case R.id.bt_recyclerview_list:
                // LayoutManager
                // rlRecyclerView.setLayoutManager(
                // new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,
                // false));
                rlRecyclerView.setLayoutManager(
                        new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
                // 设置位置到末尾
                // rlRecyclerView.scrollToPosition(datas.size() - 1);
                break;
            // 设置Grid类型效果
            case R.id.bt_recyclerview_grad:
                rlRecyclerView.setLayoutManager(
                        new GridLayoutManager(mContext, 3, GridLayoutManager.VERTICAL, false));
                break;
            // 设置瀑布流效果
            case R.id.bt_recyclerview_flow:
                rlRecyclerView.setLayoutManager(
                        new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                break;
        }
    }
}
