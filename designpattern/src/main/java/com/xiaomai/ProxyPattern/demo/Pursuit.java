
package com.xiaomai.ProxyPattern.demo;

/**
 * Created by XiaoMai on 2016/12/28 14:46.</br>
 * 这是追求者类</br>
 */
public class Pursuit implements GiveGift {

    String mmName;

    public Pursuit(String mmName) {
        this.mmName = mmName;
    }

    @Override
    public void giveDolls() {
        System.out.println("送给" + mmName + "洋娃娃");
    }

    @Override
    public void giveFlowers() {
        System.out.println("送给" + mmName + "花");
    }

    @Override
    public void giveChocolate() {
        System.out.println("送给" + mmName + "巧克力");
    }
}
