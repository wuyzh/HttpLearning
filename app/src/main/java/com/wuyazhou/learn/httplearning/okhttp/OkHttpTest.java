package com.wuyazhou.learn.httplearning.okhttp;

import android.widget.TextView;

import com.google.gson.Gson;
import com.wuyazhou.learn.httplearning.volley.JsonModel;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * @author wuyzh
 * */
public class OkHttpTest {

    public static void getRequest(String url,final TextView textView){
        Request.Builder requestBuilder = new Request.Builder().url(url);
        requestBuilder.method("GET",null);
        final Request request = requestBuilder.build();

        OkHttpClient okHttpClient = new OkHttpClient();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new CallbackToMainThread(new CallbackToMainThread.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, String response){
                textView.setText(response);
            }
        }));
    }

    public static void postRequest(String url,final TextView textView){
        FormBody.Builder formBodyBuilder = new FormBody.Builder();
        formBodyBuilder.add("app","phone.get").
                add("phone","13800138000").
                add("appkey","10003").
                add("sign","b59bc3ef6191eb9f747dd4e83c99f2a4").
                add("format","json");

        Request request = new Request.Builder().
                url(url).
                post(formBodyBuilder.build()).
                build();

        OkHttpClient okHttpClient = new OkHttpClient();

        Call call = okHttpClient.newCall(request);

        call.enqueue(new CallbackToMainThread(new CallbackToMainThread.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, String response){
                JsonModel jsonModel = new Gson().fromJson(response,JsonModel.class);
                String result = "success:"+jsonModel.success+"\n"+"status:"+jsonModel.result.status+"\n"+
                        "phone:"+jsonModel.result.phone+"\n"+"area:"+jsonModel.result.area+"\n"+
                        "postNum:"+jsonModel.result.postNum+"\n"+"att:"+jsonModel.result.att+"\n"+
                        "type:"+jsonModel.result.type+"\n"+"par:"+jsonModel.result.par+"\n"+
                        "prefix:"+jsonModel.result.prefix+"\n"+"operators:"+jsonModel.result.operators+"\n"+
                        "styleSimCall:"+jsonModel.result.styleSimCall+"\n"+"styleCityName:"+jsonModel.result.styleCityName;

                textView.setText(result);
            }
        }));
    }
}
