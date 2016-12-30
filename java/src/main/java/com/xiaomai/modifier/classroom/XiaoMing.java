
package com.xiaomai.modifier.classroom;

import com.xiaomai.modifier.policestation.LiGang;

/**
 * Created by XiaoMai on 2016/12/30 17:09. </br>
 * 小明是学生，当然要在教室里（classroom这个包中）
 */
public class XiaoMing extends LiGang {

    /**
     * 这是受保护的方法
     */
    protected void banZheng() {
        System.out.println("我爸是李刚，我让我爸办证");
        super.banZheng();
    }

    /**
     * 不是我的同学，办证就要拿Money
     * 
     * @param money
     */
    public void banZheng(int money) {
    }
}
