
package com.xiaomai.modifier.policestation;

/**
 * Created by XiaoMai on 2016/12/30 17:19. </br>
 * 这是李刚的同事，都在派出所内（都在policestation包中）
 */
public class WorkerMate {

    public static void main(String[] args) {
        LiGang police = new LiGang();
        // 同包下可以调用Protected方法修饰的方法
        System.out.println("我是李刚的同事，让他帮我办证");
        police.banZheng();
    }
}
