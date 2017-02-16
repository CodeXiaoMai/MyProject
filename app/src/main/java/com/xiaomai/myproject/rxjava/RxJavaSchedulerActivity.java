
package com.xiaomai.myproject.rxjava;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.base.BaseActivity;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * 在不指定线程的情况下， RxJava 遵循的是线程不变的原则，即：在哪个线程调用subscribe()，就在哪个线程生产事件；
 * 在哪个线程生产事件，就在哪个线程消费事件。如果需要切换线程，就需要用到 Scheduler （调度器）。 </br>
 * </br>
 * 在RxJava 中，Scheduler ————调度器，相当于线程控制器，RxJava 通过它来指定每一段代码应该运行在什么样的线程。
 * RxJava已经内置了几个 Scheduler ，它们已经适合大多数的使用场景： </br>
 * Schedulers.immediate():直接在当前线程运行，相当于不指定线程。这是默认的 Scheduler。 </br>
 * Schedulers.newThread():总是启用新线程，并在新线程执行操作。</br>
 * Schedulers.io(): I/O 操作（读写文件、读写数据库、网络信息交互等）所使用的Scheduler。行为模式和 newThread()
 * 差不多，区别在于 io() 的内部实现是是用一个无数量上限的线程池，可以重用空闲的线程，因此多数情况下 io() 比 newThread()
 * 更有效率。不要把计算工作放在 io() 中，可以避免创建不必要的线程。 </br>
 * Schedulers.computation(): 计算所使用的Scheduler。这个计算指的是 CPU 密集型计算，即不会被
 * I/O等操作限制性能的操作，例如图形的计算。这个 Scheduler 使用的固定的线程池，大小为 CPU 核数。不要把 I/O 操作放在
 * computation() 中，否则 I/O 操作的等待时间会浪费 CPU。 </br>
 * 另外， Android 还有一个专用的 AndroidSchedulers.mainThread()，它指定的操作将在 Android 主线程运行。
 * 有了这几个 Scheduler ，就可以使用 subscribeOn() 和 observeOn() 两个方法来对线程进行控制了。
 * subscribeOn(): 指定subscribe()所发生的线程，即Observable.OnSubscribe被激活时所处的线程,
 * 或者叫做事件产生的线程。 </br>
 * observeOn(): 指定 Subscriber 所运行在的线程。或者叫做事件消费的线程。
 */
public class RxJavaSchedulerActivity extends BaseActivity implements View.OnClickListener{

    private TextView tv_rx_java_scheduler;

    private ImageView iv_rx_java_scheduler;

    private Button bt_rx_java_scheduler_text;

    private Button bt_rx_java_scheduler_image;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_rx_java_scheduler;
    }

    @Override
    protected void initViews() {
        super.initViews();
        setTitle("RxJava的Scheduler的用法");
        tv_rx_java_scheduler = (TextView) findViewById(R.id.tv_rx_java_scheduler);
        iv_rx_java_scheduler = (ImageView) findViewById(R.id.iv_rx_java_scheduler);
        bt_rx_java_scheduler_text = (Button) findViewById(R.id.bt_rx_java_scheduler_text);
        bt_rx_java_scheduler_image = (Button) findViewById(R.id.bt_rx_java_scheduler_image);
        bt_rx_java_scheduler_text.setOnClickListener(this);
        bt_rx_java_scheduler_image.setOnClickListener(this);
    }

    @Override
    protected int getCodeResId() {
        return R.string.code_rx_java_scheduler;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_rx_java_scheduler_text:
                showText();
                break;
            case R.id.bt_rx_java_scheduler_image:
                showImage();
                break;
        }
    }

    private void showImage() {
        final int drawableRes = R.drawable.ic_launcher;
        Observable.create(new Observable.OnSubscribe<Drawable>() {
            @Override
            public void call(Subscriber<? super Drawable> subscriber) {
                Drawable drawable = getResources().getDrawable(drawableRes);
                subscriber.onNext(drawable);
                subscriber.onCompleted();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Drawable>() {
                    @Override
                    public void call(Drawable drawable) {
                        iv_rx_java_scheduler.setImageDrawable(drawable);
                    }
                });
    }

    private void showText() {
        Observable.just(1, 2, 3,4)
                .subscribeOn(Schedulers.io())   // 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread())  // 指定 Subscriber 的回调发生在主线程
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        tv_rx_java_scheduler.setText(tv_rx_java_scheduler.getText() + String.valueOf(integer));
                    }
                });
    }
}
