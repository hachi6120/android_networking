package com.example.myapplication.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.Truyen;
import com.example.myapplication.R;
import com.example.myapplication.ThongTinTruyenActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterRecyclerViewTrangTruyen extends RecyclerView.Adapter<AdapterRecyclerViewTrangTruyen.ViewHolder> {

    private String[] truyenList;

    public void setData(String[] list){
        this.truyenList = list;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_trang_truyen,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (truyenList==null){
            return;
        }
        Picasso.get().load(truyenList[position]).into(holder.img);
    }

    @Override
    public int getItemCount() {
        if (truyenList != null){
            return truyenList.length;
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView img;
        public ViewHolder(View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.item_rcv_trang_truyen_img);
        }
    }
}
