package com.yf.warehousewms.model.login.api;

import androidx.lifecycle.MutableLiveData;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.yf.common.bean.LoginBean;
import com.yf.common.bean.VersionBean;
import com.yf.network.NetWorkUtil;
import com.yf.network.callback.RequestCallback;

import java.lang.ref.WeakReference;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * @Author
 * @cerate 2021/9/2 16:01
 **/
public class LoginRepository {
    private static WeakReference<LoginRepository> loginRepository;
    private final LoginApi api;

    public LoginRepository() {
        api = NetWorkUtil.getInstall().createService(LoginApi.class);
    }

    public static LoginRepository getLoginRepository() {
        if (loginRepository == null) {
            synchronized (LoginRepository.class) {
                if (loginRepository == null) {
                    loginRepository = new WeakReference<>(new LoginRepository());
                }
            }
        }
        return loginRepository.get();
    }

    public MutableLiveData<LoginBean> doLogin(String name, String password) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("username", name);
        jsonObject.addProperty("password", password);
        return RequestCallback.doNoRequestHead(api.login(jsonObject));
    }

    public MutableLiveData<VersionBean> getUpdateInfo() {
        return RequestCallback.doNoRequestHead(api.getUpdateInfo());
    }

    public MutableLiveData<JSONObject> webServicesByNPo() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("cust_po","1");
        return RequestCallback.doNoRequestHead(api.webServicesByNPo(jsonObject));
    }
    interface LoginApi {
        /**
         * 登录
         *
         * @param jsonObject 参数
         * @return 用户信息
         */
        @POST("/shYf/sh/android/login")
        Observable<LoginBean> login(@Body JsonObject jsonObject);

        /**
         * 获取apk版本号进行更新
         *
         * @return 版本号
         */
        @GET("/shYf/sh/android/getUpdateInfo")
        Observable<VersionBean> getUpdateInfo();

        @Headers({"base_url:wms"})
        @POST("/a/web/webServicesByNPo")
        Observable<JSONObject> webServicesByNPo(@Body JsonObject jsonObject);

    }
}
