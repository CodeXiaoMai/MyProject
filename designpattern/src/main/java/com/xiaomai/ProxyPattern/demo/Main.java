
package com.xiaomai.ProxyPattern.demo;

/**
 * Created by XiaoMai on 2016/12/28 14:51.
 */
public class Main {

    public static void main(String[] args) {
        Proxy proxy = new Proxy("jiaojiao");
        proxy.giveDolls();
        proxy.giveFlowers();
        proxy.giveChocolate();
    }
}
