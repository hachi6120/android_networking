package com.example.myapplication.Adapter;

import android.app.Activity;
import android.content.Intent;
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

import java.util.List;

public class AdapterRecyclerViewTruyen extends RecyclerView.Adapter<AdapterRecyclerViewTruyen.TruyenViewHolder> {

    private List<Truyen> truyenList;

    Activity activity;
    private int temp = 0;

    public AdapterRecyclerViewTruyen(Activity activity) {
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

        if (temp == 1){
            holder.ln.setBackgroundResource(R.drawable.custom_item_truyen);
        } else if (temp == 2) {
            holder.ln.setBackgroundResource(R.drawable.custom_item_truyen_b);
        }else if (temp == 3) {
            holder.ln.setBackgroundResource(R.drawable.custom_item_truyen_a);
            temp = 0;
        }

        holder.ln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
