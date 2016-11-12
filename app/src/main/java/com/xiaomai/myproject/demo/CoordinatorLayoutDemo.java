package com.xiaomai.myproject.demo;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.base.BaseActivity;

/**
 * CoordinatorLayout [koʊ'ɔdənˌeɪtə]
 * 这个控件的目的就是协调它里面View的行为
 * Created by XiaoMai on 2016/11/5 16:46.
 */
public class CoordinatorLayoutDemo extends BaseActivity {
    @Override
    protected void initViews() {


    }

    @Override
    protected int getContentLayout() {
        return R.layout.act_coordinator;
    }

    @Override
    protected boolean isWithoutToolbar() {
        return true;
    }

}
