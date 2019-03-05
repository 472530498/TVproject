package com.example.a47253.tvproject.retrofit;

import com.example.a47253.tvproject.bean.ResponseBean;


import retrofit2.Call;
import retrofit2.http.GET;

public interface VideoRequest {
        @GET("commonModel/videoManager/selectVideoAll")
        Call<ResponseBean> selectVideoAll();
}
