package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import com.example.myapplication.Adapter.AdapterRecyclerViewTrangTruyen;
import com.example.myapplication.Model.Truyen;
import com.google.gson.Gson;

public class TruyenActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truyen);

        // Lấy dữ liệu
        SharedPreferences pref = getSharedPreferences("INFOR_TRUYEN", MODE_PRIVATE);
        String sTruyen = pref.getString("TRUYEN","");

        Gson gson = new Gson();
        Truyen obj = gson.fromJson(sTruyen,Truyen.class);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //set title truyện
        getSupportActionBar().setTitle(obj.getTen());
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getColor(R.color.color_app)));

        recyclerView = findViewById(R.id.truyen_activity_rcv);
        Log.e("TAG", "onCreate: "+obj.getLinkAnhTruyen()[0]);

        AdapterRecyclerViewTrangTruyen adapterRecyclerViewTrangTruyen = new AdapterRecyclerViewTrangTruyen();
        //set data cho adapter
        adapterRecyclerViewTrangTruyen.setData(obj.getLinkAnhTruyen());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapterRecyclerViewTrangTruyen);

    }
}