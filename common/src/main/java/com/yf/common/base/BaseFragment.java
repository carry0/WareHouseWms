package com.yf.common.base;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
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

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @Author
 * @cerate 2021/9/2 15:03
 **/
public abstract class BaseFragment<V extends ViewDataBinding> extends Fragment {
    protected V binding;
    /**
     * Fragment中由于网络异常导致加载失败显示的布局
     */
    protected View badNetworkView = null;
    /**
     * 容器
     */
    private FrameLayout frameLayout;
    /**
     * Activity中显示的加载等待的控件
     */
    private ProgressBar loading = null;
    /**
     * Fragment中由于服务器异常导致加载失败显示的布局
     */
    private View loadErrorView = null;
    /**
     * Fragment中界面上没有任何内容时展示的布局
     */
    private View noContentView = null;

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
        return frameLayout;
    }

    private void initUpView(View view) {
        View loadingView = View.inflate(view.getContext(), R.layout.loading, null);

        loading = loadingView.findViewById(R.id.loading);
        noContentView = view.findViewById(R.id.noContentView);
        badNetworkView = view.findViewById(R.id.badNetworkView);
        loadErrorView = view.findViewById(R.id.loadErrorView);
    }

    private void onCreateView(View view) {
        initUpView(view);
        String TAG = "BaseFragment";
        if (loading == null) {
            Log.e(TAG, "loading is null");
        }
        if (badNetworkView == null) {
            Log.e(TAG, "badNetworkView is null");
        }
        if (loadErrorView == null) {
            Log.e(TAG, "loadErrorView is null");
        }
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
                    showBadNetworkView();
                } else {
                    showLoadErrorView(TextUtils.isEmpty(message) ? (AppCommon.getInstance().getApplicationContext()).getResources().getString(R.string.failed_load_data) : message);
                }
            }

            @Override
            protected void onSuccess(T data) {
                if (data instanceof List) {
                    if (((List) data).size() == 0) {
                        showNoContentView("data is null!");
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
        MutableLiveData<T> liveData = new MutableLiveData<>();
        dataLive.observe(this, t -> {
            if (t == null) {
                liveData.setValue((T) new JSONObject());
                showLoadErrorView(TextUtils.isEmpty("message") ? (AppCommon.getInstance().getApplicationContext()).getResources().getString(R.string.failed_load_data) : "message");
            } else {
                if (t instanceof List) {
                    if (((List) t).size() == 0) {
                        showNoContentView("data is null!");
                    }
                }
                liveData.setValue(t);
                showBadNetworkView();

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

    public void startLoading() {
        loadFinished();
        loading.setVisibility(View.VISIBLE);
    }

    public void loadFinished() {
        loading.setVisibility(View.GONE);
        loadErrorView.setVisibility(View.GONE);
        badNetworkView.setVisibility(View.GONE);
        noContentView.setVisibility(View.GONE);
        binding.getRoot().setVisibility(View.GONE);
    }

    /**
     * 当Activity中的加载内容服务器返回失败，通过此方法显示提示界面给用户。
     *
     * @param tip 界面中的提示信息
     */
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
    public void showBadNetworkView() {
        Toast.makeText(requireContext(), getString(R.string.bad_network_view_tip), Toast.LENGTH_SHORT).show();
        loadFinished();
        binding.getRoot().setVisibility(View.GONE);
        badNetworkView.setVisibility(View.VISIBLE);
        frameLayout.setOnClickListener(v -> {
            badNetworkView.setVisibility(View.GONE);
            binding.getRoot().setVisibility(View.VISIBLE);
            Log.i("TAG", "doNotBaseNetRequest: ");
        });
    }

    /**
     * 当Activity中没有任何内容的时候，通过此方法显示提示界面给用户。
     *
     * @param tip 界面中的提示信息
     */
    public void showNoContentView(String tip) {
        loadFinished();
        View view = View.inflate(noContentView.getContext(), R.layout.loading, null);
        TextView textView = view.findViewById(R.id.noContentText);
        textView.setText(tip);
        noContentView.setVisibility(View.VISIBLE);
    }
}
