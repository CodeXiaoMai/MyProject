
package com.xiaomai.myproject.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.xiaomai.myproject.base.BaseFragment;

/**
 * Created by XiaoMai on 2017/2/5 11:44.
 */
public class OtherFragment extends BaseFragment {

    private static final String TAG = OtherFragment.class.getSimpleName();

    private TextView mTextView;

    @Override
    protected View initView() {
        Log.d(TAG, "initView: ");
        mTextView = new TextView(mContext);
        mTextView.setGravity(Gravity.CENTER);
        mTextView.setTextSize(20);
        mTextView.setTextColor(Color.BLACK);
        return mTextView;
    }

    @Override
    protected void loadData() {
        Log.d(TAG, "loadData: ");
        super.loadData();
        mTextView.setText("这是OtherFragment");
    }
}
