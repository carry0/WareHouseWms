package com.yf.loadview.helper;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import com.yf.loadview.R;

/**
 * 用于切换布局,用一个新的布局覆盖在原布局之上
 *
 * @author lwr 2021/12/9 11:05
 **/
public class DefaultLoadHelper{
    /**
     * 容器
     */
    private  ViewGroup group;
    /**
     * View show data
     */
    private final View binding;
    /**
     * 网络状态返回接口
     */
    protected Iml defaultIml;
    /**
     * Fragment中由于网络异常导致加载失败显示的布局
     */
    private ViewStub badNetworkView = null;
    /**
     * Activity中显示的加载等待的控件
     */
    private ViewStub loading = null;
    /**
     * Fragment中由于服务器异常导致加载失败显示的布局
     */
    private ViewStub loadErrorView = null;
    /**
     * Fragment中界面上没有任何内容时展示的布局
     */
    private ViewStub noContentView = null;


    public DefaultLoadHelper(@NonNull View view) {
        super();
        this.binding = view;
        //获取当前view的LayoutParams
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        //加载ViewStub布局资源
        View inflate = View.inflate(view.getContext(), R.layout.layout_lce, null);
        onCreateView(inflate);
        group = (ViewGroup) view.getParent();
        if (group == null) {
            group = new FrameLayout(view.getContext());
            group.addView(view,layoutParams);
        }
        group.addView(inflate, layoutParams);
    }

    public View getGroup() {
        return group;
    }

    private void initUpView(View view) {
        loading = view.findViewById(R.id.loading);
        noContentView = view.findViewById(R.id.noContentView);
        badNetworkView = view.findViewById(R.id.badNetworkView);
        loadErrorView = view.findViewById(R.id.loadErrorView);
    }

    private void onCreateView(View view) {
        initUpView(view);
        if (loading == null) {
            return;
        }
        if (badNetworkView == null) {
            return;
        }
        if (loadErrorView == null) {
            return;
        }

        defaultIml = new DefaultIml(
                badNetworkView,
                loading,
                loadErrorView,
                noContentView,
                binding);
    }

    public Iml getDefaultIml() {
        return defaultIml;
    }

}
