
package com.xiaomai.PrototypePattern;

/**
 * Created by XiaoMai on 2016/12/29 13:55.</br>
 * 原型类
 */
public class Prototype implements Cloneable {

    private String id;

    public Prototype(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    /**
     * 抽象类关键就是这一个clone方法
     *
     * @return
     */
    @Override
    public Object clone() {
        Prototype prototype = null;
        try {
            prototype = (Prototype) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return prototype;
    }
}
