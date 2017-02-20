
package com.xiaomai.myproject.handler;

import android.os.Handler;
import android.os.Message;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.base.BaseActivity;
import com.xiaomai.myproject.view.MyToast;

import java.lang.ref.WeakReference;

/**
 * Handler 的使用造成的内存泄漏问题应该说是最为常见了，很多时候我们为了避免 ANR
 * 而不在主线程进行耗时操作，在处理网络任务或者封装一些请求回调等api都借助Handler来处理，但 Handler 不是万能的，对于 Handler
 * 的使用代码编写一不规范即有可能造成内存泄漏。另外，我们知道 Handler、Message 和 MessageQueue 都是相互关联在一起的，万一
 * Handler 发送的 Message 尚未被处理，则该 Message 及发送它的 Handler 对象将被线程 MessageQueue 一直持有。
 * 由于 Handler 属于 TLS(Thread Local Storage) 变量, 生命周期和 Activity
 * 是不一致的。因此这种实现方式一般很难保证跟 View 或者 Activity 的生命周期保持一致，故很容易导致无法正确释放。
 */
public class HandlerActivity extends BaseActivity {

    private final MyHandler mHandler = new MyHandler(this);

    /**
     * 当匿名类是静态的时候，不会持有外部类的引用
     */
    private static final Runnable sRunnable = new Runnable() {
        @Override
        public void run() {

        }
    };

    @Override
    protected int getContentLayout() {
        return R.layout.activity_handler;
    }

    @Override
    protected int getCodeResId() {
        return R.string.code_handler;
    }

    @Override
    protected void initViews() {
        super.initViews();
        setTitle("Handler避免内存泄漏");
    }

    @Override
    protected void loadData() {
        super.loadData();
        mHandler.postDelayed(sRunnable, 1000 * 60 * 10);
        MyToast.show(this, "finish");
//        finish();
    }

    /**
     * 静态内部类的实例不会持有外部类的引用
     */
    private static class MyHandler extends Handler {

        /**
         * 如果只是想避免OutOfMemory异常的发生，则可以使用软引用。如果对于应用的性能更在意，想尽快回收一些占用内存比较大的对象，则可以使用弱引用。
         * 另外可以根据对象是否经常使用来判断选择软引用还是弱引用。如果该对象可能会经常使用的，就尽量用软引用。如果该对象不被使用的可能性更大些，就可以用弱引用。
         */
        private final WeakReference<HandlerActivity> mActivity;

        public MyHandler(HandlerActivity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            HandlerActivity activity = mActivity.get();
            if (activity != null) {
                super.handleMessage(msg);
            }
        }
    }

    /**
     * Looper 线程的消息队列中还是可能会有待处理的消息，所以我们在 Activity 的 Destroy 时或者 Stop 时应该移除消息队列
     * MessageQueue 中的消息。
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(this);
    }

}
