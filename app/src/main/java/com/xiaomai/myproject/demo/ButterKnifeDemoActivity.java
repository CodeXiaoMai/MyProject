
package com.xiaomai.myproject.demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.view.MyToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

/**
 * Created by XiaoMai on 2017/2/4 14:11.
 */
public class ButterKnifeDemoActivity extends Activity {

    @BindView(R.id.tv_butterknife)
    TextView tvButterknife;

    @BindView(R.id.bt_butterknife)
    Button btButterknife;

    @BindView(R.id.bt_butterknife_2)
    Button btButterknife2;

    @BindView(R.id.cb_butterknife)
    CheckBox cbButterknife;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butterknife);
        ButterKnife.bind(this);
        tvButterknife.setText("sdlfkj");
    }

    @OnClick(R.id.bt_butterknife)
    void onButtonClick() {
        MyToast.show(this, "click");
    }

    @OnClick(R.id.bt_butterknife_2)
    void onButton2Click() {
        MyToast.show(this, "click2");
    }

    @OnCheckedChanged(R.id.cb_butterknife)
    void onChecked(){
        MyToast.show(this, "onChecked");
    }
}
