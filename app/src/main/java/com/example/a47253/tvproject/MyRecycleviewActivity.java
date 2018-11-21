package com.example.a47253.tvproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.a47253.tvproject.adapter.RecycleViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyRecycleviewActivity extends AppCompatActivity {
    List<String> list = new ArrayList<String>();
    List<String> videoList = new ArrayList<String>();
    RecyclerView viedioView;
    RecycleViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_layout);
        viedioView = findViewById(R.id.recyclerViewOPtion);
        recyclerViewAdapter = new RecycleViewAdapter(this, list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        viedioView.setLayoutManager(layoutManager);
        viedioView.setAdapter(recyclerViewAdapter);
        for (int i = 0; i<10; i++) {
            list.add(i + "");
        }
        Log.i("list", list.toString());
        recyclerViewAdapter.update(list);
    }
}
