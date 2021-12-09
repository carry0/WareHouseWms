package com.yf.common.base.loadhelper;

import android.view.View;

/**
 * 网络状态返回接口
 * @author lwr
 **/
public interface Iml {
    /**
     * 开始加载网络
     */
    void startLoading();

    /**
     * 结束加载
     */
    void loadFinished();

    /**
     * 由于服务器异常导致加载失败
     * @param tip text
     */
    void showLoadErrorView(String tip);

    /**
     * 由于网络异常导致加载失败
     * @param onClickListener onClick
     */
    void showBadNetworkView(View.OnClickListener onClickListener);

    /**
     * 界面上没有任何内容时展示的布局
     * @param tip text
     */
    void showNoContentView(String tip);

}
