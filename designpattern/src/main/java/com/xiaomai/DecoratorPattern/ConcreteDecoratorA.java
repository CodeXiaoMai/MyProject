package com.xiaomai.DecoratorPattern;

/**
 * Created by XiaoMai on 2016/12/28 10:15.
 */
public class ConcreteDecoratorA extends Decorator {

    private String state;

    @Override
    public void operation() {
        super.operation();
        state = "new state";
        System.out.println("具体装饰对象A的操作");
    }
}
