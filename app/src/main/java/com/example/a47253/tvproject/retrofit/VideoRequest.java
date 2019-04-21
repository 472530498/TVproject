package com.example.a47253.tvproject.retrofit;

import com.example.a47253.tvproject.bean.ResponseBean;
import com.google.gson.JsonObject;


import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface VideoRequest {
        @GET("commonModel/videoManager/selectVideoAll")
        Call<ResponseBean> selectVideoAll();

        @POST("commonModel/userManager/insertUser")
        Call<ResponseBean> register (@Body RequestBody requestBody);

        @POST("commonModel/userManager/login")
        Call<ResponseBean> login (@Body RequestBody requestBody);
}
