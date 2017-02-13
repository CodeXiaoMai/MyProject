
package com.xiaomai.myproject.retrofit;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.base.BaseActivity;
import com.xiaomai.myproject.retrofit.bean.ResponseInfo;
import com.xiaomai.myproject.retrofit.bean.User;
import com.xiaomai.myproject.retrofit.service.DemoService;

import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by XiaoMai on 2017/2/10 16:48.
 */
public class RetrofitActivity extends BaseActivity implements View.OnClickListener {

    private Retrofit retrofit;

    private TextView tv_retrofit;

    private Button bt_retrofit_get1;

    private Button bt_retrofit_get2;

    private Button bt_retrofit_get3;

    private Button bt_retrofit_get4;

    private Button bt_retrofit_post1;

    private Button bt_retrofit_post2;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_retrofit;
    }

    @Override
    protected void initViews() {
        super.initViews();
        setTitle("Retrofit的使用");
        tv_retrofit = (TextView) findViewById(R.id.tv_retrofit);
        bt_retrofit_get1 = (Button) findViewById(R.id.bt_retrofit_get1);
        bt_retrofit_get2 = (Button) findViewById(R.id.bt_retrofit_get2);
        bt_retrofit_get3 = (Button) findViewById(R.id.bt_retrofit_get3);
        bt_retrofit_get4 = (Button) findViewById(R.id.bt_retrofit_get4);
        bt_retrofit_post1 = (Button) findViewById(R.id.bt_retrofit_post1);
        bt_retrofit_post2 = (Button) findViewById(R.id.bt_retrofit_post2);
        bt_retrofit_get1.setOnClickListener(this);
        bt_retrofit_get2.setOnClickListener(this);
        bt_retrofit_get3.setOnClickListener(this);
        bt_retrofit_get4.setOnClickListener(this);
        bt_retrofit_post1.setOnClickListener(this);
        bt_retrofit_post2.setOnClickListener(this);
    }

    @Override
    protected int getCodeResId() {
        return R.string.code_retrofit;
    }

    @Override
    protected void initVariables() {
        super.initVariables();
        retrofit = new Retrofit.Builder().baseUrl("http://192.168.95.1:8080/FirstServlet/")
                .client(new OkHttpClient.Builder().addInterceptor(new LoggingInterceptor()).build())
                .addConverterFactory(GsonConverterFactory.create()).build();
    }

    private void testRetrofitHttpGet() {

        DemoService service = retrofit.create(DemoService.class);
        Call<ResponseInfo> call = service.testHttpGet();
        // call.execute(); 同步执行
        // call.enqueue(); 异步执行
        call.enqueue(new Callback<ResponseInfo>() {
            @Override
            public void onResponse(Call<ResponseInfo> call, Response<ResponseInfo> response) {
                tv_retrofit.setText("testRetrofitHttpGet\n" + response.body().toString());
            }

            @Override
            public void onFailure(Call<ResponseInfo> call, Throwable t) {
                tv_retrofit.setText(t.getMessage());
            }
        });
    }

    private void testRetrofitHttpGetByReplacement() {
        DemoService demoService = retrofit.create(DemoService.class);
        Call<ResponseInfo> call = demoService.testHttpGetQuery("test");
        call.enqueue(new Callback<ResponseInfo>() {
            @Override
            public void onResponse(Call<ResponseInfo> call, Response<ResponseInfo> response) {
                tv_retrofit
                        .setText("testRetrofitHttpGetByReplacement\n" + response.body().toString());
            }

            @Override
            public void onFailure(Call<ResponseInfo> call, Throwable t) {
                tv_retrofit.setText(t.getMessage());
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_retrofit_get1:
                testRetrofitHttpGet();
                break;
            case R.id.bt_retrofit_get2:
                testRetrofitHttpGetByReplacement();
                break;
            case R.id.bt_retrofit_get3:
                testRetrofitHttpGetWithQueryParams();
                break;
            case R.id.bt_retrofit_get4:
                testRetrofitHttpGetWithQueryMap();
                break;
            case R.id.bt_retrofit_post1:
                testRetrofitHttpPost();
                break;
            case R.id.bt_retrofit_post2:
                testRetrofitHttpPostWithHeaders();
                break;
        }
    }

    private void testRetrofitHttpPostWithHeaders() {
        DemoService service = retrofit.create(DemoService.class);
        Call<ResponseInfo> call = service.uploadUserWithHeaders("小明", "男", 20);
        call.enqueue(new Callback<ResponseInfo>() {
            @Override
            public void onResponse(Call<ResponseInfo> call, Response<ResponseInfo> response) {
                if (response != null) {
                    tv_retrofit.setText(response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<ResponseInfo> call, Throwable t) {
                tv_retrofit.setText(t.getMessage());
            }
        });
    }

    private void testRetrofitHttpPost() {
        DemoService service = retrofit.create(DemoService.class);
        User user = new User("小明", "1", 1);
        Call<ResponseInfo> call = service.uploadUser(user.getName(), user.getGender(),
                user.getAge());
        call.enqueue(new Callback<ResponseInfo>() {
            @Override
            public void onResponse(Call<ResponseInfo> call, Response<ResponseInfo> response) {
                tv_retrofit.setText("testRetrofitHttpPost\n" + response.body().toString());
            }

            @Override
            public void onFailure(Call<ResponseInfo> call, Throwable t) {
                tv_retrofit.setText(t.getMessage());
            }
        });
    }

    private void testRetrofitHttpGetWithQueryMap() {
        DemoService service = retrofit.create(DemoService.class);
        Map<String, String> params = new HashMap<>();
        params.put("model", "Android4.3");
        params.put("version", "2.2.1");
        Call<ResponseInfo> call = service.testHttpGet(params);
        call.enqueue(new Callback<ResponseInfo>() {
            @Override
            public void onResponse(Call<ResponseInfo> call, Response<ResponseInfo> response) {
                tv_retrofit
                        .setText("testRetrofitHttpGetWithQueryMap\n" + response.body().toString());
            }

            @Override
            public void onFailure(Call<ResponseInfo> call, Throwable t) {
                tv_retrofit.setText(t.getMessage());
            }
        });
    }

    private void testRetrofitHttpGetWithQueryParams() {
        DemoService service = retrofit.create(DemoService.class);
        final Call<ResponseInfo> call = service.testHttpGet("Android_4.3");
        call.enqueue(new Callback<ResponseInfo>() {
            @Override
            public void onResponse(Call<ResponseInfo> call, Response<ResponseInfo> response) {
                tv_retrofit.setText(
                        "testRetrofitHttpGetWithQueryParams\n" + response.body().toString());
            }

            @Override
            public void onFailure(Call<ResponseInfo> call, Throwable t) {
                tv_retrofit.setText(t.getMessage());
            }
        });
    }
}
