package com.xiaomai.myproject.lifecycle;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.xiaomai.myproject.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyFragmentA.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MyFragmentA#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyFragmentA extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "MyFragmentA";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Context mContext;

    private OnFragmentInteractionListener mListener;

    public MyFragmentA() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyFragmentA.
     */
    // TODO: Rename and change types and number of parameters
    public static MyFragmentA newInstance(String param1, String param2) {
        MyFragmentA fragment = new MyFragmentA();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach: ");
        mContext = context;
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    /**
     * 这个方法是在fragment初始化的时候调用，我们通常在这个方法中使用getArgument获取activity传来的初始化fragment的参数。
     * 注意：在 这个方法中我们不能获取activity中的控件，下面的这段代码是在fragment的onCreate中的，你发现activity不是空，
     * 但是text是null。因为，这是activity的onCreate还没有执行完，即activity还没有创建完，
     * 要想获取activity相关的资源应该在onActivityCreated中获取。
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    /**
     * 这个方法中我们主要是通过布局填充器获取fragment布局。我们在这个方法中通过view.findViewById初始化fragment中的控件
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(TAG, "onCreateView: ");
        return inflater.inflate(R.layout.fragment_my, container, false);
    }

    /**
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated: ");
        Button button = (Button) view.findViewById(R.id.bt_my_fragment);
        button.setOnClickListener(this);
    }

    /**
     * 这个方法是在activity的onCreate方法执行完执行这个方法，通知fragment关联的activity的onCreate方法执行完了，
     * activity创建完了，可以在这个方法中获取activity中的资源。例如可以获取activity布局中的TextView控件
     *
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated: ");
//        FrameLayout frameLayout = (FrameLayout) getActivity().findViewById(R.id.fl_life_cycle);
    }

    /**
     * 这个是在activity的onStart执行完立即执行，这个方法执行完fragment界面就显示出来了，但是现在还没有获取焦点，用户是不能操作。
     */
    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    /**
     * 这个方法是在activity的onResume方法执行完立即执行，此时fragment的获取了界面，用户可以操作。
     */
    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    /**
     * fragment失去焦点，此时用户是不能操作的，执行完立即执行activity的onPause方法。
     */
    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    /**
     * fragment不可见，执行完立即执行activity的onStop方法。
     */
    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    /**
     * 在onCreateView中创建的fragment视图会被销毁。Fragment的视图被回收。
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView: ");
    }

    /**
     * 当这个fragment不再使用时调用。需要注意的是，它即使经过了onDestroy()阶段，但仍然能从Activity中找到，因为它还没有Detach
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    /**
     * fragment与activity解除关联，fragment的所有的资源都被回收。
     */
    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach: ");
        mListener = null;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(mContext, LifeCycleActivityB.class));
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
