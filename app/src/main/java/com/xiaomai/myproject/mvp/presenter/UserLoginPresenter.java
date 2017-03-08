
package com.xiaomai.myproject.mvp.presenter;

import android.os.Handler;

import com.xiaomai.myproject.mvp.bean.User;
import com.xiaomai.myproject.mvp.biz.IUserBiz;
import com.xiaomai.myproject.mvp.biz.OnLoginListener;
import com.xiaomai.myproject.mvp.biz.UserBiz;
import com.xiaomai.myproject.mvp.view.IUserLoginView;

/**
 * Created by HSEDU on 2017/3/8 11:19.
 */

public class UserLoginPresenter {

    private IUserBiz userBiz;

    private IUserLoginView userLoginView;

    private Handler mHandler = new Handler();

    public UserLoginPresenter(IUserLoginView userLoginView) {
        this.userLoginView = userLoginView;
        this.userBiz = new UserBiz();
    }

    public void login() {
        userLoginView.showLoading();
        userBiz.login(userLoginView.getUserName(), userLoginView.getPassword(), new OnLoginListener() {
            @Override
            public void loginSuccess(final User user) {
                // 需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        userLoginView.toMainActivity(user);
                        userLoginView.hideLoading();
                    }
                });
            }

            @Override
            public void loginFail() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        userLoginView.showFailedError();
                        userLoginView.hideLoading();
                    }
                });
            }
        });
    }

    public void clear() {
        userLoginView.clearUserName();
        userLoginView.clearPassword();
    }
}
