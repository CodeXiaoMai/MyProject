
package com.xiaomai.FactoryMethodPattern;

import com.xiaomai.SimpleFactoryPattern.Operation;
import com.xiaomai.SimpleFactoryPattern.OperationAdd;

/**
 * Created by XiaoMai on 2016/12/28 15:37.
 */
public class AddFactory implements IFactory {
    @Override
    public Operation createOperation() {
        return new OperationAdd();
    }
}
