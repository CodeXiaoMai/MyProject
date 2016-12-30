
package com.xiaomai.modifier.policestation;

/**
 * Created by XiaoMai on 2016/12/30 17:08.</br>
 * 这个包代表派出所，小明的爸爸李刚在这里上班，</br>
 */
public class LiGang {

    /**
     * 这个方法是Protected修饰的，也就是只有同包或子类或自己这个类调用 </br>
     * 同包：就是指派出所内的人可以让小明的爸爸办证 </br>
     * 子类：小明可以让爸爸办证 </br>
     * 本类：自己让自己办证
     */
    protected void banZheng() {
        System.out.println("我是科长：我可以办证");
    }

}
