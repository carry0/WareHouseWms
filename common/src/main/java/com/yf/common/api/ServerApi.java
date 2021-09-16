package com.yf.common.api;

import com.alibaba.fastjson.JSONObject;
import com.yf.common.bean.Carrier;


import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @Author
 * @cerate 2021/9/16 10:10
 **/
public interface ServerApi {
    /**
     * 上架
     * @param map
     * @return
     */
    @POST("/shYf/sh/input/pushInput.sh")
    Observable<JSONObject> putAway(@Body Map<String,Object> map);
    /**
     * 根据epc获取库位信息
     * @param jsonObject
     * @return
     */
    @POST("/shYf/sh/count/getCarrier.sh")
    Observable<Carrier> getCarrier(@Body String jsonObject);
}
