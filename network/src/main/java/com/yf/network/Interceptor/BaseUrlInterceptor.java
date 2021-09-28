package com.yf.network.Interceptor;

import com.yf.common.tool.ConfigManage;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


/**
 *
 * @author w
 **/
public class BaseUrlInterceptor implements Interceptor {

    @Override
    public @NotNull Response intercept(@NotNull Chain chain) throws IOException {
        //获取request
        Request request = chain.request();
        //获取request的创建者builder
        Request.Builder builder = request.newBuilder();
        //从request中获取headers，通过给定的键url_name
        List<String> headerValues = request.headers("base_url");
        if (headerValues.size() > 0) {
            //如果有这个header，先将配置的header删除，因此header仅用作app和okhttp之间使用
            builder.removeHeader("base_url");
            //匹配获得新的BaseUrl
            String headerValue = headerValues.get(0);
            HttpUrl networkUrl;
            if (ConfigManage.APP_TABLE.equals(headerValue)) {
                networkUrl = HttpUrl.parse(ConfigManage.CLOUD_IP);
            } else {
                networkUrl = HttpUrl.parse(ConfigManage.IP);
            }
            //从request中获取原有的HttpUrl实例oldHttpUrl
            HttpUrl oldHttpUrl = request.url();
            //重建新的HttpUrl，修改需要修改的url部分
            HttpUrl newFullUrl = oldHttpUrl
                    .newBuilder()
                    .scheme(networkUrl.scheme())
                    .host(networkUrl.host())
                    .port(networkUrl.port())
                    .build();
            //重建这个request，通过builder.url(newFullUrl).build()；
            //然后返回一个response至此结束修改
            return chain.proceed(builder.url(newFullUrl).build());
        } else {
            return chain.proceed(request);
        }
    }
}
