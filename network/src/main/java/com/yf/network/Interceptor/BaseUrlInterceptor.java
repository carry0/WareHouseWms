package com.yf.network.Interceptor;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Author
 * @cerate 2021/9/2 12:24
 **/
public class BaseUrlInterceptor implements Interceptor {

    @Override
    public @NotNull Response intercept(@NotNull Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl oldUrl = request.url();
        Request.Builder newBuilder = request.newBuilder();
        List<String> headers = request.headers("");
        if (headers.size() > 0) {
            newBuilder.removeHeader("");
            String get = headers.get(0);
            HttpUrl networkUrl;
            if (get.equals("0")){
                networkUrl = HttpUrl.parse("ss");
            }else {
                networkUrl = oldUrl;
                return chain.proceed(newBuilder.url(networkUrl).build());
            }
            //从request中获取原有的HttpUrl实例oldUrl
            //重建新的HttpUrl，修改需要修改的url部分
            HttpUrl newFullUrl = oldUrl
                    .newBuilder()
                    .scheme(networkUrl.scheme())
                    .host(networkUrl.host())
                    .port(networkUrl.port())
                    .build();
            //重建这个request，通过builder.url(newFullUrl).build()；
            //然后返回一个response至此结束修改
            return chain.proceed(newBuilder.url(newFullUrl).build());
        }
        return chain.proceed(request);
    }
}
