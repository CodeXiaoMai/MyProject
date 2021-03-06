package com.xiaomai.myproject.demo;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.base.BaseActivity;
import com.xiaomai.myproject.utils.MyLog;

/**
 * Created by XiaoMai on 2016/10/19 16:35.
 */
public class PropertyAnimationDemoActivity extends BaseActivity {

    private ImageView mImageView;

    private Button mButtonAnimationSet;

    @Override
    protected void initVariables() {
        super.initVariables();
        /**
         * ValueAnimator的使用
         */
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1, 5, 10, 0, 1, 5, 10, 0, 1, 5, 10, 0, 1, 5, 10)
                .setDuration(1000);
        /**
         * repeatMode有三种：
         * INFINITE 无限循环
         * RESTART 默认参数，
         * REVERSE 反转运行
         */
        valueAnimator.setRepeatMode(ValueAnimator.INFINITE);
        valueAnimator.setRepeatCount(1);
        //动画开始的延迟时间
        valueAnimator.setStartDelay(1000);
        valueAnimator.start();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                MyLog.e(animatedValue + "");
            }
        });
    }

    @Override
    protected void initViews() {
        mImageView = (ImageView) findViewById(R.id.iv);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * "alpha" 不透明度
                 * "rotation" 旋转
                 * "translationX" 水平位移
                 * "translationY" 垂直位移
                 * "scaleY"     "垂直缩放"
                 * "scaleX"     "水平缩放"
                 */
                ObjectAnimator animator = ObjectAnimator.ofFloat(mImageView, "rotation", 0f, 360.0f, 0f, 10.0f, 0f)
                        .setDuration(1500);
                animator.start();
                animator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
            }
        });

        mButtonAnimationSet = (Button) findViewById(R.id.button_ani_set);
        mButtonAnimationSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 组合动画的使用
                 * 先从屏幕外移动进屏幕，然后开始旋转360度，旋转的同时进行淡入淡出操作，
                 */
                ObjectAnimator movieIn = ObjectAnimator.ofFloat(mImageView, "translationX", -500f, 0);
                ObjectAnimator rotation = ObjectAnimator.ofFloat(mImageView, "rotation", 0, 360);
                ObjectAnimator alpha = ObjectAnimator.ofFloat(mImageView, "alpha", 1, 0, 1);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet
                        .play(rotation)
                        .with(alpha)
                        .after(movieIn);
                animatorSet.setDuration(5000).start();
            }
        });
    }

    @Override
    protected int getContentLayout() {
        return R.layout.act_property_animation;
    }
}
