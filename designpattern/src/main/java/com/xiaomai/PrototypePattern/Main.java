
package com.xiaomai.PrototypePattern;

/**
 * Created by XiaoMai on 2016/12/29 13:54.
 */
public class Main {

    public static void main(String[] args) {
        ConcretePrototypeA concretePrototypeA = new ConcretePrototypeA("1");
        ConcretePrototypeA copy = (ConcretePrototypeA) concretePrototypeA.clone();
        System.out.print(copy.getId() == concretePrototypeA.getId());
    }
}
