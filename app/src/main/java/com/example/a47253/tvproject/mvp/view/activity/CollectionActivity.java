package com.example.a47253.tvproject.mvp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.a47253.tvproject.R;
import com.example.a47253.tvproject.adapter.GridAdapter;
import com.example.a47253.tvproject.adapter.OnItemClickListener;
import com.example.a47253.tvproject.bean.PosterBean;
import com.example.a47253.tvproject.bean.ResponseBean;
import com.example.a47253.tvproject.bean.VideoBean;
import com.example.a47253.tvproject.retrofit.VideoRequest;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CollectionActivity extends AppCompatActivity {
    private final static String TAG = "CollectionActivity";
    List<Object> videoList = new ArrayList<Object>();
    GridAdapter gridAdapter;
    RecyclerView viedioView;
    Gson gson = new Gson();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_layout);
        Bundle bundle = this.getIntent().getExtras();
//        String video_zone_tags_name = bundle.getString("video_zone_tags_name");
        viedioView = findViewById(R.id.recyclerViewOPtion);
        gridAdapter = new GridAdapter(videoList);
        RecyclerView.LayoutManager manager = new GridLayoutManager(this, 3);
        manager.setAutoMeasureEnabled(true);
        ((GridLayoutManager) manager).setOrientation(GridLayoutManager.VERTICAL);
        viedioView.setLayoutManager(manager);
        viedioView.setAdapter(gridAdapter);
        SharedPreferences sharedPreferences = this.getSharedPreferences("collection", Context.MODE_PRIVATE);
        HashSet outData = (HashSet)sharedPreferences.getStringSet("videoCollection", new HashSet<>());
        for (Object datum : outData) {
            Log.i(TAG, datum.getClass().getName());
            VideoBean videoBean = gson.fromJson((String)datum, VideoBean.class);
//            LinkedTreeMap temp = (LinkedTreeMap)datum;
//            PosterBean posterBean = new PosterBean((String) temp.get("video_poster_url"), (String) temp.get("video_name"));
            videoList.add(videoBean);
            Log.i("list", videoList.toString());
            gridAdapter.update(videoList);
        }
        gridAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                Log.i(TAG, postion + "");
                VideoBean videoBean = (VideoBean)videoList.get(postion);
                Log.i(TAG, videoBean.getVideo_zone_tags_id());
                if (videoBean.getVideo_zone_tags_id().equals("499")) {
                    Intent intent = new Intent(CollectionActivity.this, WebActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("channelUrl",videoBean.getChannelUrl());
                    intent.putExtras(bundle);
                    startActivity(intent);
                    return;
                }
                Intent intent = new Intent(CollectionActivity.this, GSYVideoPlayerActivity.class);
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
}
