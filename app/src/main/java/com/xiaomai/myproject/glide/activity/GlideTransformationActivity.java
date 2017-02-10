
package com.xiaomai.myproject.glide.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.base.BaseActivity;
import com.xiaomai.myproject.glide.adapter.GlideTransformationAdapter;

import butterknife.BindView;

public class GlideTransformationActivity extends BaseActivity {

    @BindView(R.id.rv_glide_transform)
    RecyclerView rvGlideTransform;

    private String[] mDatas;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_glide_transformation;
    }

    @Override
    protected void initViews() {
        super.initViews();
        setTitle("Glide图形变换");
        rvGlideTransform.setAdapter(new GlideTransformationAdapter(mContext, mDatas));
        rvGlideTransform.setLayoutManager(
                new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    protected int getCodeResId() {
        return R.string.code_glide_transformation;
    }

    @Override
    protected void initVariables() {
        super.initVariables();
        mDatas = new String[21];
        for (int i = 1; i <= 21; i++) {
            mDatas[i - 1] = String.valueOf(i);
        }
    }
}
