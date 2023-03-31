package com.example.myapplication.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.Ritrofit.UserRitrofit;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Arrays;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    EditText edtUserName, edtPass;
    public TextInputLayout tilUserName, tilPass;

    Button btnLogin;

    String url = "https://mli72h-8080.csb.app/";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setTitle("Login");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getColor(R.color.color_app)));


        edtUserName = findViewById(R.id.login_edt_username);
        edtPass = findViewById(R.id.login_edt_pass);

        tilUserName = findViewById(R.id.login_til_username);
        tilPass = findViewById(R.id.login_til_pass);

        btnLogin = findViewById(R.id.login_btn_dn);

        findViewById(R.id.login_tv_dk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int temp = 0;
                if (edtUserName.getText().toString().isEmpty()){
                    tilUserName.setError("Tài khoản không được để trống");
                    temp++;
                }else {
                    tilUserName.setError("");
                }
                if (edtPass.getText().toString().isEmpty()){
                    tilPass.setError("Mật khẩu không được để trống");
                    temp++;
                }else {
                    tilPass.setError("");
                }

                if (temp == 0){
                    UserRitrofit userRitrofit = new UserRitrofit(LoginActivity.this,url);
                    userRitrofit.UserGetRetrofit(edtUserName.getText().toString(),edtPass.getText().toString(),LoginActivity.this);
                }
            }
        });

    }
}