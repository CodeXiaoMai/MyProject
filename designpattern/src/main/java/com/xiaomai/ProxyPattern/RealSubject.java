
package com.xiaomai.ProxyPattern;

/**
 * Created by XiaoMai on 2016/12/28 14:39.</br>
 * RealSubject类，定义Proxy所代表的真实实体</br>
 */
public class RealSubject implements Subject {
    @Override
    public void request() {
        System.out.println("真实的请求");
    }
}
