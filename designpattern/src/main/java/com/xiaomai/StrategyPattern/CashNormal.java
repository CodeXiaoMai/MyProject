
package com.xiaomai.StrategyPattern;

/**
 * Created by XiaoMai on 2016/12/27 15:13.<br>
 * 普通收费方式
 */
public class CashNormal extends BaseCash {

    @Override
    public double acceptCash(double money) {
        return money;
    }
}
