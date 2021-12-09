package com.yf.common.base;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.alibaba.fastjson.JSONObject;
import com.orhanobut.logger.Logger;
import com.yf.common.AppCommon;
import com.yf.common.R;
import com.yf.common.base.loadhelper.DefaultIml;
import com.yf.common.base.loadhelper.Iml;
import com.yf.common.tool.ConfigManage;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author lwr 2021/9/2 15:03
 **/
public abstract class BaseFragment<V extends ViewDataBinding> extends Fragment {
    protected V binding;
    /**
     * 容器
     */
    protected FrameLayout frameLayout;
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (frameLayout == null) {
            frameLayout = new FrameLayout(requireActivity());
            View inflate = View.inflate(requireActivity(), R.layout.layout_lce, null);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
            binding = setBinding(inflater, container, savedInstanceState);
            inflate.setLayoutParams(layoutParams);
            onCreateView(inflate);
            frameLayout.addView(binding.getRoot());
            frameLayout.addView(inflate);
        }
        Logger.d("hello");
        return frameLayout;
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
            Logger.e("loading is null");
        }
        if (badNetworkView == null) {
            Logger.e("badNetworkView is null");
        }
        if (loadErrorView == null) {
            Logger.e("loadErrorView is null");
        }

        defaultIml = new DefaultIml(
                badNetworkView,
                loading,
                loadErrorView,
                noContentView,
                binding.getRoot(),
                frameLayout
        );
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                onBackPressed();
            }
        });
    }

    protected void onBackPressed() {
        navigateUp();
    }

    /**
     * 初始化控件
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 有Request请求返回
     *
     * @param dataLive 网络请求
     */
    public <T> T doNetRequest(@NotNull MutableLiveData<BaseRequest<T>> dataLive) {
        MutableLiveData<T> liveData = new MutableLiveData<>();
        dataLive.observe(this, new BaseObserver<T>() {
            @Override
            protected void onError(int code, String message) {
                liveData.setValue((T) message);
                if (code == ConfigManage.CODE_ERROR) {
                    Toast.makeText(requireContext(), getString(R.string.bad_network_view_tip), Toast.LENGTH_SHORT).show();
                } else {
                    defaultIml.showLoadErrorView(TextUtils.isEmpty(message) ? (AppCommon.getInstance().getApplicationContext()).getResources().getString(R.string.failed_load_data) : message);
                }
            }

            @Override
            protected void onSuccess(T data) {
                if (data instanceof List) {
                    if (((List) data).size() == 0) {
                        defaultIml.showNoContentView("data is null!");
                    }
                }
                liveData.setValue(data);
            }
        });
        return liveData.getValue();
    }

    /**
     * 没有Request请求返回
     *
     * @param dataLive 网络请求
     */
    public <T> T doNotBaseNetRequest(@NotNull MutableLiveData<T> dataLive) {
        defaultIml.startLoading();
        MutableLiveData<T> liveData = new MutableLiveData<>();
        dataLive.observe(this, t -> {
            if (t == null) {
                liveData.setValue((T) new JSONObject());
                defaultIml.showLoadErrorView(TextUtils.isEmpty("message") ? (AppCommon.getInstance().getApplicationContext()).getResources().getString(R.string.failed_load_data) : "message");
            } else {
                if (t instanceof List) {
                    if (((List) t).size() == 0) {
                        defaultIml.showNoContentView("data is null!");
                    }
                }
                liveData.setValue(t);
            }
        });
        return liveData.getValue();
    }


    protected NavController findController() {
        return NavHostFragment.findNavController(this);
    }


    protected void navigate(int id) {
        findController().navigate(id);
    }


    /**
     * navigation回退
     */
    protected void navigateUp() {
        findController().navigateUp();
    }


    /**
     * 绑定布局
     *
     * @param inflater           inflater
     * @param container          container
     * @param savedInstanceState bundle
     * @return View
     */
    protected abstract V setBinding(@NonNull LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState);

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        frameLayout = null;
    }
}
