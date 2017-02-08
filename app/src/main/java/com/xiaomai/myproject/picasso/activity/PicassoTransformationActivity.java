package com.xiaomai.myproject.picasso.activity;

import android.widget.ListView;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.base.BaseActivity;
import com.xiaomai.myproject.picasso.adapter.TransformationsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by XiaoMai on 2017/2/8.
 */
public class PicassoTransformationActivity extends BaseActivity {

    @BindView(R.id.lv_picasso_transformation)
    ListView lvPicassoTransformation;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_picasso_transformation;
    }

    @Override
    protected void initViews() {
        super.initViews();
        setTitle("Picasso的转换操作");
        List<String> data = new ArrayList<>();
        for (int i = 1; i <= 36; i++) {
            data.add(i + "");
        }
        lvPicassoTransformation.setAdapter(new TransformationsAdapter(this, data));
    }
}
