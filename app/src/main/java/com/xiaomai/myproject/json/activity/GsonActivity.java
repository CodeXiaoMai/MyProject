
package com.xiaomai.myproject.json.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xiaomai.myproject.R;
import com.xiaomai.myproject.base.BaseActivity;
import com.xiaomai.myproject.json.activity.bean.ShopInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XiaoMai on 2017/2/6 15:44.
 */
public class GsonActivity extends BaseActivity implements View.OnClickListener {

    private Button bt_gson_tojava;

    private Button bt_gson_tojavalist;

    private Button bt_gson_javatojson;

    private Button bt_gson_javatojsonarray;

    private TextView tv_gson_origin;

    private TextView tv_gson_last;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initViews() {
        super.initViews();
        setTitle("GSON解析");
        bt_gson_tojava = (Button) findViewById(R.id.bt_gson_tojava);
        bt_gson_tojavalist = (Button) findViewById(R.id.bt_gson_tojavalist);
        bt_gson_javatojson = (Button) findViewById(R.id.bt_gson_javatojson);
        bt_gson_javatojsonarray = (Button) findViewById(R.id.bt_gson_javatojsonarray);
        tv_gson_origin = (TextView) findViewById(R.id.tv_gson_origin);
        tv_gson_last = (TextView) findViewById(R.id.tv_gson_last);

        bt_gson_tojava.setOnClickListener(this);
        bt_gson_tojavalist.setOnClickListener(this);
        bt_gson_javatojson.setOnClickListener(this);
        bt_gson_javatojsonarray.setOnClickListener(this);
    }

    @Override
    protected int getCodeResId() {
        return R.string.code_gson;
    }

    @Override
    protected int getContentLayout() {
        return R.layout.activity_gson;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_gson_tojava:
                jsonToJavaObject();
                break;
            case R.id.bt_gson_tojavalist:
                jsonToJavaList();
                break;
            case R.id.bt_gson_javatojson:
                javaToJson();
                break;
            case R.id.bt_gson_javatojsonarray:
                javaToJsonArray();
                break;
        }
    }

    /**
     * 将Java转换为JsonArray
     */
    private void javaToJsonArray() {
        List<ShopInfo> shops = new ArrayList<>();
        shops.add(new ShopInfo(1, "暴雨", 240, "baoyu"));
        shops.add(new ShopInfo(2, "海参", 232, "haishen"));

        Gson gson = new Gson();
        String json = gson.toJson(shops);
        tv_gson_origin.setText(shops.toString());
        tv_gson_last.setText(json);
    }

    /**
     * 将Java转换为Json
     */
    private void javaToJson() {
        ShopInfo shopInfo = new ShopInfo(1, "暴雨", 250.0, "www.baidu.com");
        Gson gson = new Gson();
        String json = gson.toJson(shopInfo);
        tv_gson_origin.setText(shopInfo.toString());
        tv_gson_last.setText(json);
    }

    /**
     * 将Json格式的字符串转换为Java集合
     */
    private void jsonToJavaList() {
        String json = "[\n" + "{\n" + "\"id\":1, \"name\":\"大虾1\",\n" + "\"price\":12.3,\n"
                + "\"imagePath\":\"http://192.168.10.165:8080/f1.jpg\"\n" + "},\n" + "{\n"
                + "\"id\":2, \"name\":\"大虾2\",\n" + "\"price\":12.5,\n"
                + "\"imagePath\":\"http://192.168.10.165:8080/f2.jpg\"\n" + "}\n" + "]";
        Gson gson = new Gson();
        List<ShopInfo> shops = gson.fromJson(json, new TypeToken<List<ShopInfo>>() {
        }.getType());

        tv_gson_origin.setText(json);
        String hint = "用这种方式：\nList<ShopInfo> shops = gson.fromJson(json, new TypeToken<List<ShopInfo>>() {}.getType());\n\n\n";
        SpannableString spannableString = new SpannableString(hint + shops.toString());
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.RED);
        spannableString.setSpan(colorSpan, 0, hint.length() - 1, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        // 用spannableString.toString()文字颜色不生效
        tv_gson_last.setText(spannableString);
    }

    /**
     * 将Json格式的字符串转换为Java对象
     */
    private void jsonToJavaObject() {
        String json = "{\n" + "\"id\":2, \"name\":\"大虾\",\n" + "\"price\":12.3,\n"
                + "\"imagePath\":\"http://192.168.10.165:8080/L05_Server/images/f1.jpg\"\n" + "}";
        Gson gson = new Gson();

        ShopInfo shopInfo = gson.fromJson(json, ShopInfo.class);

        tv_gson_origin.setText(json);
        tv_gson_last.setText(shopInfo.toString());
    }

}
