
package com.xiaomai.ProxyPattern;

/**
 * Created by XiaoMai on 2016/12/28 14:37.</br>
 * Subject接口，定义了RealSubject和Proxy的公用接口，这样在任何使用RealSubject的地方就都可以使用Proxy。</br>
 */
public interface Subject {
    void request();
}
