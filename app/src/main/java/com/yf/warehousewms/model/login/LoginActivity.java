package com.yf.warehousewms.model.login;

import android.content.Intent;
import android.util.Log;
import androidx.lifecycle.ViewModelProvider;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.yf.common.base.BaseActivity;
import com.yf.common.tool.ConfigManage;
import com.yf.common.tool.SpModel;
import com.yf.warehousewms.App;
import com.yf.warehousewms.R;
import com.yf.warehousewms.databinding.LoginBinding;
import com.yf.warehousewms.model.home.MenuManageActivity;
import com.yf.warehousewms.model.login.vm.LoginViewModel;

@Route(path = "/warehousewms/model/login/LoginActivity")
public class LoginActivity extends BaseActivity<LoginBinding> {
    SpModel spModel;
    private LoginViewModel viewModel;

    @Override
    public int setLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        defaultLoadHelper.getDefaultIml().showBadNetworkView(v -> {
            Logger.d("刷新");
        });
        spModel = SpModel.getInstance(App.getInstance(), ConfigManage.APP_TABLE);
        viewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(LoginViewModel.class);
//        viewModel.getUpdateInfo().observe(this, versionBean -> {
////            if (!getVersion(App.getInstance()).equals(versionBean.getVersion_no())) {
////                downloadManage(versionBean.getUpdate_url(), this);
////            }
//            Log.i("TAG", "initView: " + new Gson().toJson(versionBean));
//        });

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