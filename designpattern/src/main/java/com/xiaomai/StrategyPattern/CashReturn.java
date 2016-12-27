
package com.xiaomai.StrategyPattern;

/**
 * Created by XiaoMai on 2016/12/27 15:17.</br>
 * 满额返利模式
 */
public class CashReturn extends BaseCash {

    private double condition = 0;

    private double moneyReturn = 0;

    public CashReturn(double condition, double moneyReturn) {
        this.condition = condition;
        this.moneyReturn = moneyReturn;
    }

    @Override
    public double acceptCash(double money) {
        if (money >= condition)
            return money - money / condition * moneyReturn;
        return money;
    }
}
