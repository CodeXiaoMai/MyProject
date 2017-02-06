
package com.xiaomai.myproject.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.TextView;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.base.BaseActivity;
import com.xiaomai.myproject.utils.Const;

/**
 * Created by XiaoMai on 2017/2/6 23:01.
 */
public class CodeActivity extends BaseActivity {

    private TextView tv_code;

    @Override
    protected void initViews() {
        super.initViews();
        setOnMoreClickListener(null);
        setTitle("代码");
        tv_code = (TextView) findViewById(R.id.tv_code);
        Intent intent = getIntent();
        if (intent != null) {
            String codeContent = intent.getStringExtra(Const.CODE_CONTENT);
            if (!TextUtils.isEmpty(codeContent)) {
                tv_code.setText(codeContent);
            }
        }
    }

    @Override
    protected int getContentLayout() {
        return R.layout.activity_code;
    }
}
