package com.yf.network;

import com.yf.common.tool.ConfigManage;
import com.yf.network.Interceptor.AddSessionIdInterceptor;
import com.yf.network.Interceptor.BaseUrlInterceptor;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author  l
 **/
public class NetWorkUtil {
    private static NetWorkUtil INSTALL;
    private Retrofit retrofit;

    public NetWorkUtil() {
        if (retrofit == null) {
            synchronized (NetWorkUtil.class) {
                if (retrofit == null) {
                    retrofit = new Retrofit.Builder()
                            .client(httpClient())
                            .baseUrl(ConfigManage.IP)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .build();
                }
            }
        }
    }

    public static NetWorkUtil getInstall() {
        if (INSTALL == null) {
            synchronized (NetWorkUtil.class) {
                if (INSTALL == null) {
                    INSTALL = new NetWorkUtil();
                }
            }
        }
        return INSTALL;
    }

    private @NotNull OkHttpClient httpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .callTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                //切换网络URL拦截器
                .addInterceptor(new BaseUrlInterceptor())
                //保存sessionId拦截器
                .addInterceptor(new AddSessionIdInterceptor())
                //输出log日志格式
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
    }

    public <T> T createService(Class<T> tClass) {
        return retrofit.create(tClass);
    }

}
