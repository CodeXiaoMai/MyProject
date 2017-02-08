package com.xiaomai.myproject.picasso.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.xiaomai.myproject.R;
import com.xiaomai.myproject.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class PicassoActivity extends BaseActivity {

    @BindView(R.id.bt_picasso_base)
    Button btPicassoBase;
    @BindView(R.id.bt_picasso_listview)
    Button btPicassoListview;
    @BindView(R.id.bt_picasso_transformations)
    Button btPicassoTransformations;
    @BindView(R.id.iv_picasso_result1)
    ImageView ivPicassoResult1;
    @BindView(R.id.iv_picasso_result2)
    ImageView ivPicassoResult2;
    @BindView(R.id.iv_picasso_result3)
    ImageView ivPicassoResult3;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_picasso;
    }

    @OnClick({R.id.bt_picasso_base, R.id.bt_picasso_listview, R.id.bt_picasso_transformations})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_picasso_base:
                // 基本用法
                Picasso.with(mContext)
                        .load("https://www.baidu.com/img/bd_logo1.png")
                        .into(ivPicassoResult1);
                // 裁剪图片
                Picasso.with(mContext)
                        .load("https://www.baidu.com/img/bd_logo1.png")
                        .resize(100, 100)
                        .into(ivPicassoResult2);
                // 旋转180度
                Picasso.with(mContext)
                        .load("https://www.baidu.com/img/bd_logo1.png")
                        .rotate(180)
                        .into(ivPicassoResult3);
                break;
            case R.id.bt_picasso_listview:
                startActivity(new Intent(mContext, PicassoListViewActivity.class));
                break;
            case R.id.bt_picasso_transformations:
                startActivity(new Intent(mContext, PicassoTransformationActivity.class));
                break;
        }
    }
}
