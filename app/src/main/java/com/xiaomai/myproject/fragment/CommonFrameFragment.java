
package com.xiaomai.myproject.fragment;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.activity.DataStructActivity;
import com.xiaomai.myproject.activity.FloatAnimator;
import com.xiaomai.myproject.activity.RuntimePermissionActivity;
import com.xiaomai.myproject.activity.SdCardSelectActivity;
import com.xiaomai.myproject.activity.WebViewActivity;
import com.xiaomai.myproject.adapter.CommonFrameFragmentAdapter;
import com.xiaomai.myproject.base.BaseFragment;
import com.xiaomai.myproject.bignumber.BigNumberActivity;
import com.xiaomai.myproject.capturing.CaptureActivity;
import com.xiaomai.myproject.demo.FragmentStackDemo;
import com.xiaomai.myproject.edittext.EditTextDemoActivity;
import com.xiaomai.myproject.glide.activity.GlideActivity;
import com.xiaomai.myproject.handler.HandlerActivity;
import com.xiaomai.myproject.json.activity.FastJsonActivity;
import com.xiaomai.myproject.json.activity.GsonActivity;
import com.xiaomai.myproject.json.activity.JsonActivity;
import com.xiaomai.myproject.largeimage.LargeImageActivity;
import com.xiaomai.myproject.launchmode.ActivityA;
import com.xiaomai.myproject.lifecycle.LifeCycleActivityA;
import com.xiaomai.myproject.mvp.view.UserLoginActivity;
import com.xiaomai.myproject.okhttp.activity.OKHttpActivity;
import com.xiaomai.myproject.picasso.activity.PicassoActivity;
import com.xiaomai.myproject.recyclerview.activity.RecyclerViewActivity;
import com.xiaomai.myproject.retrofit.RetrofitActivity;
import com.xiaomai.myproject.rxjava.RxJavaActivity;
import com.xiaomai.myproject.tablayout.activity.TabLayoutActivity;
import com.xiaomai.myproject.touchevent.TouchEventDispatchActivity;
import com.xiaomai.myproject.utils.Const;

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
        datas = new String[]{
                "拍照", "Edit Text", "Fragment Stack", "Animation", "LaunchMode", "注解", "大图加载", "MVP", "运行时权限", "SD卡检测", "数据结构", "Java中大数科学计数", "事件分发机制",
                "Handler的使用", "Fragment", "TabLayout", "RxJava", "Retrofit", "Glide", "Picasso",
                "FastJson", "GSON", "JSON", "RecyclerView", "OKHttp"
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
        String data = datas[position];
        if ("OKHttp".equals(data)) {
            intent = new Intent(mContext, OKHttpActivity.class);
        } else if ("RecyclerView".equals(data)) {
            intent = new Intent(mContext, RecyclerViewActivity.class);
        } else if ("JSON".equals(data)) {
            intent = new Intent(mContext, JsonActivity.class);
        } else if ("GSON".equals(data)) {
            intent = new Intent(mContext, GsonActivity.class);
        } else if ("FastJson".equals(data)) {
            intent = new Intent(mContext, FastJsonActivity.class);
        } else if ("Picasso".equals(data)) {
            intent = new Intent(mContext, PicassoActivity.class);
        } else if ("Glide".equals(data)) {
            intent = new Intent(mContext, GlideActivity.class);
        } else if ("Retrofit".equals(data)) {
            intent = new Intent(mContext, RetrofitActivity.class);
        } else if ("RxJava".equals(data)) {
            intent = new Intent(mContext, RxJavaActivity.class);
        } else if ("TabLayout".equals(data)) {
            intent = new Intent(mContext, TabLayoutActivity.class);
        } else if ("Fragment".equals(data)) {
            intent = new Intent(mContext, LifeCycleActivityA.class);
        } else if ("Handler的使用".equals(data)) {
            intent = new Intent(mContext, HandlerActivity.class);
        } else if ("事件分发机制".equals(data)) {
            intent = new Intent(mContext, TouchEventDispatchActivity.class);
        } else if ("Java中大数科学计数".equals(data)) {
            intent = new Intent(mContext, BigNumberActivity.class);
        } else if ("数据结构".equals(data)) {
            intent = new Intent(mContext, DataStructActivity.class);
        } else if ("SD卡检测".equals(data)) {
            intent = new Intent(mContext, SdCardSelectActivity.class);
        } else if ("运行时权限".equals(data)) {
            intent = new Intent(mContext, RuntimePermissionActivity.class);
        } else if ("MVP".equals(data)) {
            intent = new Intent(mContext, UserLoginActivity.class);
        } else if (TextUtils.equals("注解", data)) {
            intent = new Intent(mContext, WebViewActivity.class);
            intent.putExtra(Const.URL,
                    "https://github.com/CodeXiaoMai/MyProject/blob/master/md/%E6%B3%A8%E8%A7%A3.md");
        } else if (TextUtils.equals("大图加载", data)) {
            intent = new Intent(mContext, LargeImageActivity.class);
        } else if (TextUtils.equals("LaunchMode", data)) {
            intent = new Intent(mContext, ActivityA.class);
        } else if (TextUtils.equals("Animation", data)) {
            intent = new Intent(mContext, FloatAnimator.class);
        } else if (TextUtils.equals("Fragment Stack", data)) {
            intent = new Intent(mContext, FragmentStackDemo.class);
        } else if (TextUtils.equals("Edit Text", data)) {
            intent = new Intent(mContext, EditTextDemoActivity.class);
        } else if (TextUtils.equals("拍照", data)) {
            intent = new Intent(mContext, CaptureActivity.class);
        }
        if (intent != null) {
            startActivity(intent);
        } else {
            Toast.makeText(mContext, "data == " + datas[position], Toast.LENGTH_SHORT).show();
        }
    }
}
