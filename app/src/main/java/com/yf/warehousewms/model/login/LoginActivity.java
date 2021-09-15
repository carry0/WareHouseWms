package com.yf.warehousewms.model.login;

import android.content.Intent;
import android.util.Log;
import android.view.Menu;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.yf.common.base.BaseActivity;
import com.yf.common.tool.ConfigManage;
import com.yf.common.tool.SpModel;
import com.yf.warehousewms.App;
import com.yf.warehousewms.R;
import com.yf.warehousewms.databinding.LoginBinding;
import com.yf.warehousewms.model.home.MenuManageActivity;
import com.yf.warehousewms.model.login.vm.LoginViewModel;


public class LoginActivity extends BaseActivity<LoginBinding> {
    SpModel spModel;
    private LoginViewModel viewModel;

    @Override
    public int setLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        spModel = SpModel.getInstance(App.getInstance(), ConfigManage.APP_TABLE);
        viewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(LoginViewModel.class);
        viewModel.getUpdateInfo().observe(this, versionBean -> {
//            if (!getVersion(App.getInstance()).equals(versionBean.getVersion_no())) {
//                downloadManage(versionBean.getUpdate_url(), this);
//            }
            Log.i("TAG", "initView: " + new Gson().toJson(versionBean));
        });

    }

    @Override
    protected void initListener() {
        binding.loginButton.setOnClickListener(v -> login());
    }


    private void login() {
        viewModel.doLogin("admin", "Ridko2019").observe(this, loginBean -> {
            spModel.putData(ConfigManage.HOME_MENU,new Gson().toJson(loginBean));
            spModel.putData(ConfigManage.USER_NAME, "admin");
            spModel.putData(ConfigManage.USER_PASSWORD, "Ridko2019");
            startActivity(new Intent(LoginActivity.this, MenuManageActivity.class));
            finish();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        spModel = null;
    }
}