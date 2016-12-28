
package com.xiaomai.FactoryMethodPattern;

import com.xiaomai.SimpleFactoryPattern.Operation;
import com.xiaomai.SimpleFactoryPattern.OperationSub;

/**
 * Created by XiaoMai on 2016/12/28 15:37.
 */
public class SubFactory implements IFactory {
    @Override
    public Operation createOperation() {
        return new OperationSub();
    }
}
