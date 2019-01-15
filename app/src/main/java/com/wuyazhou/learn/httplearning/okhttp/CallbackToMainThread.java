package com.wuyazhou.learn.httplearning.okhttp;

import android.os.Looper;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author wuyzh
 * @fuction 将返回结果直接回调给UI线程，整个方法都在UI线程内执行
 * */
public class CallbackToMainThread implements Callback {
    private Callback mCallback = null;
    private Call mCall = null;
    private IOException mIOException = null;
    private String mResponse = null;
    public CallbackToMainThread(Callback callback){
        mCallback = callback;
    }
    @Override
    public void onFailure(Call call, IOException e) {
        mCall = call;
        mIOException = e;
        new android.os.Handler(Looper.getMainLooper()).post(runnableFailureUI);
    }

    @Override
    public void onResponse(Call call, Response response) {
        mCall = call;
        try {
            mResponse = response.body().string();
        } catch (IOException e) {
            mIOException = e;
            new android.os.Handler(Looper.getMainLooper()).post(runnableFailureUI);
        }
        new android.os.Handler(Looper.getMainLooper()).post(runnableSuccessUI);
    }
    Runnable runnableFailureUI = new Runnable() {
        @Override
        public void run() {
            mCallback.onFailure(mCall,mIOException);
        }
    };

    Runnable runnableSuccessUI = new Runnable() {
        @Override
        public void run() {
            try {
                mCallback.onResponse(mCall,mResponse);
            } catch (IOException e) {
                mCallback.onFailure(mCall,e);
            }
        }
    };

    public interface Callback {
        void onFailure(Call call, IOException e);
        void onResponse(Call call, String response) throws IOException;
    }
}
