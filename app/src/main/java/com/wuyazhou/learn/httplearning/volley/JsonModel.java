package com.wuyazhou.learn.httplearning.volley;

import com.google.gson.annotations.SerializedName;

/**
 * @author wuyzh
 * @function json数据解析使用
 * */
public class JsonModel {
    public int success;
    public Result result;
    
    public class Result{
        public String status;
        public String phone;
        public String area;
        @SerializedName("postno")
        public String postNum;
        public String att;
        @SerializedName("ctype")
        public String type;
        public String par;
        public String prefix;
        public String operators;
        @SerializedName("style_simcall")
        public String styleSimCall;
        @SerializedName("style_citynm")
        public String styleCityName;
    }
}
