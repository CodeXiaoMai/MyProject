
package com.xiaomai.myproject.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.base.BaseActivity;
import com.xiaomai.myproject.utils.Const;

import butterknife.BindView;
import butterknife.OnClick;

public class DataStructActivity extends BaseActivity {

    @BindView(R.id.bt_data_struct_1)
    Button btDataStruct1;

    @BindView(R.id.bt_data_struct_2)
    Button btDataStruct2;

    @BindView(R.id.bt_data_struct_3)
    Button btDataStruct3;

    @BindView(R.id.bt_data_struct_4)
    Button btDataStruct4;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_data_struct;
    }

    @Override
    protected void initViews() {
        super.initViews();
        setTitle("数据结构");
        setOnMoreClickListener(null);
    }

    @OnClick({
            R.id.bt_data_struct_1, R.id.bt_data_struct_2, R.id.bt_data_struct_3, R.id.bt_data_struct_4
    })
    public void onClick(View view) {
        Intent intent = new Intent(this, WebViewActivity.class);
        switch (view.getId()) {
            case R.id.bt_data_struct_1:
                intent.putExtra(Const.URL,
                        "https://github.com/CodeXiaoMai/DataStructure/blob/master/01.%E9%A2%84%E5%A4%87%E7%9F%A5%E8%AF%86.md");
                break;
            case R.id.bt_data_struct_2:
                intent.putExtra(Const.URL,
                        "https://github.com/CodeXiaoMai/DataStructure/blob/master/02.%E7%BA%BF%E6%80%A7%E7%BB%93%E6%9E%84.md");
                break;
            case R.id.bt_data_struct_3:
                intent.putExtra(Const.URL,
                        "https://github.com/CodeXiaoMai/DataStructure/blob/master/03.%E9%93%BE%E8%A1%A8%E5%87%86%E5%A4%87%E7%9F%A5%E8%AF%86.md");
                break;
            case R.id.bt_data_struct_4:
                intent.putExtra(Const.URL,
                        "https://github.com/CodeXiaoMai/DataStructure/blob/master/04.%E9%93%BE%E8%A1%A8.md");
                break;
        }
        startActivity(intent);
    }

}
