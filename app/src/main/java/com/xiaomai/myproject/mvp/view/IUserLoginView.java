
package com.xiaomai.myproject.mvp.view;

import com.xiaomai.myproject.mvp.bean.User;

/**
 * Created by HSEDU on 2017/3/8 11:19.
 */

public interface IUserLoginView {

    String getUserName();

    String getPassword();

    void clearUserName();

    void clearPassword();

    void showLoading();

    void hideLoading();

    void toMainActivity(User user);

    void showFailedError();
}
