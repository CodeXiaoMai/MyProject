
package com.xiaomai.StrategyPattern;

/**
 * Created by XiaoMai on 2016/12/27 15:07.</br>
 * 策略模式(Strategy [ˈstrætədʒi] Pattern)：</br>
 * 是一种对象行为型模式。定义一系列算法，将每一个算法封装起来，并让它们可以相互替换。</br>
 * 策略模式让算法独立于使用它的客户而变化，也称为政策模式(Policy)。
 */
public class Main {

    public static void main(String[] args) {
        // 使用默认的策略模式
        CashContext cashContext = new CashContext(new CashNormal());
        System.out.print(cashContext.getResult(200));
        // 使用策略模式和简单工厂模式结合
        CashWithFactoryContext cashContextWithFactory = new CashWithFactoryContext(
                CashWithFactoryContext.TYPE_NORMAL);
        System.out.print(cashContextWithFactory.getResult(200));
    }
}
