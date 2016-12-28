
package com.xiaomai.DecoratorPattern;

/**
 * Created by XiaoMai on 2016/12/28 10:11.</br>
 * 装饰模式(Decorator Pattern) ：</br>
 * 动态地给一个对象增加一些额外的职责(Responsibility)，就增加对象功能来说，装饰模式比生成子类实现更为灵活。
 * 其别名也可以称为包装器(Wrapper)，与适配器模式的别名相同，但它们适用于不同的场合。</br>
 * 根据翻译的不同，装饰模式也有人称之为“油漆工模式”，它是一种对象结构型模式。</br>
 * 装饰模式包含如下角色：</br>
 * Component: 抽象构件 </br>
 * ConcreteComponent: 具体构件 </br>
 * Decorator: 抽象装饰类 </br>
 * ConcreteDecorator: 具体装饰类</br>
 * 模式分析:</br>
 * 与继承关系相比，关联关系的主要优势在于不会破坏类的封装性，而且继承是一种耦合度较大的静态关系，</br>
 * 无法在程序运行时动态扩展。在软件开发阶段，关联关系虽然不会比继承关系减少编码量，但是到了软件维护阶段，</br>
 * 由于关联关系使系统具有较好的松耦合性，因此使得系统更加容易维护。</br>
 * 当然，关联关系的缺点是比继承关系要创建更多的对象。使用装饰模式来实现扩展比继承更加灵活，</br>
 * 它以对客户透明的方式动态地给一个对象附加更多的责任。装饰模式可以在不需要创造更多子类的情况下，将对象的功能加以扩展。</br>
 * 装饰模式的优点: </br>
 * 装饰模式与继承关系的目的都是要扩展对象的功能，但是装饰模式可以提供比继承更多的灵活性。</br>
 * 可以通过一种动态的方式来扩展一个对象的功能，通过配置文件可以在运行时选择不同的装饰器，从而实现不同的行为。</br>
 * 通过使用不同的具体装饰类以及这些装饰类的排列组合，可以创造出很多不同行为的组合。</br>
 * 可以使用多个具体装饰类来装饰同一对象，得到功能更为强大的对象。具体构件类与具体装饰类可以独立变化，</br>
 * 用户可以根据需要增加新的具体构件类和具体装饰类，在使用时再对其进行组合，原有代码无须改变，符合“开闭原则”</br>
 * 装饰模式的缺点:</br>
 * 使用装饰模式进行系统设计时将产生很多小对象，这些对象的区别在于它们之间相互连接的方式有所不同，</br>
 * 而不是它们的类或者属性值有所不同，同时还将产生很多具体装饰类。这些装饰类和小对象的产生将增加系统的复杂度，</br>
 * 加大学习与理解的难度。这种比继承更加灵活机动的特性，也同时意味着装饰模式比继承更加易于出错，排错也很困难，</br>
 * 对于多次装饰的对象，调试时寻找错误可能需要逐级排查，较为烦琐。
 */
public class Main {

    public static void main(String[] args) {
        ConcreteComponent concreteComponent = new ConcreteComponent();
        ConcreteDecoratorA concreteDecoratorA = new ConcreteDecoratorA();
        ConcreteDecoratorB concreteDecoratorB = new ConcreteDecoratorB();

        concreteDecoratorA.setComponent(concreteComponent);
        concreteDecoratorB.setComponent(concreteDecoratorA);
        concreteDecoratorB.operation();
    }
}
