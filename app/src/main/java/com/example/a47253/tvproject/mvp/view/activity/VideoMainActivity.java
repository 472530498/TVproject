package com.example.a47253.tvproject.mvp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.a47253.tvproject.R;
import com.example.a47253.tvproject.adapter.GridAdapter;
import com.example.a47253.tvproject.adapter.OnItemClickListener;
import com.example.a47253.tvproject.bean.PosterBean;
import com.example.a47253.tvproject.bean.ResponseBean;
import com.example.a47253.tvproject.bean.VideoBean;
import com.example.a47253.tvproject.retrofit.VideoRequest;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VideoMainActivity extends AppCompatActivity {
    private final static String TAG = "VideoMainActivity";
    List<Object> videoList = new ArrayList<Object>();
    GridAdapter gridAdapter;
    RecyclerView viedioView;
    Gson gson = new Gson();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_layout);
        Bundle bundle = this.getIntent().getExtras();
        String video_zone_tags_name = bundle.getString("video_zone_tags_name");
        viedioView = findViewById(R.id.recyclerViewOPtion);
        gridAdapter = new GridAdapter(videoList);
        RecyclerView.LayoutManager manager = new GridLayoutManager(this, 3);
        manager.setAutoMeasureEnabled(true);
        ((GridLayoutManager) manager).setOrientation(GridLayoutManager.VERTICAL);
        viedioView.setLayoutManager(manager);
        viedioView.setAdapter(gridAdapter);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .connectTimeout(30, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.8:7001/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        VideoRequest service = retrofit.create(VideoRequest.class);
        service.selectVideoAll().enqueue(new Callback<ResponseBean>() {
            @Override
            public void onResponse(Call<ResponseBean> call, Response<ResponseBean> response) {
//                Log.i(TAG, response.body().getResultMsg());
//                Log.i(TAG, response.body().getResultCode());
//                Log.i(TAG, gson.toJson(response.body().getData()));
//                Log.i(TAG, response.body().getData().getClass().getName());
                for (Object datum : (java.util.ArrayList) (response.body().getData())) {
                    Log.i(TAG, datum.getClass().getName());
                    LinkedTreeMap temp = (LinkedTreeMap)datum;
                    PosterBean posterBean = new PosterBean((String) temp.get("video_poster_url"), (String) temp.get("video_name"));
                    videoList.add(new VideoBean(posterBean, (String) temp.get("video_url"),(String) temp.get("video_name"), (String) temp.get("video_zone_tags_name"), (String) temp.get("video_zone_tags_id") ));
                    Log.i("list", videoList.toString());
                    gridAdapter.update(videoList);
                }
            }
            @Override
            public void onFailure(Call<ResponseBean> call, Throwable t) {
                System.out.print("onFailure response.body():");
            }
        });
//        for (int i = 0; i<7; i++) {
//            videoList.add(new PosterBean("","海报名字"));
//        }
//        Log.i("list", videoList.toString());
//        gridAdapter.update(videoList);
        gridAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                Log.i(TAG, postion + "");
                VideoBean videoBean = (VideoBean)videoList.get(postion);
                Log.i(TAG, videoBean.getVideo_zone_tags_id());
                if (videoBean.getVideo_zone_tags_id().equals("499")) {
                    Intent intent = new Intent(VideoMainActivity.this, WebActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("channelUrl",videoBean.getChannelUrl());
                    intent.putExtras(bundle);
                    startActivity(intent);
                    return;
                }
                Intent intent = new Intent(VideoMainActivity.this, GSYVideoPlayerActivity.class);
                Bundle bundle = new Bundle();
//                PosterBean posterBean = (PosterBean)videoList.get(postion);
                bundle.putString("channelUrl",videoBean.getChannelUrl());
                bundle.putString("channelTitle",videoBean.getChannelTitle());
                bundle.putString("posterName",videoBean.getPosterBean().getPosterName());
                bundle.putString("posterUrl", videoBean.getPosterBean().getPosterUrl());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }


//    List<String> list = new ArrayList<String>();
//    List<String> videoList = new ArrayList<String>();
//    RecyclerView viedioView;
//    RecycleViewAdapter recyclerViewAdapter;
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_recycle_layout);
//        viedioView = findViewById(R.id.recyclerViewOPtion);
//        recyclerViewAdapter = new RecycleViewAdapter(this, list);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        viedioView.setLayoutManager(layoutManager);
//        viedioView.setAdapter(recyclerViewAdapter);
//        for (int i = 0; i<10; i++) {
//            list.add(i + "");
//        }
//        Log.i("list", list.toString());
//        recyclerViewAdapter.update(list);
//    }
}
