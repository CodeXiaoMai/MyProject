
package com.xiaomai.SimpleFactoryPattern;

/**
 * Created by XiaoMai on 2016/12/21 15:47.<br>
 * 这是减法运算类
 */
public class OperationSub extends Operation {
    @Override
    public double getResult() {
        return getNumberA() - getNumberB();
    }
}
