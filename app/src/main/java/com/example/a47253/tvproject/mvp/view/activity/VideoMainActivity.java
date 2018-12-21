package com.example.a47253.tvproject.mvp.view.activity;

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

import java.util.ArrayList;
import java.util.List;

public class VideoMainActivity extends AppCompatActivity {
    private final static String TAG = "VideoMainActivity";
    List<Object> videoList = new ArrayList<Object>();
    GridAdapter gridAdapter;
    RecyclerView viedioView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_layout);
        viedioView = findViewById(R.id.recyclerViewOPtion);
        gridAdapter = new GridAdapter(videoList);
        RecyclerView.LayoutManager manager = new GridLayoutManager(this, 3);
        manager.setAutoMeasureEnabled(true);
        ((GridLayoutManager) manager).setOrientation(GridLayoutManager.VERTICAL);
        viedioView.setLayoutManager(manager);
        viedioView.setAdapter(gridAdapter);
        for (int i = 0; i<7; i++) {
            videoList.add(new PosterBean("","海报名字"));
        }
        Log.i("list", videoList.toString());
        gridAdapter.update(videoList);
        gridAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                Log.i(TAG, postion + "");
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
