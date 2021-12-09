package com.yf.loadview.helper;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import com.yf.loadview.R;

/**
 * 用于切换布局,用一个新的布局覆盖在原布局之上
 * @author lwr 2021/12/9 11:05
 **/
public class DefaultLoadHelper {
    /**
     * 网络状态返回接口
     */
    protected Iml defaultIml;
    /**
     * 容器
     */
    private final FrameLayout group;
    /**
     * View show data
     */
    private final View binding;
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

    /**
     * @param isActivity true为activity反正为fragment
     */
    public DefaultLoadHelper(@NonNull View view, Activity activity, boolean isActivity) {
        super();
        this.binding = view;
        group = new FrameLayout(activity);
        ViewGroup parent;
        ViewGroup.LayoutParams layoutParams;
        View inflate = View.inflate(activity, R.layout.layout_lce, null);
        if (isActivity) {
            parent = (ViewGroup) view.getParent();
            layoutParams = parent.getLayoutParams();
        } else {
            parent = group;
            layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
        if (parent == null) {
            return;
        } else {
            if (isActivity) {
                parent.removeView(view);
                group.addView(inflate);
                parent.addView(group, layoutParams);
            } else {
                parent.removeView(view);
                parent.addView(inflate, layoutParams);
            }
        }
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        parent.addView(view, params);
        onCreateView(inflate);
    }

    public FrameLayout getGroup() {
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
                binding,
                group
        );
    }

    public Iml getDefaultIml() {
        return defaultIml;
    }

}
