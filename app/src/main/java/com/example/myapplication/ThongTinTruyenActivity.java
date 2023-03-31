package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Adapter.AdapterRecyclerViewBinhLuan;
import com.example.myapplication.Model.BinhLuan;
import com.example.myapplication.Model.Truyen;
import com.example.myapplication.Model.User;
import com.example.myapplication.Ritrofit.BinhLuanRitrofit;
import com.example.myapplication.Ritrofit.UserRitrofit;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ThongTinTruyenActivity extends AppCompatActivity {

    TextView tvTen,tvTacGia,tvNamXb,tvNoiDung;
    ImageView imgAnhBia,imgBanner;
    Button btnDoc,btnLuu;
    RecyclerView rcvBinhLuan;

    EditText cmt;

    String url = "https://mli72h-8080.csb.app/";

    boolean check = false;

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
        btnLuu = findViewById(R.id.ttt_btn_luu);

        cmt = findViewById(R.id.ttt_edt_bl);

        rcvBinhLuan = findViewById(R.id.ttt_rcv_binhluan);

        //Lấy dữ liệu
        SharedPreferences pref = getSharedPreferences("INFOR_TRUYEN", MODE_PRIVATE);
        String sTruyen = pref.getString("TRUYEN","");

        Gson gson = new Gson();
        Truyen obj = gson.fromJson(sTruyen,Truyen.class);

        SharedPreferences prefUser = getSharedPreferences("INFOR_USER", MODE_PRIVATE);
        String sUser = prefUser.getString("USER","");

        Gson gUser = new Gson();
        User objUser = gUser.fromJson(sUser,User.class);

        for (int i = 0; i < objUser.getTruyenyeuthich().length; i++) {
            if (Integer.parseInt(objUser.getTruyenyeuthich()[i]) == obj.getId()){
                btnLuu.setText("Đã Lưu");
                btnLuu.setBackground(ContextCompat.getDrawable(this, R.drawable.custombuttomcheck));
                check = true;
            }
        }

        // Tải dữ liệu lên giao diện
        tvTen.setText(obj.getTen());
        tvTacGia.setText(obj.getTenTG());
        tvNamXb.setText(""+obj.getNamSX());
        tvNoiDung.setText(obj.getMoTa());
        Picasso.get().load(obj.getLinkAnhBia()).into(imgAnhBia);
        Picasso.get().load(obj.getLinkAnhBia()).into(imgBanner);

        btnDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ThongTinTruyenActivity.this,TruyenActivity.class));
            }
        });

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] arr = objUser.getTruyenyeuthich();
                List<String> list = new ArrayList<>(Arrays.asList(arr));
                if (check == true){
                    list.remove(String.valueOf(obj.getId()));
                    btnLuu.setText("Lưu");
                    btnLuu.setBackground(ContextCompat.getDrawable(ThongTinTruyenActivity.this, R.drawable.custombuttom));
                }else {
                    list.add(String.valueOf(obj.getId()));
                    btnLuu.setText("Đã Lưu");
                    btnLuu.setBackground(ContextCompat.getDrawable(ThongTinTruyenActivity.this, R.drawable.custombuttomcheck));
                }
                objUser.setTruyenyeuthich(list.toArray(new String[0]));
                UserRitrofit userRitrofit = new UserRitrofit(ThongTinTruyenActivity.this,url);
                userRitrofit.userUpdate(objUser);
            }
        });

        //btn cmt
        findViewById(R.id.ttt_btn_bl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cmt.getText().toString().isEmpty()){
                    Toast.makeText(ThongTinTruyenActivity.this, "Bạn chưa nhập nội dung", Toast.LENGTH_SHORT).show();
                }else {
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm - DD/MM/yyyy");
                    String ngay = sdf.format(new Date());

                    BinhLuan binhLuan = new BinhLuan(String.valueOf(objUser.getId()),
                            String.valueOf(obj.getId()),
                            cmt.getText().toString(),ngay);

                    BinhLuanRitrofit binhLuanRitrofit = new BinhLuanRitrofit(ThongTinTruyenActivity.this,url);
                    binhLuanRitrofit.BLPortRetrofit(binhLuan,rcvBinhLuan,obj.getId());
                    cmt.setText("");
                }
            }
        });

        // load cmt
        BinhLuanRitrofit binhLuanRitrofit = new BinhLuanRitrofit(this,url);
        binhLuanRitrofit.BinhLuanGetRetrofit(rcvBinhLuan,obj.getId());
    }
}