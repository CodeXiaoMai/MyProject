
package com.xiaomai.myproject.mvp.biz;

import com.xiaomai.myproject.mvp.bean.User;

/**
 * Created by HSEDU on 2017/3/8 11:18.
 */

public class UserBiz implements IUserBiz {
    @Override
    public void login(final String userName, final String passWord,
            final OnLoginListener loginListener) {
        // 模拟耗时操作
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 模拟登陆成功
                if ("123".equals(userName) && "123".equals(passWord)) {
                    User user = new User();
                    user.setUserName(userName);
                    user.setPassWord(passWord);
                    loginListener.loginSuccess(user);
                } else {
                    loginListener.loginFail();
                }
            }
        }.start();
    }
}
