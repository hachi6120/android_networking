package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.myapplication.Model.User;
import com.example.myapplication.Ritrofit.TruyenRitrofit;
import com.google.gson.Gson;

public class TruyenYeuThich extends AppCompatActivity {

    ExpandableHeightGridView gridView;
    String url = "https://mli72h-8080.csb.app/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truyen_yeu_thich);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Truyện Yêu Thích");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getColor(R.color.color_app)));


        gridView = (ExpandableHeightGridView) findViewById(R.id.truyenyeuthich_gridview);

        SharedPreferences sharedPreferences = getSharedPreferences("INFOR_USER",MODE_PRIVATE);
        String sUser = sharedPreferences.getString("USER","");

        Gson gson = new Gson();
        User user = gson.fromJson(sUser,User.class);

        if (user.getTruyenyeuthich().length == 0){
            Toast.makeText(TruyenYeuThich.this, "Bạn chưa có truyện yêu thích", Toast.LENGTH_SHORT).show();
        }else {
            TruyenRitrofit truyenRitrofit = new TruyenRitrofit(this,url);
            truyenRitrofit.TruyenYeuThich(user,gridView);
        }
    }
}