
package com.xiaomai.myproject.glide.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class GlideActivity extends BaseActivity {

    @BindView(R.id.bt_glide_base)
    Button btGlideBase;

    @BindView(R.id.bt_glide_recyclerview)
    Button btGlideRecyclerView;

    @BindView(R.id.bt_glide_transformation)
    Button btGlideTransformation;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_glide;
    }

    @Override
    protected void initViews() {
        super.initViews();
        setTitle("Glide的使用");
        setOnMoreClickListener(null);
    }

    @OnClick({
            R.id.bt_glide_base, R.id.bt_glide_recyclerview, R.id.bt_glide_transformation
    })
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_glide_base:
                startActivity(new Intent(mContext, GlideBaseActivity.class));
                break;
            case R.id.bt_glide_recyclerview:
                startActivity(new Intent(mContext, GlideRecyclerViewActivity.class));
                break;
            case R.id.bt_glide_transformation:
                startActivity(new Intent(mContext, GlideTransformationActivity.class));
                break;
        }
    }
}
