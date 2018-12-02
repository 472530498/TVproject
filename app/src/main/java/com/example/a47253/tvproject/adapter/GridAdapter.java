package com.example.a47253.tvproject.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a47253.tvproject.R;
import com.example.a47253.tvproject.bean.PosterBean;

import java.io.FileInputStream;
import java.util.List;

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder>{
    List<Object> list;

    public GridAdapter(List<Object> list) {
        this.list = list;
    }

    public void update(List<Object> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull

    @Override
    public GridAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tv_video_grid, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GridAdapter.ViewHolder viewHolder, int i) {
        PosterBean object = (PosterBean)list.get(i);
        viewHolder.poster_name.setText(object.getPosterName());
        FileInputStream in = null;
        Bitmap bitmap = BitmapFactory.decodeResource(viewHolder.itemView.getResources(), R.drawable.poster);
        viewHolder.poster.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView poster_name;
        public final AppCompatImageView poster;

        public ViewHolder(View itemView) {
            super(itemView);
            poster_name= (TextView) itemView.findViewById(R.id.poster_name);
            poster = (AppCompatImageView)itemView.findViewById(R.id.poster);
        }
    }
}
