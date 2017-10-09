package com.xiaomai.myproject.demo;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.xiaomai.myproject.R;

/**
 * Created by XiaoMai on 2017/5/23.
 */

public class StackFragment extends Fragment {

    public static StackFragment newInstance() {
        return new StackFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Button button = new Button(getActivity());
        button.setText(hashCode() + "");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .hide(StackFragment.this)
                        .add(R.id.container, StackFragment.newInstance())
                        .addToBackStack(null)
                        .commit();
            }
        });
        return button;
    }
}
