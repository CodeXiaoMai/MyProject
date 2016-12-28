
package com.xiaomai.FactoryMethodPattern;

import com.xiaomai.SimpleFactoryPattern.Operation;
import com.xiaomai.SimpleFactoryPattern.OperationMul;

/**
 * Created by XiaoMai on 2016/12/28 15:38.
 */
public class MulFactory implements IFactory {
    @Override
    public Operation createOperation() {
        return new OperationMul();
    }
}
