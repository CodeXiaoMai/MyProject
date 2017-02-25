
package com.xiaomai.myproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.base.BaseActivity;
import com.xiaomai.myproject.utils.Const;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DataStructActivity extends BaseActivity {

    @BindView(R.id.bt_data_struct_1)
    Button btDataStruct1;

    @BindView(R.id.bt_data_struct_2)
    Button btDataStruct2;

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
            R.id.bt_data_struct_1, R.id.bt_data_struct_2
    })
    public void onClick(View view) {
        Intent intent = new Intent(this, WebViewActivity.class);
        switch (view.getId()) {
            case R.id.bt_data_struct_1:
                intent.putExtra(Const.URL,
                        "https://github.com/CodeXiaoMai/DataStructure/blob/master/Part1/Part1.md");
                break;
            case R.id.bt_data_struct_2:
                intent.putExtra(Const.URL,
                        "https://github.com/CodeXiaoMai/DataStructure/blob/master/Part2/Part2.md");
                break;
        }
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

}
