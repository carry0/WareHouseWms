package com.yf.warehousewms.model.home.adjust.fm;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.yf.common.base.BaseFragment;
import com.yf.common.bean.LoginBean;
import com.yf.common.tool.ConfigManage;
import com.yf.common.tool.SpModel;
import com.yf.warehousewms.App;
import com.yf.warehousewms.R;
import com.yf.warehousewms.databinding.FragmentAdjustBinding;
import com.yf.warehousewms.model.home.adapter.AdjustAdapter;
import com.yf.warehousewms.model.home.adjust.vm.AdjustViewModel;

import java.lang.ref.WeakReference;

/**
 * 倒仓上架
 * @author lwr 2021-06-02
 */
public class AdjustFragment extends BaseFragment<FragmentAdjustBinding> {

    WeakReference<AdjustAdapter> adapter;
    AdjustViewModel viewModel;
    LoginBean loginBean;

    @Override
    protected FragmentAdjustBinding setBinding(@NonNull LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        return FragmentAdjustBinding.inflate(inflater,container,false);
    }

    @Override
    protected void initView() {
        viewModel = new ViewModelProvider(findController().getViewModelStoreOwner(R.id.nav_model)).get(AdjustViewModel.class);
//        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(App.getInstance()).create(AdjustViewModel.class);
        adapter = new WeakReference<>(new AdjustAdapter());
        binding.rvList.setAdapter(adapter.get());

    }

    private void initListener() {
        adapter.get().setOnItemClickListener((view, data, position) -> navigate(R.id.action_to_adjustDetail));
        binding.button2.setOnClickListener(v -> putAway());
    }

    private void putAway() {
//        JSONObject jsonObject = doNotBaseNetRequest(viewModel.putAway(2, loginBean.getId()));
//        if (jsonObject!=null){
//            Log.i("TAG", "putAway: "+jsonObject.toString());
//        }
        viewModel.putAway(2,loginBean.getId()).observe(this,jsonObject -> {
        });

    }

    @Override
    protected void initData() {
        String bean = (String) SpModel.getInstance(App.getInstance(),
                ConfigManage.APP_TABLE).getData(ConfigManage.HOME_MENU, ConfigManage.HOME_MENU);
        loginBean = new Gson().fromJson(bean, LoginBean.class);
        adapter.get().submitList(loginBean.getApp_auth());
        initListener();
    }

}
