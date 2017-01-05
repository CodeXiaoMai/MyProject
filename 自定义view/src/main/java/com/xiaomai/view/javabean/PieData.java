
package com.xiaomai.view.javabean;

/**
 * Created by XiaoMai on 2017/1/5 16:30.</br>
 */
public class PieData {

    private String name;// 名字

    private float value;// 数值

    private float percentage;// 百分百

    private int color;// 颜色

    private float angle;// 角度

    public PieData(String name, float value, int color) {
        this.name = name;
        this.value = value;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }
}
