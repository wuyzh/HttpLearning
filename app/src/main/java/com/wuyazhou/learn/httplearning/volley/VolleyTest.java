package com.wuyazhou.learn.httplearning.volley;

import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import org.json.JSONObject;

/**
 * @author wuyazhou
 * */
public class VolleyTest {
    public static void useVolleyStringRequest(String url, RequestQueue queue, final TextView textView){
        StringRequest mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                textView.setText(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                textView.setText(volleyError.getMessage());
            }
        });
        queue.add(mStringRequest);
    }

    public static void useVolleyJsonObjectRequest(String url,RequestQueue queue, final TextView textView){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("wuyazhouHttp",response.toString());

                JsonModel jsonModel = new Gson().fromJson(response.toString(),JsonModel.class);
                String result = "success:"+jsonModel.success+"\n"+"status:"+jsonModel.result.status+"\n"+
                        "phone:"+jsonModel.result.phone+"\n"+"area:"+jsonModel.result.area+"\n"+
                        "postNum:"+jsonModel.result.postNum+"\n"+"att:"+jsonModel.result.att+"\n"+
                        "type:"+jsonModel.result.type+"\n"+"par:"+jsonModel.result.par+"\n"+
                        "prefix:"+jsonModel.result.prefix+"\n"+"operators:"+jsonModel.result.operators+"\n"+
                        "styleSimCall:"+jsonModel.result.styleSimCall+"\n"+"styleCityName:"+jsonModel.result.styleCityName;
                textView.setText(result);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if (volleyError!=null){
                    Log.d("wuyazhouHttp",volleyError.getMessage()+"");
                }else {
                    Log.d("wuyazhouHttp","不知道啊");
                }
            }

        });
        queue.add(jsonObjectRequest);
    }
}
