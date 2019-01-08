package com.wuyazhou.learn.httplearning.volley;

import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.wuyazhou.learn.httplearning.R;

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


    public static void useVolleyImageRequest(String url, RequestQueue queue, final ImageView imageView){
        ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                imageView.setImageBitmap(bitmap);
            }
        }, 0, 0, Bitmap.Config.RGB_565,new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
            }
        });
        queue.add(imageRequest);
    }

    public static void useVolleyImageLoader(String url, RequestQueue queue, final ImageView imageView){
        ImageLoader imageLoader = new ImageLoader(queue, new ImageLoader.ImageCache() {
            @Override
            public Bitmap getBitmap(String url) {
                return null;
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {

            }
        });

        ImageLoader.ImageListener imageListener = ImageLoader.getImageListener(imageView,R.drawable.saierda,R.drawable.saierda);
        imageLoader.get(url,imageListener);
    }

    public static void useVolleyNetWorkImageView(String url, RequestQueue queue, final NetworkImageView networkImageView){
        ImageLoader imageLoader = new ImageLoader(queue, new ImageLoader.ImageCache() {
            @Override
            public Bitmap getBitmap(String url) {
                return null;
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {

            }
        });
        networkImageView.setDefaultImageResId(R.drawable.saierda);
        networkImageView.setErrorImageResId(R.drawable.saierda);
        networkImageView.setImageUrl(url,imageLoader);
    }
}
