
package com.xiaomai.SimpleFactoryPattern;

/**
 * Created by XiaoMai on 2016/12/21 15:49.<br>
 * 这是除法运算类
 */
public class OperationDiv extends Operation {
    @Override
    public double getResult() {
        if (getNumberB() == 0) {
            try {
                throw new Exception("除数不能为0");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return getNumberA() / getNumberB();
    }
}
