package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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

    }
}