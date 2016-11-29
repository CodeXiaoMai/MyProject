
package com.xiaomai.myproject.demo;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import com.xiaomai.myproject.R;

/**
 * Created by XiaoMai on 2016/11/29 11:44.
 */
public class SensorDemoActivity extends Activity {

    private SensorManager sensorManager;

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_sensor_manage);
        mTextView = (TextView) findViewById(R.id.textview);
        // SensorManager是系统所有传感器的管理器
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        // getDefaultSensor()方法来得到任意的传感器类型,
        // 这里使用 Sensor.TYPE_LIGHT 常量来指定传感器类型，
        // 此时的 Sensor 实例就代表着一个光照传感器。
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        /**
         * 第三个参数是用于表示传感器输出信息的更新速率，共有 SENSOR_DELAY_UI、SENSOR_DELAY_NORMAL、
         * SENSOR_DELAY_GAME 和 SENSOR_DELAY_FASTEST 这四种值可选， 它们的更新速率是依次递增的。
         */
        sensorManager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    /**
     * 要对传感器输出的信号进行监听，这就要借助 SensorEventListener 来实现 了。SensorEventListener
     * 是一个接口，其中定义了 onSensorChanged()和 onAccuracyChanged() 这两个方法
     */
    SensorEventListener listener = new SensorEventListener() {
        /**
         * 当传感器监测到的数值发生变化时就会调用 onSensorChanged()方法
         * 
         * @param event
         */
        @Override
        public void onSensorChanged(SensorEvent event) {
            // values数组中第一个下标的值是当前的光照强度
            // values 数组中只会有一个值，就是手机当前检测到的光照强度，以勒克斯为单位，
            float value = event.values[0];
            mTextView.setText("当前光照强度为:" + value + " lx");
        }

        /**
         * 当传感器的精度发生变化时就会调用 onAccuracyChanged()方法
         * 
         * @param sensor
         * @param accuracy
         */
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /**
         * 另外始终要记得，当程序退出或传感器使用完毕时，一定要调用 unregisterListener ()方 法将使用的资源释放掉
         */
        if (sensorManager != null)
            sensorManager.unregisterListener(listener);
    }
}
