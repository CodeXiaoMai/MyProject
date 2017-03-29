package com.xiaomai.myproject.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.TextView;

import com.xiaomai.myproject.R;

/**
 * Created by XiaoMai on 2017/3/28 16:36.
 */

public class FloatAnimator extends Activity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator);
        textView = (TextView) findViewById(R.id.textView);
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator translation = ObjectAnimator.ofFloat(textView, "translationY", 0, -800);
        final ObjectAnimator alpha = ObjectAnimator.ofFloat(textView, "alpha", 1.0f, 0f);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(textView, "scaleX", 1.0f, 2.0f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(textView, "scaleY", 1.0f, 2.0f);
        animatorSet.playTogether(translation, scaleX, scaleY);
        translation.setInterpolator(new AccelerateDecelerateInterpolator());
        translation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                alpha.setDuration(1000).start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animatorSet.setDuration(3000).start();
    }
}
