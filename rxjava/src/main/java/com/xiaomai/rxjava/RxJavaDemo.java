
package com.xiaomai.rxjava;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Created by XiaoMai on 2016/12/23 13:04.
 */
public class RxJavaDemo extends Activity {

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

    Observable observable = Observable.create(new Observable.OnSubscribe<String>() {

        @Override
        public void call(Subscriber<? super String> subscriber) {
            /*subscriber.onNext("Hello");
            subscriber.onNext("Hi");
            subscriber.onNext("Aloha");
            subscriber.onCompleted();*/
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        observable.subscribe(observer);
        observable.subscribe(onNextAction);
//        observable.subscribe(onNextAction, onErrorAction, onCompletedAction);
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
