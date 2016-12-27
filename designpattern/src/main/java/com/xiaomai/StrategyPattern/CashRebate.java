
package com.xiaomai.StrategyPattern;

/**
 * Created by XiaoMai on 2016/12/27 15:15.<br>
 * 打折收费方式
 */
public class CashRebate extends BaseCash {

    private double rebate = 1;

    public CashRebate(double rebate) {
        this.rebate = rebate;
    }

    @Override
    public double acceptCash(double money) {
        return rebate * money;
    }
}
