
package com.xiaomai.myproject.json.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.base.BaseActivity;
import com.xiaomai.myproject.json.activity.bean.DataInfo;
import com.xiaomai.myproject.json.activity.bean.FilmInfo;
import com.xiaomai.myproject.json.activity.bean.ShopInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XiaoMai on 2017/2/6 11:27.
 */
public class JsonActivity extends BaseActivity implements View.OnClickListener {

    private Button bt_json_tojava;

    private Button bt_json_tojavalist;

    private Button bt_json_complex;

    private Button bt_json_special;

    private TextView tv_json_origin;

    private TextView tv_json_last;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initViews() {
        super.initViews();
        setTitle("JSON解析");
        bt_json_tojava = (Button) findViewById(R.id.bt_json_tojava);
        bt_json_tojavalist = (Button) findViewById(R.id.bt_json_tojavalist);
        bt_json_complex = (Button) findViewById(R.id.bt_json_complex);
        bt_json_special = (Button) findViewById(R.id.bt_json_special);
        tv_json_origin = (TextView) findViewById(R.id.tv_json_origin);
        tv_json_last = (TextView) findViewById(R.id.tv_json_last);

        bt_json_tojava.setOnClickListener(this);
        bt_json_tojavalist.setOnClickListener(this);
        bt_json_complex.setOnClickListener(this);
        bt_json_special.setOnClickListener(this);
    }

    @Override
    protected int getCodeResId() {
        return R.string.code_json;
    }

    @Override
    protected int getContentLayout() {
        return R.layout.activity_json;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_json_tojava:
                jsonToJavaObject();
                break;
            case R.id.bt_json_tojavalist:
                jsonToJavaList();
                break;
            case R.id.bt_json_complex:
                jsonToJavaComplex();
                break;
            case R.id.bt_json_special:
                jsonToJavaSpecial();
                break;
        }
    }

    /**
     * 解析特殊格式
     */
    private void jsonToJavaSpecial() {
        String str = "{\n" + " \"code\": 0,\n" + " \"list\": {\n" + " \"0\": {\n"
                + " \"aid\": \"6008965\",\n" + " \"author\": \"哔哩哔哩番剧\",\n" + " \"coins\": 170,\n"
                + " \"copyright\": \"Copy\",\n" + " \"create\": \"2016-08-25 21:34\"\n" + " },\n"
                + " \"1\": {\n" + " \"aid\": \"6008938\",\n" + " \"author\": \"哔哩哔哩番剧\",\n"
                + " \"coins\": 404,\n" + " \"copyright\": \"Copy\",\n"
                + " \"create\": \"2016-08-25 21:33\"\n" + " }\n" + " }\n" + "}";

        FilmInfo filmInfo = new FilmInfo();
        try {
            JSONObject jsonObject = new JSONObject(str);
            // 第一层
            int code = jsonObject.optInt("code");
            JSONObject list = jsonObject.optJSONObject("list");

            List<FilmInfo.FileBean> lists = new ArrayList<>();
            filmInfo.setCode(code);
            filmInfo.setList(lists);
            // 第二层
            int length = list.length();
            for (int i = 0; i < length; i++) {
                JSONObject object = list.optJSONObject(String.valueOf(i));
                if (object != null) {
                    String aid = object.optString("aid");
                    String author = object.optString("author");
                    int coins = object.optInt("coins");
                    String copyright = object.optString("copyright");
                    String create = object.optString("create");

                    FilmInfo.FileBean fileBean = new FilmInfo.FileBean();
                    fileBean.setAid(aid);
                    fileBean.setAuthor(author);
                    fileBean.setCoins(coins);
                    fileBean.setCopyright(copyright);
                    fileBean.setCreate(create);

                    lists.add(fileBean);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        tv_json_origin.setText(str);
        tv_json_last.setText(filmInfo.toString());
    }

    /**
     * 复杂Json解析
     */
    private void jsonToJavaComplex() {
        String str = "{\n" + " \"data\": {\n" + " \"count\": 5,\n" + " \"items\": [\n" + " {\n"
                + " \"id\": 45,\n" + " \"title\": \"坚果\"\n" + " },\n" + " {\n" + " \"id\": 132,\n"
                + " \"title\": \"炒货\"\n" + " },\n" + " {\n" + " \"id\": 166,\n"
                + " \"title\": \"蜜饯\"\n" + " },\n" + " {\n" + " \"id\": 195,\n"
                + " \"title\": \"果脯\"\n" + " },\n" + " {\n" + " \"id\": 196,\n"
                + " \"title\": \"礼盒\"\n" + " }\n" + " ]\n" + " },\n" + " \"rs_code\": \"1000\",\n"
                + " \"rs_msg\": \"success\"\n" + "}\n";
        DataInfo dataInfo = new DataInfo();
        try {
            JSONObject jsonObject = new JSONObject(str);

            // 第一层解析
            JSONObject data = jsonObject.optJSONObject("data");
            String rs_code = jsonObject.optString("rs_code");
            String rs_msg = jsonObject.optString("rs_msg");

            DataInfo.DataBean dataBean = new DataInfo.DataBean();
            dataInfo.setRs_code(rs_code);
            dataInfo.setRs_msg(rs_msg);
            dataInfo.setData(dataBean);

            // 第二层解析
            int count = data.optInt("count");
            JSONArray items = data.optJSONArray("items");

            List<DataInfo.DataBean.ItemsBean> itemBeans = new ArrayList<>();
            dataBean.setCount(count);
            dataBean.setItems(itemBeans);
            // 第三层解析
            for (int i = 0; i < count; i++) {
                JSONObject item = items.optJSONObject(i);
                if (item != null) {
                    int id = item.optInt("id");
                    String title = item.optString("title");

                    DataInfo.DataBean.ItemsBean bean = new DataInfo.DataBean.ItemsBean();
                    bean.setId(id);
                    bean.setTitle(title);

                    itemBeans.add(bean);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        tv_json_origin.setText(str);
        tv_json_last.setText(dataInfo.toString());
    }

    /**
     * 将Json转换为JavaList集合
     */
    private void jsonToJavaList() {
        String str = "[\n" + "{\n" + "\"id\":1, \"name\":\"大虾1\",\n" + "\"price\":12.3,\n"
                + "\"imagePath\":\"http://192.168.10.165:8080/f1.jpg\"\n" + "},\n" + "{\n"
                + "\"id\":2, \"name\":\"大虾2\",\n" + "\"price\":12.5,\n"
                + "\"imagePath\":\"http://192.168.10.165:8080/f2.jpg\"\n" + "}\n" + "]";
        List<ShopInfo> shopInfos = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(str);
            int length = jsonArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if (jsonObject != null) {
                    int id = jsonObject.optInt("id");
                    String name = jsonObject.optString("name");
                    double price = jsonObject.optDouble("price");
                    String imagePath = jsonObject.optString("imagePath");
                    ShopInfo shopInfo = new ShopInfo(id, name, price, imagePath);
                    shopInfos.add(shopInfo);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        tv_json_origin.setText(str);
        tv_json_last.setText(shopInfos.toString());
    }

    /**
     * 将Json转换为Java对象
     */
    private void jsonToJavaObject() {
        String json = "{\n" + "\"id\":2, \"name\":\"大虾\",\n" + "\"price\":12.3,\n"
                + "\"imagePath\":\"http://192.168.10.165:8080/L05_Server/images/f1.jpg\"\n" + "}";
        ShopInfo shopInfo = null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            int id = jsonObject.getInt("id");
            // optXXX()方法会在key值对应的value不存在的时候返回一个空字符串或指定的默认值，
            int id1 = jsonObject.optInt("id");
            String name = jsonObject.optString("name");
            double price = jsonObject.optDouble("price");
            String imagePath = jsonObject.optString("imagePath");
            // 这种会崩溃
            // String bucunzai1 = jsonObject.getString("askld");
            // 不会崩溃
            String bucunzai = jsonObject.optString("skldf");
            shopInfo = new ShopInfo(id, name, price, imagePath);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        tv_json_origin.setText(json);
        tv_json_last.setText(shopInfo.toString());
    }

}
