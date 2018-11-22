package com.example.a47253.tvproject.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a47253.tvproject.R;

import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> {
    Context context;
    List<String> list;
    private LayoutInflater inflater;

    public RecycleViewAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    public void update(List<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tv_video_list, viewGroup, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        if (myViewHolder.recyclerGridView.getAdapter() == null) {
            myViewHolder.recyclerGridView.setAdapter(new GridAdapter(list));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        RecyclerView recyclerGridView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerGridView = (RecyclerView)itemView.findViewById(R.id.recyclerGridView);
            RecyclerView.LayoutManager manager = new GridLayoutManager(itemView.getContext(), 3);
            manager.setAutoMeasureEnabled(true);
            ((GridLayoutManager) manager).setOrientation(GridLayoutManager.HORIZONTAL);
            recyclerGridView.setLayoutManager(manager);
        }
    }
}
