
package com.xiaomai.myproject.wheelpicker.activity;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.base.BaseActivity;

/**
 * Created by XiaoMai on 2017/2/7 4:17.
 */
public class WheelPickerViewActivity extends BaseActivity {
    @Override
    protected int getContentLayout() {
        return R.layout.activity_wheelpicker;
    }

    @Override
    protected void initViews() {
        super.initViews();
        setTitle("WheelPickerView");
    }
}
