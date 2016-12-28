
package com.xiaomai.DecoratorPattern;

/**
 * Created by XiaoMai on 2016/12/28 10:13.
 */
public abstract class Decorator extends Component {

    protected Component component;

    public void setComponent(Component component) {
        this.component = component;
    }

    @Override
    public void operation() {
        if (component != null) {
            component.operation();
        }
    }
}
