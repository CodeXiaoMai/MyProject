
package com.xiaomai.myproject.rxjava;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.base.BaseActivity;
import com.xiaomai.myproject.rxjava.bean.Course;
import com.xiaomai.myproject.rxjava.bean.Student;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class RxJavaFlatActivity extends BaseActivity implements View.OnClickListener {

    private Button bt_rx_java_flat_map;

    private Button bt_rx_java_flat_flatMap;

    private Button bt_rx_java_retrofit;

    private ImageView iv_rx_java_flat;

    private TextView tv_rx_java_flat;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_rx_java_flat;
    }

    @Override
    protected int getCodeResId() {
        return R.string.code_rx_java_flat;
    }

    @Override
    protected void initViews() {
        super.initViews();
        setTitle("RxJava变换的使用");
        bt_rx_java_flat_map = (Button) findViewById(R.id.bt_rx_java_flat_map);
        bt_rx_java_flat_flatMap = (Button) findViewById(R.id.bt_rx_java_flat_flatMap);
        bt_rx_java_retrofit = (Button) findViewById(R.id.bt_rx_java_lift);
        bt_rx_java_retrofit.setOnClickListener(this);
        bt_rx_java_flat_map.setOnClickListener(this);
        bt_rx_java_flat_flatMap.setOnClickListener(this);
        iv_rx_java_flat = (ImageView) findViewById(R.id.iv_rx_java_flat);
        tv_rx_java_flat = (TextView) findViewById(R.id.tv_rx_java_flat);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_rx_java_flat_map:
                map();
                break;
            case R.id.bt_rx_java_flat_flatMap:
                flatMap();
                break;
            case R.id.bt_rx_java_lift:
                lift();
                break;
        }
    }

    private void lift() {
        Observable.just(1,2,3,4,5).lift(new Observable.Operator<String, Integer>() {
            @Override
            public Subscriber<? super Integer> call(final Subscriber<? super String> subscriber) {
                return new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        subscriber.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        subscriber.onError(e);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        subscriber.onNext(String.valueOf(integer));
                    }
                };
            }
        })
        .subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                tv_rx_java_flat.setText(tv_rx_java_flat.getText() + "\n" + s);
            }
        });
    }

    /**
     * flatMap() 和 map() 有一个相同点：它也是把传入的参数转化之后返回另一个对象。但需要注意，和 map() 不同的是，
     * flatMap() 中返回的是个 Observable 对象，并且这个 Observable 对象并不是被直接发送到了 Subscriber
     * 的回调方法中。 flatMap() 的原理是这样的：1. 使用传入的事件对象创建一个 Observable 对象；2. 并不发送这个
     * Observable, 而是将它激活，于是它开始发送事件；3. 每一个创建出来的 Observable 发送的事件，都被汇入同一个
     * Observable ，而这个 Observable 负责将这些事件统一交给 Subscriber
     * 的回调方法。这三个步骤，把事件拆成了两级，通过一组新创建的 Observable
     * 将初始的对象『铺平』之后通过统一路径分发了下去。而这个『铺平』就是 flatMap() 所谓的 flat。
     */
    private void flatMap() {
        List<Course> list1 = new ArrayList<>();
        list1.add(new Course("数学"));
        list1.add(new Course("英语"));
        list1.add(new Course("体育"));

        List<Course> list2 = new ArrayList<>();
        list2.add(new Course("自然"));
        list2.add(new Course("频道"));
        Student[] students = {
                new Student("xiaowang", list1), new Student("xiaoli", list2)
        };
        Observable.from(students).flatMap(new Func1<Student, Observable<Course>>() {
            @Override
            public Observable<Course> call(Student student) {
                return Observable.from(student.getCourses());
            }
        }).subscribe(new Action1<Course>() {
            @Override
            public void call(Course course) {
                tv_rx_java_flat.setText(tv_rx_java_flat.getText() + "\n" + course.getName());
            }
        });
    }

    /**
     * 这里出现了一个叫做 Func1 的类。它和 Action1 非常相似，也是 RxJava 的一个接口，用于包装含有一个参数的方法。 Func1 和
     * Action 的区别在于， Func1 包装的是有返回值的方法。另外，和 ActionX 一样， FuncX
     * 也有多个，用于不同参数个数的方法。FuncX 和 ActionX 的区别在 FuncX 包装的是有返回值的方法。 可以看到，map()
     * 方法将参数中的 String 对象转换成一个 Bitmap 对象后返回，而在经过 map() 方法后，事件的参数类型也由 String 转为了
     * Bitmap。这种直接变换对象并返回的，是最常见的也最容易理解的变换。不过 RxJava
     * 的变换远不止这样，它不仅可以针对事件对象，还可以针对整个事件队列，这使得 RxJava 变得非常灵活。
     */
    private void map() {
        Observable.just("/meinv1.jpg").subscribeOn(Schedulers.io())
                .map(new Func1<String, Bitmap>() {
                    @Override
                    public Bitmap call(String s) {
                        String path = Environment.getExternalStorageDirectory() + s;
                        return BitmapFactory.decodeFile(path);
                    }
                }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Bitmap>() {
                    @Override
                    public void call(Bitmap bitmap) {
                        iv_rx_java_flat.setImageBitmap(bitmap);
                    }
                });
    }
}
