
package com.xiaomai.myproject.lifecycle;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.base.BaseActivity;

public class LifeCycleActivityA extends BaseActivity implements MyFragmentA.OnFragmentInteractionListener{

    private static final String TAG = "Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }

    @Override
    protected int getContentLayout() {
        return R.layout.activity_life_cycle_a;
    }

    @Override
    protected void initViews() {
        super.initViews();
        setTitle("Activity的布局中包含Fragment");
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
