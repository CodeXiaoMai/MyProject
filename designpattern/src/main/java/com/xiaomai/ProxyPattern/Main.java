
package com.xiaomai.ProxyPattern;

/**
 * Created by XiaoMai on 2016/12/28 14:37.</br>
 * 代理模式(Proxy Pattern) ：给某一个对象提供一个代理，并由代理对象控制对原对象的引用。</br>
 * 代理模式的英文叫做Proxy或Surrogate，它是一种对象结构型模式。</br>
 * 代理模式包含如下角色： </br>
 * Subject: 抽象主题角色 </br>
 * Proxy: 代理主题角色 </br>
 * RealSubject: 真实主题角色</br>
 * 代理模式的优点</br>
 * 代理模式能够协调调用者和被调用者，在一定程度上降低了系统的耦合度。远程代理使得客户端可以访问在远程机器上的对象，远程机器
 * 可能具有更好的计算性能与处理速度，可以快速响应并处理客户端请求。 虚拟代理通过使用一个小对象来代表一个大对象，可以减少系
 * 统资源的消耗，对系统进行优化并提高运行速度。 保护代理可以控制对真实对象的使用权限。</br>
 * 代理模式的缺点</br>
 * 由于在客户端和真实主题之间增加了代理对象，因此有些类型的代理模式可能会造成请求的处理速度变慢。 </br>
 * 实现代理模式需要额外的工作，有些代理模式的实现非常复杂。
 */
public class Main {

    public static void main(String[] args) {
        Proxy proxy = new Proxy();
        proxy.request();
    }
}
