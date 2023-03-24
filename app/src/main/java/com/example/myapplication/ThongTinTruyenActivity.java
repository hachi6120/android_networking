package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.Model.BinhLuan;

import java.util.ArrayList;
import java.util.List;

public class ThongTinTruyenActivity extends AppCompatActivity {

    TextView tvTen,tvTacGia,tvNamXb,tvNoiDung;
    ImageView imgAnhBia,imgBanner;
    Button btnDoc;
    RecyclerView rcvBinhLuan;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_truyen);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Thông Tin Truyện");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getColor(R.color.color_app)));

        tvTen = findViewById(R.id.ttt_tv_tentruyen);
        tvTacGia = findViewById(R.id.ttt_tv_tacgia);
        tvNamXb = findViewById(R.id.ttt_tv_namxb);
        tvNoiDung = findViewById(R.id.ttt_tv_noidung);

        imgAnhBia = findViewById(R.id.ttt_img_bia);
        imgBanner = findViewById(R.id.ttt_img_banner);

        btnDoc = findViewById(R.id.ttt_btn_doc);

        rcvBinhLuan = findViewById(R.id.ttt_rcv_binhluan);

        btnDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ThongTinTruyenActivity.this,TruyenActivity.class));
            }
        });

        List<BinhLuan> list = new ArrayList<>();
        list.add(new BinhLuan(1,4,"Truyện hay, hấp dẫn","26/02/2022"));
        list.add(new BinhLuan(2,15,"Truyện hay, hấp dẫn","05/02/2022"));
        list.add(new BinhLuan(3,41,"Truyện hay, hấp dẫn","22/02/2022"));
        list.add(new BinhLuan(4,15,"Truyện hay, hấp dẫn","05/02/2022"));
        list.add(new BinhLuan(5,71,"Truyện hay, hấp dẫn","14/02/2022"));
        list.add(new BinhLuan(6,15,"Truyện hay, hấp dẫn","15/12/2022"));

        AdapterRecyclerViewBinhLuan adapterRecyclerViewBinhLuan = new AdapterRecyclerViewBinhLuan();
        adapterRecyclerViewBinhLuan.setData(list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rcvBinhLuan.setLayoutManager(linearLayoutManager);
        rcvBinhLuan.setAdapter(adapterRecyclerViewBinhLuan);

    }
}