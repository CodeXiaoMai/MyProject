
package com.xiaomai.StrategyPattern;

/**
 * Created by XiaoMai on 2016/12/27 16:14.</br>
 * 这是策略模式和简单工厂模式结合的Context
 */
public class CashWithFactoryContext {

    /**
     * 普通方式
     */
    public static final String TYPE_NORMAL = "normal";

    /**
     * 打折
     */
    public static final String TYPE_REBATE = "rebate";

    /**
     * 返利
     */
    public static final String TYPE_RETURN = "return";

    private BaseCash cash;

    public CashWithFactoryContext(String type) {
        switch (type) {
            case TYPE_NORMAL:
                cash = new CashNormal();
                break;
            case TYPE_REBATE:
                cash = new CashRebate(0.8);
                break;
            case TYPE_RETURN:
                cash = new CashReturn(200, 10);
                break;
        }
    }

    public double getResult(double money) {
        return cash.acceptCash(money);
    }
}
