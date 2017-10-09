package com.xiaomai.myproject.test;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by XiaoMai on 2017/9/20.
 */
public class CalculatorTest {
    @Test
    public void testAdd() throws Exception {
        Calculator calculator = new Calculator();
        int sum = calculator.add(1, 2);
        Assert.assertEquals(3, sum);
    }

    @Test
    public void testGet() throws Exception {
        // 创建 mock 对象

    }
}