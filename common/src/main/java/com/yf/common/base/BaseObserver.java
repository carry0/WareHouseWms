package com.yf.common.base;


import androidx.lifecycle.Observer;

import com.yf.common.AppCommon;
import com.yf.common.R;
import com.yf.common.tool.ConfigManage;

/**
 * @Author
 * @cerate 2021/9/29 12:26
 **/
public abstract class BaseObserver<T> implements Observer<BaseRequest<T>> {
    @Override
    public void onChanged(BaseRequest<T> tBaseRequest) {
        if (tBaseRequest !=null){
            if (tBaseRequest.getCode()== ConfigManage.CODE_SUCCESS){
                onSuccess((T)tBaseRequest.getData());
            }else {
                onError(tBaseRequest.getCode(),tBaseRequest.getMessage());
            }
        }else {
            onError(ConfigManage.CODE_ERROR, AppCommon.getInstance().getString(R.string.error_message));
        }
    }

    /**
     * 失败
     * @param code 状态码
     * @param message 错误信息
     */
    protected abstract void onError(int code, String message);


    /**
     * 成功返回
     * @param data T
     */
    protected abstract void onSuccess(T data);
}
