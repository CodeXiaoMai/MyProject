package com.xiaomai.myproject.mvp.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.mvp.bean.User;
import com.xiaomai.myproject.mvp.presenter.UserLoginPresenter;
import com.xiaomai.myproject.view.MyToast;

/**
 * Created by HSEDU on 2017/3/8 11:19.
 */

public class UserLoginActivity extends AppCompatActivity implements IUserLoginView {

    private EditText et_user_login_userName, et_user_login_password;
    private Button bt_user_login_login, bt_user_login_logout;
    private ProgressBar pb_user_login;
    private UserLoginPresenter mUserLoginPresenter = new UserLoginPresenter(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        initViews();
    }

    private void initViews() {
        et_user_login_userName = (EditText) findViewById(R.id.et_user_login_userName);
        et_user_login_password = (EditText) findViewById(R.id.et_user_login_password);
        pb_user_login = (ProgressBar) findViewById(R.id.pb_user_login);
        bt_user_login_login = (Button) findViewById(R.id.bt_user_login_login);
        bt_user_login_logout = (Button) findViewById(R.id.bt_user_login_logout);

        bt_user_login_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserLoginPresenter.login();
            }
        });

        bt_user_login_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserLoginPresenter.clear();
            }
        });
    }

    @Override
    public String getUserName() {
        return et_user_login_userName.getText().toString();
    }

    @Override
    public String getPassword() {
        return et_user_login_password.getText().toString();
    }

    @Override
    public void clearUserName() {
        et_user_login_userName.setText("");
    }

    @Override
    public void clearPassword() {
        et_user_login_password.setText("");
    }

    @Override
    public void showLoading() {
        pb_user_login.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        pb_user_login.setVisibility(View.GONE);
    }

    @Override
    public void toMainActivity(User user) {
        MyToast.show(this, "登录成功");
    }

    @Override
    public void showFailedError() {
        MyToast.show(this, "登录失败");
    }
}
