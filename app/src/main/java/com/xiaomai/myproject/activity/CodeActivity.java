
package com.xiaomai.myproject.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.WindowManager;
import android.widget.TextView;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.utils.Const;

/**
 * Created by XiaoMai on 2017/2/6 23:01.
 */
public class CodeActivity extends Activity {

    private TextView tv_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setFlags(flag, flag);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);
        initViews();
    }

    protected void initViews() {
        tv_code = (TextView) findViewById(R.id.tv_code);
        Intent intent = getIntent();
        if (intent != null) {
            String codeContent = intent.getStringExtra(Const.CODE_CONTENT);
            if (!TextUtils.isEmpty(codeContent)) {
                tv_code.setText(codeContent);
            }
        }
    }

}
