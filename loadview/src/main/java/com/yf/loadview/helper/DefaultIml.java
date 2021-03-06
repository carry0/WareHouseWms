package com.yf.loadview.helper;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yf.loadview.AppLoad;
import com.yf.loadview.R;

/**
 * 操作界面：由于网络变化而变化的界面，修改layout布局等等
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
     * 显示数据界面
     */
    private final View binding;

    public DefaultIml(ViewStub badNetworkView,
                      ViewStub loading,
                      ViewStub loadErrorView,
                      ViewStub noContentView,
                      View binding){
        this.badNetworkView = badNetworkView;
        this.loading = loading;
        this.loadErrorView = loadErrorView;
        this.noContentView = noContentView;
        this.binding = binding;
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
        //ViewStub只能inflate()一次
        View inflate = loadErrorView.inflate();
        TextView viewById = inflate.findViewById(R.id.loadErrorText);
        viewById.setText(tip);
        loadErrorView.setVisibility(View.VISIBLE);
    }

    /**
     * 当Activity中的内容因为网络原因无法显示的时候，通过此方法显示提示界面给用户。
     */
    @Override
    public void showBadNetworkView(View.OnClickListener onClickListener) {
        Toast.makeText(AppLoad.getInstance(), AppLoad.getInstance().getString(R.string.bad_network_view_tip), Toast.LENGTH_SHORT).show();
        loadFinished();
        binding.setVisibility(View.GONE);
        badNetworkView.inflate().setOnClickListener(v -> {
            badNetworkView.setVisibility(View.GONE);
            binding.setVisibility(View.VISIBLE);
            onClickListener.onClick(v);
        });
        badNetworkView.setVisibility(View.VISIBLE);


    }

    /**
     * 当Activity中没有任何内容的时候，通过此方法显示提示界面给用户。
     *
     * @param tip 界面中的提示信息
     */
    @Override
    public void showNoContentView(String tip) {
        loadFinished();
        View inflate = noContentView.inflate();
        TextView textView = inflate.findViewById(R.id.noContentText);
        textView.setText(tip);
        noContentView.setVisibility(View.VISIBLE);
    }

}
