package com.wuyazhou.learn.httplearning.okhttp;

import android.graphics.Bitmap;
import android.os.Environment;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.wuyazhou.learn.httplearning.volley.JsonModel;
import com.wuyazhou.learn.logview.LogShowUtil;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * @author wuyzh
 * */
public class OkHttpTest {

    public static void getRequest(OkHttpClient okHttpClient, String url,final TextView textView){
        Request.Builder requestBuilder = new Request.Builder().url(url);
        requestBuilder.method("GET",null);
        final Request request = requestBuilder.build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new CallbackToMainThread(new CallbackToMainThread.Callback<String>() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, String response){
                textView.setText(response);
            }

            @Override
            public String getTClassName() {
                return String.class.getName();
            }
        }));
    }

    public static void postRequest(OkHttpClient okHttpClient, String url,final TextView textView){
        FormBody.Builder formBodyBuilder = new FormBody.Builder();
        formBodyBuilder.add("app","phone.get").
                add("phone","13800138000").
                add("appkey","10003").
                add("sign","b59bc3ef6191eb9f747dd4e83c99f2a4").
                add("format","json");
        RequestBody requestBody = formBodyBuilder.build();

        Request request = new Request.Builder().
                url(url).
                post(requestBody).
                build();

        Call call = okHttpClient.newCall(request);

        call.enqueue(new CallbackToMainThread(new CallbackToMainThread.Callback<String>() {
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

            @Override
            public String getTClassName() {
                return String.class.getName();
            }
        }));
    }

    public static void postFileRequest(OkHttpClient okHttpClient, String url,final TextView textView){
        MediaType mediaType = MediaType.parse("text/x-markdown;  charset=utf-8");
        String filePath = "";
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            filePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        }else {
            return;
        }
        File file = new File(filePath,"wuyazhou.txt");
        Request request = new Request.Builder().
                url(url).
                post(RequestBody.create(mediaType,file)).
                build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new CallbackToMainThread(new CallbackToMainThread.Callback<String>() {
            @Override
            public void onFailure(Call call, IOException e) {
                LogShowUtil.addLog("OkHttp",e.getMessage());
                textView.setText("error");
            }

            @Override
            public void onResponse(Call call, String response) throws IOException {
                textView.setText(response);
            }

            @Override
            public String getTClassName() {
                return String.class.getName();
            }
        }));
    }

    public static void getImageRequest(OkHttpClient okHttpClient, String url, final ImageView imageView){
        Request request = new Request.Builder().url(url).method("GET",null).build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new CallbackToMainThread(new CallbackToMainThread.Callback<Bitmap>() {
            @Override
            public void onFailure(Call call, IOException e) {
                LogShowUtil.addLog("OkHttp",e.getMessage());
            }

            @Override
            public void onResponse(Call call, Bitmap response) throws IOException {
                imageView.setImageBitmap(response);
            }

            @Override
            public String getTClassName() {
                return Bitmap.class.getName();
            }
        }));
    }

    public static void postMultipartRequest(OkHttpClient okHttpClient, String url, final TextView textView){
        MediaType mediaType = MediaType.parse("text/x-markdown;  charset=utf-8");
        String filePath = "";
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            filePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        }else {
            return;
        }
        File file = new File(filePath,"wuyazhou.txt");

        RequestBody requestBody = new MultipartBody.Builder().
                setType(MultipartBody.FORM).
                addFormDataPart("title","wuyazhou").
                addFormDataPart("txt","wuyazhou.txt",RequestBody.create(mediaType,file)).
                build();
        Request request = new Request.Builder().url(url).post(requestBody).build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new CallbackToMainThread(new CallbackToMainThread.Callback<String>() {
            @Override
            public void onFailure(Call call, IOException e) {
                textView.setText("只是一个示例，不会成功的");
            }

            @Override
            public void onResponse(Call call, String response) throws IOException {
                textView.setText(response);
            }

            @Override
            public String getTClassName() {
                return String.class.getName();
            }
        }));


    }
}
