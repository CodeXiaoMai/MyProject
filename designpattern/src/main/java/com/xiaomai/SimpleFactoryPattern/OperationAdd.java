
package com.xiaomai.SimpleFactoryPattern;

/**
 * Created by XiaoMai on 2016/12/21 15:44.</br>
 * 这是加法运算类
 */
public class OperationAdd extends Operation {
    @Override
    public double getResult() {
        return getNumberA() + getNumberB();
    }
}
