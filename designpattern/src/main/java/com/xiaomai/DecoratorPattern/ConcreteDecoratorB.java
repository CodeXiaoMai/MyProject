
package com.xiaomai.DecoratorPattern;

/**
 * Created by XiaoMai on 2016/12/28 10:17.
 */
public class ConcreteDecoratorB extends Decorator {

    @Override
    public void operation() {
        super.operation();
        addBehavior();
        System.out.println("具体装饰对象B的操作");
    }

    private void addBehavior() {
        System.out.println("具体装饰对象B独有的方法");
    }
}
