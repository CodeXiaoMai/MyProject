
package com.xiaomai.myproject.wheelpicker.activity;

import android.widget.TextView;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.base.BaseActivity;
import com.xiaomai.myproject.wheelpicker.widget.WheelPickerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by XiaoMai on 2017/2/7 4:17.
 */
public class WheelPickerViewActivity extends BaseActivity {
    @BindView(R.id.wp_wheelpicker)
    WheelPickerView wpWheelpicker;

    @BindView(R.id.tv_wheelpicker)
    TextView tvWheelPicker;

    private List<String> mList;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_wheelpicker;
    }

    @Override
    protected int getCodeResId() {
        return R.string.code_wheel_picker;
    }

    @Override
    protected void initVariables() {
        super.initVariables();
        mList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mList.add(String.valueOf(i));
        }
    }

    @Override
    protected void initViews() {
        super.initViews();
        setTitle("WheelPickerView");
        wpWheelpicker.setOnScrollListener(new WheelPickerView.OnScrollListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                tvWheelPicker.setText("选择结果:" + item);
            }
        });
        wpWheelpicker.setItems(mList);
//        wpWheelpicker.setSelectedItem(1);
    }

}
