package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.BinhLuan;

import java.util.List;

public class AdapterRecyclerViewBinhLuan extends RecyclerView.Adapter<AdapterRecyclerViewBinhLuan.ViewHolder> {

    private List<BinhLuan> list;

    public void setData(List<BinhLuan> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_binh_luan,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        BinhLuan obj = list.get(position);
        if (list==null){
            return;
        }
        holder.tvName.setText(""+obj.getIdUser());
        holder.tvNgay.setText(""+obj.getNgay());
        holder.tvNoiDung.setText(obj.getCmt());

    }

    @Override
    public int getItemCount() {
        if (list != null){
            return list.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvName,tvNgay,tvNoiDung;

        public ViewHolder(View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.item_rcv_bl_tv_ten);
            tvNgay = itemView.findViewById(R.id.item_rcv_bl_tv_ngay);
            tvNoiDung = itemView.findViewById(R.id.item_rcv_bl_tv_cmt);
        }
    }
}
