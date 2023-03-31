package com.example.myapplication.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Model.Truyen;
import com.example.myapplication.R;
import com.example.myapplication.ThongTinTruyenActivity;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.List;

public class AdapterGridView extends BaseAdapter {

    private Context context;
    private List<Truyen> objects;
    private LayoutInflater inflater;


    public AdapterGridView(Context context, List<Truyen> objects) {
        this.context = context;
        this.objects = objects;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = new ViewHolder();
        if (view == null) {
            view = inflater.inflate(R.layout.item_gridview_truyen, null);

            holder.tvTenTruyen = view.findViewById(R.id.item_gv_tv_tenTruyen);
            holder.tvTacGia = view.findViewById(R.id.item_gv_tv_tacgia);
            holder.img = view.findViewById(R.id.item_gv_img);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        Truyen obj = objects.get(i);
        holder.tvTenTruyen.setText(obj.getTen());
        holder.tvTacGia.setText("Tác Giả: "+obj.getTenTG());
        Picasso.get().load(obj.getLinkAnhBia()).into(holder.img);

        return view;
    }

    public class ViewHolder {
        public TextView tvTenTruyen,tvTacGia;
        public ImageView img;
    }
}


