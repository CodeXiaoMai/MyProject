
package com.xiaomai.myproject.retrofit.bean;

/**
 * Created by XiaoMai on 2017/2/10 16:34.
 */
public class ResponseInfo {

    /**
     * describe : 请求成功
     */

    private String describe;

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    @Override
    public String toString() {
        return "ResponseInfo{" +
                "describe='" + describe + '\'' +
                '}';
    }
}
