
package com.xiaomai.FactoryMethodPattern;

import com.xiaomai.SimpleFactoryPattern.Operation;

/**
 * Created by XiaoMai on 2016/12/28 15:35.
 */
public class Main {

    public static void main(String[] args) {
        IFactory iFactory = new AddFactory();
        Operation operation = iFactory.createOperation();
        operation.setNumberA(1);
        operation.setNumberB(2);
        System.out.print(operation.getResult());
    }
}
