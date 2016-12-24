
package com.xiaomai.rxjava;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "RxJavaDemo";

    Observer<String> observer = new Observer<String>() {
        @Override
        public void onCompleted() {
            Log.d(TAG, "onCompleted: ");
        }

        @Override
        public void onError(Throwable e) {
            Log.d(TAG, "onError: ");
        }

        @Override
        public void onNext(String s) {
            Log.d(TAG, "onNext: " + s);
        }
    };

    Subscriber<String> subscriber = new Subscriber<String>() {
        @Override
        public void onCompleted() {
            Log.d(TAG, "onCompleted: ");
        }

        @Override
        public void onError(Throwable e) {
            Log.d(TAG, "onError: ");
        }

        @Override
        public void onNext(String s) {
            Log.d(TAG, "onNext: " + s);
        }
    };

    Observable observable = Observable.create(new OnSubscribe<String>() {

        @Override
        public void call(Subscriber<? super String> subscriber) {
            subscriber.onNext("Hello");
            subscriber.onNext("Hi");
            subscriber.onNext("Aloha");
            subscriber.onCompleted();
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // observable.subscribe(onNextAction, onErrorAction, onCompletedAction);

        String[] names = {
                "wang", "zhang", "li", "zhao"
        };
        Observable.from(names).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d(TAG, "call: " + s);
            }
        });

        final int drawableRes = R.mipmap.ic_launcher;
        final ImageView images = (ImageView) findViewById(R.id.iv1);
        Observable.create(new OnSubscribe<Drawable>() {
            @Override
            public void call(Subscriber<? super Drawable> subscriber) {
                try {
                    Thread.sleep(3000);
                    Drawable drawable = getResources().getDrawable(drawableRes);
                    subscriber.onNext(drawable);
                    subscriber.onCompleted();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Drawable>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Drawable drawable) {
                        images.setImageDrawable(drawable);
                    }
                });
        Observable.just("Hello").map(new Func1<String, String>() {
            @Override
            public String call(String s) {
                return s + "- Dan";
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d(TAG, "call: " + s);
            }
        });

        Observable.just("Hello").map(new Func1<String, String>() {
            @Override
            public String call(String s) {
                return s + "kd";
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {

            }
        });
    }

    Action1<String> onNextAction = new Action1<String>() {
        @Override
        public void call(String s) {
            Log.d(TAG, "call: " + s);
        }
    };

    Action1<Throwable> onErrorAction = new Action1<Throwable>() {
        @Override
        public void call(Throwable throwable) {

        }
    };

    Action0 onCompletedAction = new Action0() {
        @Override
        public void call() {
            Log.d(TAG, "call: completed");
        }
    };
}
