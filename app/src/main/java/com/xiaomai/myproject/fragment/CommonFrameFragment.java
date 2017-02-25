
package com.xiaomai.myproject.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.activity.DataStructActivity;
import com.xiaomai.myproject.activity.SdCardCheckActivity;
import com.xiaomai.myproject.adapter.CommonFrameFragmentAdapter;
import com.xiaomai.myproject.base.BaseFragment;
import com.xiaomai.myproject.bignumber.BigNumberActivity;
import com.xiaomai.myproject.glide.activity.GlideActivity;
import com.xiaomai.myproject.handler.HandlerActivity;
import com.xiaomai.myproject.json.activity.FastJsonActivity;
import com.xiaomai.myproject.json.activity.GsonActivity;
import com.xiaomai.myproject.json.activity.JsonActivity;
import com.xiaomai.myproject.lifecycle.LifeCycleActivityA;
import com.xiaomai.myproject.okhttp.activity.OKHttpActivity;
import com.xiaomai.myproject.picasso.activity.PicassoActivity;
import com.xiaomai.myproject.recyclerview.activity.RecyclerViewActivity;
import com.xiaomai.myproject.retrofit.RetrofitActivity;
import com.xiaomai.myproject.rxjava.RxJavaActivity;
import com.xiaomai.myproject.tablayout.activity.TabLayoutActivity;
import com.xiaomai.myproject.touchevent.TouchEventDispatchActivity;

/**
 * Created by XiaoMai on 2017/2/5 11:44.
 */
public class CommonFrameFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    private static final String TAG = CommonFrameFragment.class.getSimpleName();

    private ListView lvFragmentCommon;

    private String[] datas;

    @Override
    protected View initView() {
        Log.d(TAG, "initView: ");
        View view = View.inflate(mContext, R.layout.fragment_common_frame, null);
        lvFragmentCommon = (ListView) view.findViewById(R.id.lv_fragment_common);
        lvFragmentCommon.setOnItemClickListener(this);
        return view;
    }

    @Override
    protected void initVariables() {
        super.initVariables();
        datas = new String[] {
                "SD卡检测", "数据结构","Java中大数科学计数","事件分发机制", "Handler的使用", "Fragment", "TabLayout", "RxJava", "Retrofit", "Glide",
                "Picasso", "FastJson", "GSON", "JSON", "RecyclerView", "OKHttp"
        };
    }

    @Override
    protected void loadData() {
        Log.d(TAG, "loadData: ");
        super.loadData();
        lvFragmentCommon.setAdapter(new CommonFrameFragmentAdapter(datas));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = null;
        String data = datas[position].toLowerCase();
        if ("okhttp".equals(data)) {
            intent = new Intent(mContext, OKHttpActivity.class);
        } else if ("recyclerview".equals(data)) {
            intent = new Intent(mContext, RecyclerViewActivity.class);
        } else if ("json".equals(data)) {
            intent = new Intent(mContext, JsonActivity.class);
        } else if ("gson".equals(data)) {
            intent = new Intent(mContext, GsonActivity.class);
        } else if ("fastjson".equals(data)) {
            intent = new Intent(mContext, FastJsonActivity.class);
        } else if ("picasso".equals(data)) {
            intent = new Intent(mContext, PicassoActivity.class);
        } else if ("glide".equals(data)) {
            intent = new Intent(mContext, GlideActivity.class);
        } else if ("retrofit".equals(data)) {
            intent = new Intent(mContext, RetrofitActivity.class);
        } else if ("rxjava".equals(data)) {
            intent = new Intent(mContext, RxJavaActivity.class);
        } else if ("tablayout".equals(data)) {
            intent = new Intent(mContext, TabLayoutActivity.class);
        } else if ("fragment".equals(data)) {
            intent = new Intent(mContext, LifeCycleActivityA.class);
        } else if ("handler的使用".equals(data)) {
            intent = new Intent(mContext, HandlerActivity.class);
        } else if ("事件分发机制".equals(data)) {
            intent = new Intent(mContext, TouchEventDispatchActivity.class);
        } else if ("java中大数科学计数".equals(data)) {
            intent = new Intent(mContext, BigNumberActivity.class);
        } else if ("数据结构".equals(data)){
            intent = new Intent(mContext, DataStructActivity.class);
        } else if ("sd卡检测".equals(data)) {
            intent = new Intent(mContext, SdCardCheckActivity.class);
        }
        if (intent != null) {
            startActivity(intent);
        } else {
            Toast.makeText(mContext, "data == " + datas[position], Toast.LENGTH_SHORT).show();
        }
    }
}
