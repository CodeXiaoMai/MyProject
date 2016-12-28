
package com.xiaomai.DecoratorPattern;

/**
 * Created by XiaoMai on 2016/12/28 10:12.
 */
public class ConcreteComponent extends Component {
    @Override
    public void operation() {
        System.out.println("具体对象的操作");
    }
}
