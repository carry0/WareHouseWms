package com.yf.common.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

/**
 * @Author
 * @cerate 2021/9/2 15:03
 **/
public abstract class BaseFragment<V extends ViewDataBinding> extends Fragment {
    protected V binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (binding == null) {
            binding = setBinding(inflater, container, savedInstanceState);
        }
        return binding.getRoot();
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
    }
}
