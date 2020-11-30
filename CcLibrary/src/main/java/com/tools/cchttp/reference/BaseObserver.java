package com.tools.cchttp.reference;


import android.util.Log;

import com.tools.cchttp.RxExceptionUtil;
import com.tools.cchttp.bean.Response;


import java.net.SocketTimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author AlienChao
 * @date 2019/08/05 10:27
 */
public abstract class BaseObserver<T> implements Observer<Response<T>> {


    @Override
    public void onNext(Response<T> tResponse) {
        if (tResponse.getERROR_CODE() == 0) {
            onSuccess(tResponse.getRESPONSE_DATA());
        } else {
            onCodeError(tResponse.getERROR_CODE(),tResponse.getERROR_MSG());
            onError(tResponse.getERROR_MSG());
        }
    }

    @Override
    public void onError(Throwable e) {
        onError(RxExceptionUtil.exceptionHandler(e));


        if (e instanceof SocketTimeoutException) {
            onErrorException(e);

        }

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    public abstract void onSuccess(T t);


    protected void onError(String errorStr) {
        Log.e("jsc", "BaseObserver-onError:");
    }

    /** 暂时仅超时调用 */
    protected void onErrorException(Throwable e) {

    }


    protected void onCodeError(int code, String errorStr) {

    }

}
