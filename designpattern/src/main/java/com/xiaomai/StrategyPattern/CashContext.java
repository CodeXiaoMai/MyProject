
package com.xiaomai.StrategyPattern;

/**
 * Created by XiaoMai on 2016/12/27 15:22.</br>
 * 策略模式默认的Context方法
 */
public class CashContext {

    private BaseCash cash;

    public CashContext(BaseCash cash) {
        this.cash = cash;
    }

    public double getResult(double money) {
        return cash.acceptCash(money);
    }
}
