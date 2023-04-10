package com.example.myapplication.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.Model.User;
import com.example.myapplication.R;
import com.example.myapplication.Ritrofit.UserRitrofit;
import com.example.myapplication.ThongTinTruyenActivity;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

public class PasswordActivity extends AppCompatActivity {

    EditText edtMkc, edtMkm, edtMkr;
    TextInputLayout tilMkc, tilMkm, tilMkr;
    Button btn;
    String url = "https://mli72h-8080.csb.app/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Đổi mật khẩu");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getColor(R.color.color_app)));

        edtMkc = findViewById(R.id.dmk_edt_mkc);
        edtMkm = findViewById(R.id.dmk_edt_pass);
        edtMkr = findViewById(R.id.dmk_edt_repass);

        tilMkc = findViewById(R.id.dmk_til_mkc);
        tilMkm = findViewById(R.id.dmk_til_pass);
        tilMkr = findViewById(R.id.dmk_til_repass);

        btn = findViewById(R.id.dmk_btn_dmk);

        SharedPreferences prefUser = getSharedPreferences("INFOR_USER", MODE_PRIVATE);
        String sUser = prefUser.getString("USER","");

        Gson gUser = new Gson();
        User objUser = gUser.fromJson(sUser,User.class);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int temp = 0;
                if (edtMkc.getText().toString().length()<=5){
                    tilMkc.setError("Mật khẩu cũ không đúng");
                    temp++;
                }else {
                    if (!edtMkc.getText().toString().equals(objUser.getPassword())){
                        tilMkc.setError("Mật khẩu cũ không đúng");
                        temp++;
                    }else {
                        tilMkc.setError("");
                    }
                }
                if (edtMkm.getText().toString().length()<=5){
                    tilMkm.setError("Mật khẩu mới phải > 5 ký tự");
                    temp++;
                }else {
                    tilMkm.setError("");
                }
                if (edtMkr.getText().toString().length()<=5){
                    tilMkr.setError("Nhập lại mật khẩu phải > 5 ký tự");
                    temp++;
                }else {
                    tilMkr.setError("");
                }

                if (temp==0){
                    if (!edtMkm.getText().toString().equals(edtMkr.getText().toString())){
                        tilMkr.setError("Mật khẩu không trùng khớp");
                        tilMkm.setError("Mật khẩu không trùng khớp");
                    }else {
                        tilMkm.setError("");
                        tilMkr.setError("");
                        objUser.setPassword(edtMkm.getText().toString());
                        UserRitrofit userRitrofit = new UserRitrofit(PasswordActivity.this,url);
                        userRitrofit.userDoiMK(objUser);
                    }
                }
            }
        });
    }
}