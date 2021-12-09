package com.yf.common.base;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.yf.common.AppCommon;
import com.yf.common.R;
import com.yf.common.tool.ConfigManage;
import com.yf.loadview.helper.DefaultLoadHelper;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author lwr 2021/9/2 15:03
 **/
public abstract class BaseFragment<V extends ViewDataBinding> extends Fragment {
    protected V binding;
    protected DefaultLoadHelper defaultLoadHelper;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = setBinding(inflater, container, savedInstanceState);
        defaultLoadHelper = new DefaultLoadHelper(binding.getRoot(), requireActivity(),false);
        return defaultLoadHelper.getGroup();
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
                    defaultLoadHelper.getDefaultIml().showLoadErrorView(TextUtils.isEmpty(message) ? (AppCommon.getInstance().getApplicationContext()).getResources().getString(R.string.failed_load_data) : message);
                }
            }

            @Override
            protected void onSuccess(T data) {
                if (data instanceof List) {
                    if (((List) data).size() == 0) {
                        defaultLoadHelper.getDefaultIml().showNoContentView("data is null!");
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
        defaultLoadHelper.getDefaultIml().startLoading();
        MutableLiveData<T> liveData = new MutableLiveData<>();
        dataLive.observe(this, t -> {
            if (t == null) {
                liveData.setValue((T) new JSONObject());
                defaultLoadHelper.getDefaultIml().showLoadErrorView(TextUtils.isEmpty("message") ? (AppCommon.getInstance().getApplicationContext()).getResources().getString(R.string.failed_load_data) : "message");
            } else {
                if (t instanceof List) {
                    if (((List) t).size() == 0) {
                        defaultLoadHelper.getDefaultIml().showNoContentView("data is null!");
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
        defaultLoadHelper = null;
    }
}
