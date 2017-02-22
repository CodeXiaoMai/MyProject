
package com.xiaomai.myproject.bignumber;

import android.widget.TextView;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.base.BaseActivity;

import java.math.BigInteger;
import java.text.DecimalFormat;

public class BigNumberActivity extends BaseActivity {

    private TextView tv_big_number;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_big_number;
    }

    @Override
    protected int getCodeResId() {
        return R.string.code_big_number;
    }

    @Override
    protected void initViews() {
        super.initViews();
        tv_big_number = (TextView) findViewById(R.id.tv_big_number);

        BigInteger bigInteger = new BigInteger("10000000000000000000000000000000");
        bigInteger = bigInteger.add(bigInteger);

        DecimalFormat format = new DecimalFormat("#.#E0");
        tv_big_number.setText(format.format(bigInteger));
    }
}
