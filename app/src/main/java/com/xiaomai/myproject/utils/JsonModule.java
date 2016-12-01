
package com.xiaomai.myproject.utils;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XiaoMai on 2016/12/1 11:39.
 */
public class JsonModule {

    private JSONObject jsonObject;

    private String string;

    public JsonModule(String string) {
        if (TextUtils.isEmpty(string)) {
            new JsonModule(new JSONObject());
            return;
        }
        try {
            new JsonModule(new JSONObject(string));
            return;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        new JsonModule(new JSONObject());
    }

    public JsonModule(JSONObject jsonObject) {
        this.jsonObject = (jsonObject == null ? new JSONObject() : jsonObject);
    }

    public int length() {
        return jsonObject.length();
    }

    public JsonModule getJsonModule(String key) {
        try {
            JSONObject object = jsonObject.getJSONObject(key);
            if (JSONObject.NULL.equals(object)) {
                return null;
            }
            JsonModule jsonModule = new JsonModule(object);
            return jsonModule;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String get(String key) {
        try {
            Object object = jsonObject.get(key);
            if (JSONObject.NULL.equals(object)) {
                return "";
            }
            String str = object.toString();
            return TextUtils.isEmpty(str) ? "" : str;
        } catch (JSONException e) {
            e.printStackTrace();
            MyLog.d("没有字段：" + key);
        }
        return "";
    }

    public int getInt(String key) {
        return ParseUtils.parseToInteger(get(key));
    }

    public double getDouble(String key) {
        return ParseUtils.parseToDouble(get(key));
    }

    public float getFloat(String key) {
        return ParseUtils.parseToFloat(get(key));
    }

    public boolean getBoolean(String key) {
        return ParseUtils.parseToBoolean(get(key));
    }

    public List<JsonModule> getList(String key) {
        JSONArray jsonArray = null;
        try {
            jsonArray = jsonObject.getJSONArray(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        List<JsonModule> list = new ArrayList<>();
        if (jsonArray == null) {
            return list;
        }
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                list.add(new JsonModule(jsonArray.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public List<JsonModule> getList() {
        return getList("list");
    }

    public void put(String key, String value) {
        try {
            jsonObject.put(key, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void put(String key, int value) {
        try {
            jsonObject.put(key, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void put(String key, boolean value) {
        try {
            jsonObject.put(key, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return jsonObject.toString();
    }
}
