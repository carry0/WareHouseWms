package com.yf.network;

import com.yf.common.tool.ConfigManage;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Author
 * @cerate 2021/9/2 14:09
 **/
public class NetWorkUtil {
    private static NetWorkUtil INSTALL;
    private Retrofit retrofit;

    public NetWorkUtil() {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .callTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
//                .addInterceptor(new BaseUrlInterceptor())
                .build();

        if (retrofit == null) {
            synchronized (NetWorkUtil.class) {
                if (retrofit == null) {
                    retrofit = new Retrofit.Builder()
                            .baseUrl(ConfigManage.IP)
                            .client(httpClient)
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

    public <T> T createService(Class<T> tClass) {
        return retrofit.create(tClass);
    }

}
