package com.yf.network.callback;



import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.yf.common.base.BaseRequest;
import com.yf.common.bean.LoginBean;
import com.yf.common.tool.ConfigManage;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


/**
 * @Author
 * @cerate 2021/9/2 14:39
 **/
public class RequestCallback {

    @SuppressLint("CheckResult")
    public static <T> MutableLiveData<T> doRequest(Observable<BaseRequest<T>> observable) {
        MutableLiveData<T> data = new MutableLiveData<>();
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<BaseRequest<T>>() {

                    @Override
                    public void onNext(@NonNull BaseRequest<T> result) {
                        if (result.getCode() == ConfigManage.CODE_SUCCESS) {
                            data.postValue(result.getData());
                        } else {
                            data.postValue((T) new BaseRequest(result.getCode(), result.getMessage()));
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return data;
    }
    @SuppressLint("CheckResult")
    public static <T> MutableLiveData<T> doNoRequestHead(Observable<T> observable) {
        MutableLiveData<T> data = new MutableLiveData<>();
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<T>() {

                    @Override
                    public void onNext(@NonNull T result) {
                        if(result instanceof LoginBean){
                            if (((LoginBean) result).getCode()==0){
                                data.postValue(result);
                            }else {
                                data.postValue((T) new LoginBean(((LoginBean) result).getMsg(),((LoginBean) result).getCode(),-1,""));
                            }
                        }else {
                            data.postValue(result);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i("TAG", "onError: "+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return data;
    }
    public interface Callback<T> {
        /**
         * 成功回调
         * @param data 成功数据
         */
        void onSuccess(T data);

        /**
         * 错位回调
         * @param throwable 错误信息
         */
        void onError(Throwable throwable);
    }
}
