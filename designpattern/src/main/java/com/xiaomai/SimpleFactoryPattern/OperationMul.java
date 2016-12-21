
package com.xiaomai.SimpleFactoryPattern;

/**
 * Created by XiaoMai on 2016/12/21 15:48.<br>
 * 这是乘法运算类
 */
public class OperationMul extends Operation {
    @Override
    public double getResult() {
        return getNumberA() * getNumberB();
    }
}
