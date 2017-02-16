
package com.xiaomai.myproject.rxjava;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.base.BaseActivity;
import com.xiaomai.myproject.view.MyToast;

import java.util.Random;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;

public class RxJavaComplexActivity extends BaseActivity implements View.OnClickListener {

    private TextView tv_rx_java_complex;

    private ImageView iv_rx_java_complex;

    private Button bt_rx_java_complex_print_array;

    private Button bt_rx_java_complex_get_image;

    private StringBuilder mContent;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_rx_java_complex;
    }

    @Override
    protected void initViews() {
        super.initViews();
        setTitle("RxJava的高级用法");
        tv_rx_java_complex = (TextView) findViewById(R.id.tv_rx_java_complex);
        iv_rx_java_complex = (ImageView) findViewById(R.id.iv_rx_java_complex);
        bt_rx_java_complex_print_array = (Button) findViewById(R.id.bt_rx_java_complex_print_array);
        bt_rx_java_complex_get_image = (Button) findViewById(R.id.bt_rx_java_complex_get_image);
        bt_rx_java_complex_get_image.setOnClickListener(this);
        bt_rx_java_complex_print_array.setOnClickListener(this);
    }

    @Override
    protected int getCodeResId() {
        return R.string.code_rx_java_complex;
    }

    @Override
    protected void initVariables() {
        super.initVariables();
        mContent = new StringBuilder();
    }

    @Override
    protected void loadData() {
        super.loadData();
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onStart();
                float round = new Random().nextFloat();
                if (round > 0.5) {
                    subscriber.onNext(String.valueOf(round));
                    subscriber.onCompleted();
                } else {
                    try {
                        subscriber.onError(new Throwable(String.valueOf(round)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        /**
         * 除了 subscribe(Observer) 和 subscribe(Subscriber) ，subscribe()
         * 还支持不完整定义的回调，RxJava 会自动根据定义创建出 Subscriber 。形式如下：
         */
        Action1<String> onNextAction = new Action1<String>() {
            @Override
            public void call(String s) {
                mContent.append("\nonNextAction" + s);
                tv_rx_java_complex.setText(mContent);
            }
        };

        Action1<Throwable> onErrorAction = new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                mContent.append("\nonErrorAction" + throwable.getMessage());
                tv_rx_java_complex.setText(mContent);
            }
        };

        Action0 onCompletedAction = new Action0() {
            @Override
            public void call() {
                mContent.append("\nonCompletedAction");
                tv_rx_java_complex.setText(mContent);
            }
        };

        observable.subscribe(onNextAction);
        observable.subscribe(onNextAction, onErrorAction);
        observable.subscribe(onNextAction, onErrorAction, onCompletedAction);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_rx_java_complex_print_array:
                printArray();
                break;
            case R.id.bt_rx_java_complex_get_image:
                showImage();
                break;
        }
    }

    /**
     * 由指定的一个 drawable 文件 id drawableRes 取得图片，并显示在 ImageView 中，并在出现异常的时候打印 Toast
     * 报错：
     */
    private void showImage() {
        final int drawableRes = R.drawable.ic_launcher;
        Observable.create(new Observable.OnSubscribe<Drawable>() {
            @Override
            public void call(Subscriber<? super Drawable> subscriber) {
                Drawable drawable = getResources().getDrawable(drawableRes);
                subscriber.onNext(drawable);
                subscriber.onCompleted();
            }
        }).subscribe(new Observer<Drawable>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                MyToast.show(mContext, e.getMessage());
            }

            @Override
            public void onNext(Drawable drawable) {
                iv_rx_java_complex.setImageDrawable(drawable);
            }
        });
    }

    /**
     * 打印字符串数组
     */
    private void printArray() {
        String[] names = {
                "XiaoWang", "XiaoLi", "XiaoZhang"
        };
        Observable.from(names).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                mContent.append("\n" + s);
                tv_rx_java_complex.setText(mContent);
            }
        });
    }

}
