
package com.xiaomai.myproject.recyclerview.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected void initViews() {
        super.initViews();
        setTitle("RecyclerView");
    }

    @Override
    protected int getCodeResId() {
        return super.getCodeResId();
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
                break;
            case R.id.bt_recyclerview_delete:
                break;
            case R.id.bt_recyclerview_list:
                break;
            case R.id.bt_recyclerview_grad:
                break;
            case R.id.bt_recyclerview_flow:
                break;
        }
    }
}
