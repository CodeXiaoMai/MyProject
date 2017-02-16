
package com.xiaomai.myproject.rxjava;

import android.widget.TextView;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.base.BaseActivity;
import com.xiaomai.myproject.view.MyToast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;

public class RxJavaSimpleActivity extends BaseActivity {

    private TextView tv_rx_java_simple;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_rx_java_simple;
    }

    @Override
    protected int getCodeResId() {
        return R.string.code_rx_java_simple;
    }

    @Override
    protected void initViews() {
        super.initViews();
        setTitle("RxJava的简单使用");
        tv_rx_java_simple = (TextView) findViewById(R.id.tv_rx_java_simple);

        /**
         * 1) 创建 Observer</br>
         * Observer 即观察者，它决定事件触发的时候将有怎样的行为。
         */
        final Observer<String> observer = new Observer<String>() {

            @Override
            public void onCompleted() {
                MyToast.show(mContext, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                tv_rx_java_simple.setText("onError:" + e.getMessage());
            }

            @Override
            public void onNext(String s) {
                tv_rx_java_simple.setText(s);
            }
        };

        /**
         * 除了 Observer 接口之外，RxJava 还内置了一个实现了 Observer 的抽象类：Subscriber。
         * Subscriber 对 Observer 接口进行了一些扩展，但他们的基本使用方式是完全一样的，
         * 不仅基本使用方式一样，实质上，在RxJava 的 subscribe 过程中，Observer 也总是会先被转换成一个
         * Subscriber再使用。 所以如果你只想使用基本功能，选择 Observer 和 Subscriber
         * 是完全一样的。它们的区别对于使用者来说主要有两点： onStart(): 这是 Subscriber 增加的方法。它会在
         * subscribe刚开始，而事件还未发送之前被调用，可以用于做一些准备工作，例如数据的清零或重置。这是一个可选方法，
         * 默认情况下它的实现为空。需要注意的是， 如果对准备工作的线程有要求（例如弹出一个显示进度的对话框，这必须在主线程执行），onStart()
         * 就不适用了， 因为它总是在subscribe 所发生的线程被调用，而不能指定线程。要在指定的线程来做准备工作，可以使用
         * doOnSubscribe() 方法。 unsubscribe(): 这是 Subscriber 所实现的另一个接口
         * Subscription的方法，用于取消订阅。 在这个方法被调用后，Subscriber
         * 将不再接收事件。一般在这个方法调用前，可以使用isUnsubscribed() 先判断一下状态。 unsubscribe()
         * 这个方法很重要，因为在 subscribe() 之后，Observable 会持有 Subscriber
         * 的引用，这个引用如果不能及时被释放，将有内存泄露的风险。所以最好保持一个原则： 要在不再使用的时候尽快在合适的地方（例如
         * onPause() onStop() 等方法中）调用 unsubscribe() 来解除引用关系，以避免内存泄露的发生。
         */
        Subscriber<String> subscriber = new Subscriber<String>() {

            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {

            }
        };

        /**
         * 2) 创建 Observable</br>
         * Observable 即被观察者，它决定什么时候触发事件以及触发怎样的事件。 RxJava 使用 create() 方法来创建一个
         * Observable ，并为它定义事件触发规则： 可以看到，这里传入了一个 OnSubscribe 对象作为参数。OnSubscribe
         * 会被存储在返回的 Observable 对象中，它的作用相当于一个计划表，当 Observable 被订阅的时候，OnSubscribe
         * 的 call() 方法会自动被调用，事件序列就会依照设定依次触发（对于上面的代码，就是观察者Subscriber 将会被调用三次
         * onNext() 和一次
         * onCompleted()）。这样，由被观察者调用了观察者的回调方法，就实现了由被观察者向观察者的事件传递，即观察者模式。
         */
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onStart();
                float round = new Random().nextFloat();
                if (round > 0.5) {
                    subscriber.onNext(String.valueOf(round));
                    subscriber.onCompleted();
                } else {
                    subscriber.onError(new Throwable(String.valueOf(round)));
                }
            }
        });

        /**
         * create() 方法是 RxJava 最基本的创造事件序列的方法。基于这个方法， RxJava 还提供了一些方法用来快捷创建事件队列，
         */
        /**
         * 例如：just(T...): 将传入的参数依次发送出来。将会依次调用： onNext("Hello"); onNext("Hi");
         * onNext("Aloha"); onCompleted();
         */
        final Observable<String> stringObservable = Observable.just("Hello", "Hi", "Aloha0");
        /**
         * from(T[]) 或者 from(Iterable<? extends T>) : 将传入的数组或 Iterable
         * 拆分成具体对象后，依次发送出来。
         */
        String[] words = {
                "Hello", "Hi", "Aloha1"
        };
        final Observable<String> arrayObservable = Observable.from(words);
        List<String> list = new ArrayList<>();
        list.add("Hello");
        list.add("Hi");
        list.add("Aloha2");
        final Observable<String> listObservable = Observable.from(list);

        /**
         * 3) Subscribe (订阅) </br>
         * 创建了 Observable 和 Observer 之后，再用 subscribe() 方法将它们联结起来，整条链子就可以工作了。
         */
        observable.subscribe(observer);// observable.subscribe(subscriber);
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(1000);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            stringObservable.subscribe(observer);
                        }
                    });
                    Thread.sleep(1000);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            arrayObservable.subscribe(observer);
                        }
                    });
                    Thread.sleep(1000);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listObservable.subscribe(observer);

                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

}
