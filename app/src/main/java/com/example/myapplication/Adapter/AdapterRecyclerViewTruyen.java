package com.example.myapplication.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.Truyen;
import com.example.myapplication.R;
import com.example.myapplication.ThongTinTruyenActivity;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterRecyclerViewTruyen extends RecyclerView.Adapter<AdapterRecyclerViewTruyen.TruyenViewHolder> {

    private List<Truyen> truyenList;

    Context activity;
    private int temp = 0;

    public AdapterRecyclerViewTruyen(Context activity) {
        this.activity = activity;
    }

    public void setData(List<Truyen> list){
        this.truyenList = list;
        notifyDataSetChanged();
    }

    @Override
    public TruyenViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_truyen,parent,false);
        return new TruyenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TruyenViewHolder holder, int position) {
        Truyen obj = truyenList.get(position);
        temp++;
        if (truyenList==null){
            return;
        }
        holder.tvName.setText(obj.getTen());
        holder.tvTacGia.setText("Tác Gải: "+obj.getTenTG());
        holder.tvNoiDung.setText(obj.getMoTa());

        Picasso.get().load(obj.getLinkAnhBia()).into(holder.img);

        holder.ln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // chuyển về string
                Gson gson = new Gson();
                String sTruyen = gson.toJson(obj);
                //lưu dữ liệu
                SharedPreferences pref = activity.getSharedPreferences("INFOR_TRUYEN", activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("TRUYEN", sTruyen);
                editor.commit();
                activity.startActivity(new Intent(activity, ThongTinTruyenActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        if (truyenList != null){
            return truyenList.size();
        }
        return 0;
    }

    public class TruyenViewHolder extends RecyclerView.ViewHolder{

        private TextView tvName,tvTacGia,tvNoiDung;
        private ImageView img;

        private LinearLayout ln;

        public TruyenViewHolder(View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.item_rcv_tv_tentruyen);
            tvTacGia = itemView.findViewById(R.id.item_rcv_tv_tacgia);
            tvNoiDung = itemView.findViewById(R.id.item_rcv_tv_noidung);

            img = itemView.findViewById(R.id.item_rcv_img);

            ln = itemView.findViewById(R.id.item_rcv_ln_truyen);
        }
    }
}
