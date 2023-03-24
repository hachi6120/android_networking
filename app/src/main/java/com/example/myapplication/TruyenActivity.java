package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.myapplication.Adapter.AdapterRecyclerViewTrangTruyen;

public class TruyenActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truyen);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("...");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getColor(R.color.color_app)));

        recyclerView = findViewById(R.id.truyen_activity_rcv);

        String[] array = new String[]{"https://th.bing.com/th/id/R.64065aa7379b6a1ff358fe316114970f?rik=ZSioEJREMxif0g&pid=ImgRaw&r=0",
                "https://th.bing.com/th/id/R.5fe9bd7644da05c5e9c21d12578e5032?rik=Dwh73atNyrNZpw&pid=ImgRaw&r=0",
                "https://wall.vn/wp-content/uploads/2020/04/anh-dep-viet-nam-18.jpg",
                "https://upanh123.com/wp-content/uploads/2020/11/anh-phong-canh-dep-nhat-the-gioi7.jpg"};

        AdapterRecyclerViewTrangTruyen adapterRecyclerViewTrangTruyen = new AdapterRecyclerViewTrangTruyen();
        adapterRecyclerViewTrangTruyen.setData(array);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapterRecyclerViewTrangTruyen);

    }
}