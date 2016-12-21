
package com.xiaomai.SimpleFactoryPattern;

/**
 * Created by XiaoMai on 2016/12/21 16:04.<br>
 * 简单工厂模式<br>
 * 简单工厂模式(Simple Factory Pattern)：又称为静态工厂方法(Static Factory Method)模式，
 * 它属于类创建型模式。在简单工厂模式中，可以根据参数的不同返回不同类的实例。
 * 简单工厂模式专门定义一个类来负责创建其他类的实例，被创建的实例通常都具有共同的父类。 <br>
 * <br>
 * 简单工厂模式包含如下角色： <br>
 * Factory：工厂角色,工厂角色负责实现创建所有实例的内部逻辑
 * Product：抽象产品角色,抽象产品角色是所创建的所有对象的父类，负责描述所有实例所共有的公共接口
 * ConcreteProduct：具体产品角色,具体产品角色是创建目标，所有创建的对象都充当这个角色的某个具体类的实例。 <br>
 * <br>
 * 在以下情况下可以使用简单工厂模式： <br>
 * 工厂类负责创建的对象比较少：由于创建的对象较少，不会造成工厂方法中的业务逻辑太过复杂。
 * 客户端只知道传入工厂类的参数，对于如何创建对象不关心：客户端既不需要关心创建细节，甚至连类名都不需要记住，只需要知道类型所对应的参数。
 */
public class Main {

    public static void main(String[] args) {
        Operation operation = OperationFactory.createOperate(OperationFactory.OPERATION_DIV);
        operation.setNumberA(1);
        operation.setNumberB(0);
        System.out.println(operation.getResult());
    }
}
