package com.yf.warehousewms.model.login;

import android.util.Log;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.yf.common.base.BaseActivity;
import com.yf.common.tool.SpModel;
import com.yf.warehousewms.App;
import com.yf.warehousewms.R;
import com.yf.warehousewms.databinding.LoginBinding;
import com.yf.warehousewms.model.login.vm.LoginViewModel;

import static com.yf.common.tool.ConfigManage.APP_TABLE;
import static com.yf.common.tool.ConfigManage.USER_NAME;
import static com.yf.common.tool.ConfigManage.USER_PASSWORD;

public class LoginActivity extends BaseActivity<LoginBinding> {
    SpModel spModel;
    private LoginViewModel viewModel;

    @Override
    public int setLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        viewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(LoginViewModel.class);
        spModel = SpModel.getInstance(App.getInstance(), APP_TABLE);

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
            spModel.putData(USER_NAME, binding.etName.getText().toString());
            spModel.putData(USER_PASSWORD, binding.etPassword.getText().toString());
        });
    }
}