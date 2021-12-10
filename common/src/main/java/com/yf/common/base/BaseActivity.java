package com.yf.common.base;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.yf.loadview.helper.DefaultLoadHelper;


/**
 * @Author
 * @cerate 2021/9/2 15:03
 **/
public abstract class BaseActivity<V extends ViewDataBinding> extends AppCompatActivity {

    protected V binding;
    protected DefaultLoadHelper defaultLoadHelper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, setLayoutId());
        initView();
        initListener();
    }

    /**
     * 布局id
     *
     * @return id
     */
    protected abstract @LayoutRes
    int setLayoutId();

    /**
     * 初始化
     */
    protected abstract void initView();

    /**
     * 点击事件
     */
    protected abstract void initListener();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
        defaultLoadHelper = null;
    }
}
