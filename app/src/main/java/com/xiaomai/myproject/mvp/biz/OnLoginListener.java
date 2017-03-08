package com.xiaomai.myproject.mvp.biz;

import com.xiaomai.myproject.mvp.bean.User;

/**
 * Created by HSEDU on 2017/3/8 11:18.
 */

public interface OnLoginListener {

    void loginSuccess(User user);

    void loginFail();
}
