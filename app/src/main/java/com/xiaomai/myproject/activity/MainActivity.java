
package com.xiaomai.myproject.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.base.BaseFragment;
import com.xiaomai.myproject.fragment.CommonFrameFragment;
import com.xiaomai.myproject.fragment.OtherFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by XiaoMai on 2017/2/5 10:28.
 */
public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener {

    private static final String TAG = "MainActivity";

    @BindView(R.id.fl_main)
    FrameLayout flMain;

    @BindView(R.id.rb_main_home)
    RadioButton rbMainHome;

    @BindView(R.id.rb_main_other)
    RadioButton rbMainOther;

    @BindView(R.id.rg_main)
    RadioGroup rgMain;

    private List<BaseFragment> mFragments;

    private int mPosition;

    /**
     * 上次切换的Fragment
     */
    private BaseFragment mContent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initFragment();
        setListener();
    }

    private void setListener() {
        rgMain.setOnCheckedChangeListener(this);
        // 要在RadioGroup设置监听事件后再调用下面的方法，否则第一次没有数据
        rgMain.check(R.id.rb_main_home);
    }

    private void initFragment() {
        mFragments = new ArrayList<>();
        mFragments.add(new CommonFrameFragment());
        mFragments.add(new OtherFragment());
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_main_home:
                mPosition = 0;
                break;
            case R.id.rb_main_other:
                mPosition = 1;
                break;
            default:
                mPosition = 0;
                break;
        }
        // 根据位置得到对应的Fragment
        BaseFragment to = mFragments.get(mPosition);
        switchFragment(mContent, to);
    }

    /**
     * @param from 当前正在显示的Fragment
     * @param to   将要显示的Fragment
     */
    private void switchFragment(BaseFragment from, BaseFragment to) {
        // 当from和to相同时就不切换了
        if (from != to && to != null) {
            mContent = to;
            // 切换
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            if (to.isAdded()) {
                if (from != null){
                    // 隐藏from
                    fragmentTransaction.hide(from);
                }
                fragmentTransaction.show(to).commit();
            } else {
                if (from != null){
                    fragmentTransaction.hide(from);
                }
                fragmentTransaction.add(R.id.fl_main, to).commit();
            }
        }
    }

}
