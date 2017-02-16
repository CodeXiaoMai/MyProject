package com.xiaomai.myproject.rxjava.bean;

import java.util.List;

/**
 * Created by XiaoMai on 2017/2/16 11:43.
 */
public class Student {

    private String name;

    private List<Course> courses;

    public Student(String name, List<Course> courses) {
        this.name = name;
        this.courses = courses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
