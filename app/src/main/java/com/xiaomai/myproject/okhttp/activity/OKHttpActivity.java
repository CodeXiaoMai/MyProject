
package com.xiaomai.myproject.okhttp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xiaomai.myproject.R;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by XiaoMai on 2017/2/5 16:37.
 */
public class OKHttpActivity extends Activity implements View.OnClickListener {

    private MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final int GET = 1;

    private static final int POST = 2;

    private Button bt_okhttp_get;

    private Button bt_okhttp_post;

    private TextView tv_okhttp_reslut;

    private OkHttpClient okHttpClient;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case GET:
                    tv_okhttp_reslut.setText(msg.obj.toString());
                    break;
                case POST:
                    tv_okhttp_reslut.setText(msg.obj.toString());
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
        bt_okhttp_get = (Button) findViewById(R.id.bt_okhttp_get);
        bt_okhttp_post = (Button) findViewById(R.id.bt_okhttp_post);
        tv_okhttp_reslut = (TextView) findViewById(R.id.tv_okhttp_reslut);
        bt_okhttp_get.setOnClickListener(this);
        bt_okhttp_post.setOnClickListener(this);
        okHttpClient = new OkHttpClient();
    }

    @Override
    public void onClick(View v) {
        if (v == bt_okhttp_get) {
            getDataByGet();
        } else if (v == bt_okhttp_post) {
            getDataByPost();
        }
        tv_okhttp_reslut.setText("");
    }

    private void getDataByGet() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    String result = get("http://www.baidu.com");
                    Message message = Message.obtain();
                    message.what = GET;
                    message.obj = result;
                    handler.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void getDataByPost() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    String result = post("http://app.hskaoyan.com", "");
                    Message message = Message.obtain();
                    message.what = POST;
                    message.obj = result;
                    handler.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * Get请求
     *
     * @param url
     * @return
     * @throws IOException
     */
    private String get(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        Response response = okHttpClient.newCall(request).execute();
        return response.body().string();
    }

    /**
     * Post请求
     *
     * @param url
     * @param json
     * @return
     * @throws IOException
     */
    private String post(String url, String json) throws IOException {
        RequestBody requestBody = RequestBody.create(JSON, json);
        Request request = new Request.Builder().url(url).post(requestBody).build();
        Response response = okHttpClient.newCall(request).execute();
        return response.body().string();
    }
}
