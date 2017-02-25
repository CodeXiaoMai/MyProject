package com.xiaomai.myproject.lifecycle;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.base.BaseActivity;

public class LifeCycleActivityB extends BaseActivity implements MyFragmentB.OnFragmentInteractionListener{


    private static final String TAG = "Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, "onRestart: ");
    }

    @Override
    protected void initViews() {
        super.initViews();
        setTitle("动态添加Fragment");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fl_life_cycle, MyFragmentB.newInstance("param1", "param2"));
        transaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
    @Override
    protected int getContentLayout() {
        return R.layout.activity_life_cycle_activity_b;
    }
}
