package com.yf.common.base.loadhelper;

import android.view.View;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yf.common.AppCommon;
import com.yf.common.R;

/**
 * 由于网络变化而变化的界面
 *
 * @author lwr
 **/
public class DefaultIml implements Iml {

    /**
     * Fragment中由于网络异常导致加载失败显示的布局
     */
    private final ViewStub badNetworkView;
    /**
     * Fragment中显示的加载等待的控件
     */
    private final ViewStub loading;
    /**
     * Fragment中由于服务器异常导致加载失败显示的布局
     */
    private final ViewStub loadErrorView;
    /**
     * Fragment中界面上没有任何内容时展示的布局
     */
    private final ViewStub noContentView;

    /**
     * groupView
     */
    private final FrameLayout frameLayout;

    /**
     * 显示数据界面
     */
    private final View binding;

    public DefaultIml(ViewStub badNetworkView,
                      ViewStub loading,
                      ViewStub loadErrorView,
                      ViewStub noContentView,
                      View binding,
                      FrameLayout frameLayout) {
        this.badNetworkView = badNetworkView;
        this.loading = loading;
        this.loadErrorView = loadErrorView;
        this.noContentView = noContentView;
        this.binding = binding;
        this.frameLayout = frameLayout;
    }

    @Override
    public void startLoading() {
        loadFinished();
        loading.setVisibility(View.VISIBLE);
    }

    @Override
    public void loadFinished() {
        loading.setVisibility(View.GONE);
        loadErrorView.setVisibility(View.GONE);
        badNetworkView.setVisibility(View.GONE);
        noContentView.setVisibility(View.GONE);
        binding.setVisibility(View.GONE);
    }

    @Override
    public void showLoadErrorView(String tip) {
        loadFinished();
        loadErrorView.setVisibility(View.VISIBLE);
        View view = View.inflate(loadErrorView.getContext(), R.layout.load_error_view, null);
        TextView textView = view.findViewById(R.id.loadErrorText);
        textView.setText(tip);
    }

    /**
     * 当Activity中的内容因为网络原因无法显示的时候，通过此方法显示提示界面给用户。
     */
    @Override
    public void showBadNetworkView(View.OnClickListener onClickListener) {
        Toast.makeText(AppCommon.getInstance(), AppCommon.getInstance().getString(R.string.bad_network_view_tip), Toast.LENGTH_SHORT).show();
        loadFinished();
        binding.setVisibility(View.GONE);
        badNetworkView.setVisibility(View.VISIBLE);
        frameLayout.setOnClickListener(v -> {
            badNetworkView.setVisibility(View.GONE);
            binding.setVisibility(View.VISIBLE);
            onClickListener.onClick(v);
        });
    }

    /**
     * 当Activity中没有任何内容的时候，通过此方法显示提示界面给用户。
     *
     * @param tip 界面中的提示信息
     */
    @Override
    public void showNoContentView(String tip) {
        loadFinished();
        View view = View.inflate(noContentView.getContext(), R.layout.loading, null);
        TextView textView = view.findViewById(R.id.noContentText);
        textView.setText(tip);
        noContentView.setVisibility(View.VISIBLE);
    }

}
