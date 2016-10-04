package com.xiaomai.myproject.base;

/**
 * Created by XiaoMai on 2016/10/4.
 */
public abstract class BaseLazyFragment extends BaseFragment {

    /**
     * 当前Fragment是否可见
     */
    protected boolean isVisible;

    /**
     * 这里实现数据的缓加载
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    /**
     * Fragment可见时调用
     */
    protected void onVisible() {
        lazyLoad();
    }

    protected abstract void lazyLoad();

    protected void onInvisible() {
    }

}
