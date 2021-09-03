package com.yf.warehousewms.model.login.vm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.yf.common.bean.LoginBean;
import com.yf.common.bean.VersionBean;
import com.yf.warehousewms.model.login.api.LoginRepository;

/**
 * @Author
 * @cerate 2021/9/2 15:20
 **/
public class LoginViewModel extends AndroidViewModel {
    private final LoginRepository repository;
    /**
     * 用户名
     */
    private final MutableLiveData<String> name = new MutableLiveData<>();
    /**
     * 密码
     */
    private final MutableLiveData<String> password = new MutableLiveData<>();
    /**
     * 记住密码
     */
    private final MutableLiveData<Boolean> hisCheck = new MutableLiveData<>();

    public LoginViewModel(@NonNull Application application) {
        super(application);
        repository = LoginRepository.getLoginRepository();

    }

    public MutableLiveData<Boolean> getHisCheck() {
        return hisCheck;
    }

    public MutableLiveData<String> getPassword() {
        return password;
    }

    public MutableLiveData<String> getName() {
        return name;
    }

    public MutableLiveData<VersionBean> getUpdateInfo() {
        return repository.getUpdateInfo();
    }

    public MutableLiveData<LoginBean> doLogin(String name, String password) {
        return repository.doLogin(name, password);
    }
}
