package com.xiaomai.myproject;

import android.test.AndroidTestCase;

/**
 * Created by XiaoMai on 2016/11/29 13:59.
 * 那么该如何编写测试用例呢？其实也很简单，只需要定义一个以 test 开头的方法，测试
 * 框架就会自动调用这个方法了。然后我们在方法中可以通过断言（assert）的形式来期望一
 * 个运行结果，再和实际的运行结果进行对比，这样一条测试用例就完成了。测试用例覆盖的
 * 功能越广泛，程序出现 bug 的概率就会越小。
 */
public class TestDemo extends AndroidTestCase {

    /**
     * setUp()方法会在所有的测试用例执行之前调用，可以在这里进行一些初始化操作
     * @throws Exception
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    /**
     * tearDown()方法会在所有的测试用例执行之后调用，可以在这里进行一些资源释放的操作。
     * @throws Exception
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
