
package com.xiaomai.myproject.json.activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.xiaomai.myproject.R;
import com.xiaomai.myproject.base.BaseActivity;
import com.xiaomai.myproject.json.activity.bean.ShopInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XiaoMai on 2017/2/6 20:37.
 */
public class FastJsonActivity extends BaseActivity implements View.OnClickListener{

    private Button bt_fastjson_tojava;

    private Button bt_fastjson_tojavalist;

    private Button bt_fastjson_javatojson;

    private Button bt_fastjson_javatojsonarray;

    private TextView tv_fastjson_origin;

    private TextView tv_fastjson_last;

    @Override
    protected void initViews() {
        super.initViews();
        setTitle("FastJson解析");
        bt_fastjson_tojava = (Button) findViewById(R.id.bt_fastjson_tojava);
        bt_fastjson_tojavalist = (Button) findViewById(R.id.bt_fastjson_tojavalist);
        bt_fastjson_javatojson = (Button) findViewById(R.id.bt_fastjson_javatojson);
        bt_fastjson_javatojsonarray = (Button) findViewById(R.id.bt_fastjson_javatojsonarray);
        tv_fastjson_origin = (TextView) findViewById(R.id.tv_fastjson_origin);
        tv_fastjson_last = (TextView) findViewById(R.id.tv_fastjson_last);

        bt_fastjson_tojava.setOnClickListener(this);
        bt_fastjson_tojavalist.setOnClickListener(this);
        bt_fastjson_javatojson.setOnClickListener(this);
        bt_fastjson_javatojsonarray.setOnClickListener(this);
    }

    @Override
    protected int getContentLayout() {
        return R.layout.activity_fastjson;
    }

    @Override
    protected int getCodeResId() {
        return R.string.code_fastjson;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_fastjson_tojava:
                jsonToJava();
                break;
            case R.id.bt_fastjson_tojavalist:
                jsonToJavaList();
                break;
            case R.id.bt_fastjson_javatojson:
                javaToJson();
                break;
            case R.id.bt_fastjson_javatojsonarray:
                javaToJsonArray();
                break;
        }
    }

    private void javaToJsonArray() {
        List<ShopInfo> shopInfos = new ArrayList<>();
        shopInfos.add(new ShopInfo(1, "暴雨", 32, "baoyu"));
        shopInfos.add(new ShopInfo(2, "海参", 32, "haishen"));

        String json = JSON.toJSONString(shopInfos);
        tv_fastjson_origin.setText(shopInfos.toString());
        tv_fastjson_last.setText(json);
    }

    private void javaToJson() {
        ShopInfo shopInfo = new ShopInfo(1, "暴雨", 23, "baoyu");
        String json = JSON.toJSONString(shopInfo);

        tv_fastjson_origin.setText(shopInfo.toString());
        tv_fastjson_last.setText(json);
    }

    private void jsonToJavaList() {
        String json = "[\n" + "{\n" + "\"id\":1, \"name\":\"大虾1\",\n" + "\"price\":12.3,\n"
                + "\"imagePath\":\"http://192.168.10.165:8080/f1.jpg\"\n" + "},\n" + "{\n"
                + "\"id\":2, \"name\":\"大虾2\",\n" + "\"price\":12.5,\n"
                + "\"imagePath\":\"http://192.168.10.165:8080/f2.jpg\"\n" + "}\n" + "]";
        List<ShopInfo> shopInfos = JSON.parseArray(json, ShopInfo.class);
        tv_fastjson_origin.setText(json);
        tv_fastjson_last.setText(shopInfos.toString());
    }

    private void jsonToJava() {
        String json = "{\n" + "\"id\":2, \"name\":\"大虾\",\n" + "\"price\":12.3,\n"
                + "\"imagePath\":\"http://192.168.10.165:8080/L05_Server/images/f1.jpg\"\n" + "}";
        ShopInfo shopInfo = JSON.parseObject(json, ShopInfo.class);

        tv_fastjson_origin.setText(json);
        tv_fastjson_last.setText(shopInfo.toString());
    }

}
