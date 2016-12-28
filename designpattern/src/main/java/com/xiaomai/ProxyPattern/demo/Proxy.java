
package com.xiaomai.ProxyPattern.demo;

/**
 * Created by XiaoMai on 2016/12/28 14:48.
 */
public class Proxy implements GiveGift {

    private Pursuit pursuit;

    public Proxy(String mmName) {
        pursuit = new Pursuit(mmName);
    }

    @Override
    public void giveDolls() {
        System.out.print("我是代理：");
        pursuit.giveDolls();
    }

    @Override
    public void giveFlowers() {
        System.out.print("我是代理：");
        pursuit.giveFlowers();
    }

    @Override
    public void giveChocolate() {
        System.out.print("我是代理：");
        pursuit.giveChocolate();
    }
}
