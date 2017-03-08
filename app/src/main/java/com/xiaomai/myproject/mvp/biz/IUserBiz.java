
package com.xiaomai.myproject.mvp.biz;

/**
 * Created by HSEDU on 2017/3/8 11:17.
 */

public interface IUserBiz {

    void login(String userName, String passWord, OnLoginListener loginListener);
}
