
package com.xiaomai.myproject.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * 流式布局</br>
 * Created by XiaoMai on 2016/12/19 16:25.
 */
public class FlowViewGroup extends ViewGroup {
    public FlowViewGroup(Context context) {
        this(context, null);
    }

    public FlowViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 在onMeasure里，测量所有子View的宽高，以及确定ViewGroup自己的宽高
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 获取系统传递过来测量出的宽度 高度，以及相应的测量模式。
        // 如果测量模式为 EXACTLY( 确定的dp值，match_parent)，则可以调用setMeasuredDimension()设置，
        // 如果测量模式为 AT_MOST(wrap_content),则需要经过计算再去调用setMeasuredDimension()设置
        int widthMeasure = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMeasure = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        // 计算宽高，wrap_content测量模式下会使用
        int maxLineWidth = 0;
        int totalHeight = 0;
        int currentLineWidth = 0;
        int currentLineHeight = 0;

        int childCount = getChildCount();
        View childView = null;
        MarginLayoutParams params = null;
        int childWidth, childHeight;
        for (int i = 0; i < childCount; i++) {
            childView = getChildAt(i);
            if (GONE == childView.getVisibility()) {
                continue;
            }
            // 先测量子View
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
            // 获取子View的layoutParams，（子View的LayoutParams的对象类型，取决于其ViewGroup的
            // generateLayoutParams()方法返回的对象类型，这里返回的是MarginLayoutParams）
            params = (MarginLayoutParams) childView.getLayoutParams();
            // 子View需要的宽度为子View本身宽度 + marginLeft + marginRight
            childWidth = childView.getMeasuredWidth() + params.leftMargin + params.rightMargin;
            childHeight = childView.getMeasuredHeight() + params.topMargin + params.bottomMargin;
            // 如果当前的行宽度大于 父控件允许的最大宽度 则要换行
            // 父控件允许的最大宽度 如果要适配 padding 这里要- getPaddingLeft() -
            // getPaddingRight()
            // 即为测量出的宽度减去父控件的左右边距
            if (currentLineWidth + childWidth > widthMeasure - getPaddingLeft()
                    - getPaddingRight()) {
                // 通过比较当前行宽和以前存储的最大行宽,得到最新的最大行宽,用于设置父控件的宽度
                // 我觉得有点问题
                maxLineWidth = Math.max(maxLineWidth, currentLineWidth);
                // 父控件的高度增加了，为当前高度+当前行的高度
                totalHeight += currentLineHeight;
                // 换行后刷新当前行宽高数据:因为新的一行就这一个View，
                // 所以为当前这个view占用的宽高(要加上View 的 margin)
                currentLineWidth = childWidth;
                currentLineHeight = childHeight;
            } else {
                // 不换行：叠加当前行宽 和 比较当前行高:
                currentLineWidth += childWidth;
                currentLineHeight = Math.max(currentLineHeight, childHeight);
            }
            // 如果已经是最后一个View,要比较当前行的 宽度和最大宽度，叠加一共的高度
            if (i == childCount - 1) {
                maxLineWidth = Math.max(maxLineWidth, currentLineWidth);
                totalHeight += childHeight;
            }
        }
        // 适配padding,如果是wrap_content,则除了子控件本身占据的控件，还要在加上父控件的padding
        setMeasuredDimension(
                widthMode == MeasureSpec.EXACTLY ? widthMeasure
                        : maxLineWidth + getPaddingLeft() + getPaddingRight(),
                heightMode == MeasureSpec.EXACTLY ? heightMeasure
                        : totalHeight + getPaddingTop() + getPaddingBottom());
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int childCount = getChildCount();
        // ParentView的宽度（包含Padding）
        int width = getWidth();
        // 除去padding的右边界
        int rightLimit = width - getPaddingRight();
        // 基准的left top (子类.layout(),里的坐标是基于父控件的坐标，所以
        // x应该是从0+父控件左内边距开始，y从0+父控件上内边距开始)
        int baseLeft = getPaddingLeft();
        int baseTop = getPaddingTop();
        // 当前left top
        int currentLeft = baseLeft;
        int currentTop = baseTop;

        // 子View
        View childView = null;
        int childLeft, childTop, childRight, childBottom;
        MarginLayoutParams params = null;
        // 子View Layout需要的宽高（包含margin），用于计算是否越界
        int childWidth, childHeight;
        // 子View 本事的高度
        int childRealWidth, childRealHeight;
        // 临时增加一个temp 存储上一个View的高度 解决过长的两行View导致显示不正确的bug
        int lastChildHeight = 0;
        for (int i = 0; i < childCount; i++) {
            childView = getChildAt(i);
            // 如果Gone，就不布局了
            if (childView.getVisibility() == GONE) {
                continue;
            }
            // 获取子View本身的宽高
            childRealWidth = childView.getMeasuredWidth();
            childRealHeight = childView.getMeasuredHeight();
            // 获取子View的LayoutParams，取其margin
            params = (MarginLayoutParams) childView.getLayoutParams();
            childWidth = childRealWidth + params.leftMargin + params.rightMargin;
            childHeight = childRealHeight + params.topMargin + params.bottomMargin;
            // 这里要考虑padding，所以右边界为 ViewParent宽度(包含padding) -ViewParent右内边距
            if (currentLeft + childWidth > rightLimit) {
                // 如果当前行已经放不下该子View了 需要换行放置：
                currentTop = currentTop + lastChildHeight;
                childLeft = baseLeft + params.leftMargin;
                childTop = currentTop + params.topMargin;
                childRight = childLeft + childRealWidth;
                childBottom = childTop + childRealHeight;
                currentLeft = baseLeft + childWidth;
            } else {
                // 当前行可以放下子View
                childLeft = currentLeft + params.leftMargin;
                childTop = currentTop + params.topMargin;
                childRight = childLeft + childRealWidth;
                childBottom = childTop + childRealHeight;
                currentLeft += childWidth;
            }
            lastChildHeight = childHeight;
            // 布局子View
            childView.layout(childLeft, childTop, childRight, childBottom);
        }
    }

    @Override
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }
}
