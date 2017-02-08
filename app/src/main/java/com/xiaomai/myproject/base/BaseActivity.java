
package com.xiaomai.myproject.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.activity.CodeActivity;
import com.xiaomai.myproject.utils.Const;
import com.xiaomai.myproject.utils.MyLog;
import com.xiaomai.myproject.view.MyProgressDialog;
import com.xiaomai.myproject.view.MyToast;

import butterknife.ButterKnife;

/**
 * Created by XiaoMai on 2016/8/29.
 */
public abstract class BaseActivity extends AppCompatActivity {

    /**
     * 返回按钮
     */
    private ImageView iv_back;

    /**
     * 更多按钮
     */
    private ImageView iv_more;

    /**
     * 标题
     */
    private TextView tv_title;

    /**
     * 更多按钮的点击事件
     */
    private View.OnClickListener onMoreClickListener;

    /**
     * 进度条Dialog
     */
    private MyProgressDialog mProgressDialog;

    protected Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int contentLayoutId = getContentLayout();
        if (contentLayoutId <= 0) {
            MyLog.e(Const.TAG_ERROR, "还没有setContentLayout");
            MyToast.show(this, "还没有setContentLayout");
            return;
        }
        setContentView(contentLayoutId);
        ButterKnife.bind(this);
        initVariables();
        initViews();
        loadData();
    }

    /**
     * 初始化数据
     */
    protected void initVariables() {
        mContext = this;
    }

    /**
     * 初始化布局
     */
    protected void initViews() {
        setOnMoreClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CodeActivity.class);
                int resId = getCodeResId();
                if (resId != 0) {
                    intent.putExtra(Const.CODE_CONTENT, getString(resId));
                    startActivity(intent);
                } else {
                    MyToast.show(mContext, "没有设置resId");
                }
            }
        });
        /**
         * 返回按钮
         */
        iv_back = (ImageView) findViewById(R.id.back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackClick();
            }
        });
        /**
         * more按钮
         */
        iv_more = (ImageView) findViewById(R.id.more);
        iv_more.setOnClickListener(onMoreClickListener);
        /**
         * 标题
         */
        tv_title = (TextView) findViewById(R.id.tv_title);
    }

    /**
     * 加载数据
     */
    protected void loadData() {
        // showProgressDialog();
    }

    /**
     * 设置布局文件
     *
     * @return
     */
    protected abstract int getContentLayout();

    protected boolean isWithoutToolbar() {
        return false;
    }

    /**
     * 返回按钮的点击事件
     */
    protected void onBackClick() {
        finish();
    }

    /**
     * 更多按钮的点击事件
     */
    protected void setOnMoreClickListener(View.OnClickListener onMoreClickListener) {
        this.onMoreClickListener = onMoreClickListener;
        if (onMoreClickListener == null) {
            iv_more.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * 设置标题
     *
     * @param str
     */
    public void setTitle(String str) {
        tv_title.setText(str);
    }

    /**
     * 设置标题
     *
     * @param resId
     */
    public void setTitle(int resId) {
        tv_title.setText(getResources().getString(resId));
    }

    /**
     * 显示进度Dialog，可以取消
     */
    protected void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new MyProgressDialog.Builder(this).create();
        }
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();
    }

    /**
     * 显示进度Dialog
     * 
     * @param cancelable 默认值false，不可取消，true可以取消
     */
    protected void showProgressDialog(boolean cancelable) {
        if (mProgressDialog == null) {
            mProgressDialog = new MyProgressDialog.Builder(this).setCancelable(cancelable).create();
        }
        mProgressDialog.setCancelable(cancelable);
        mProgressDialog.show();
    }

    /**
     * 隐藏进度Dialog
     */
    protected void disMissProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    protected int getCodeResId() {
        return 0;
    }
}
