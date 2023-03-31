package com.example.myapplication.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.Model.User;
import com.example.myapplication.R;
import com.example.myapplication.Ritrofit.UserRitrofit;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity {

    EditText edtUsername, edtPassword, edtRePassword, edtEmail, edtHoten;
    public TextInputLayout tilUsername,tilPassword, tilRePassword, tilEmail, tilHoten;

    Button btnDk;

    String url = "https://mli72h-8080.csb.app/";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Đăng Ký");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getColor(R.color.color_app)));

        edtUsername = findViewById(R.id.dk_edt_username);
        edtPassword = findViewById(R.id.dk_edt_pass);
        edtRePassword = findViewById(R.id.dk_edt_repass);
        edtEmail = findViewById(R.id.dk_edt_email);
        edtHoten = findViewById(R.id.dk_edt_name);

        tilUsername = findViewById(R.id.dk_til_username);
        tilPassword = findViewById(R.id.dk_til_pass);
        tilRePassword = findViewById(R.id.dk_til_repass);
        tilEmail = findViewById(R.id.dk_til_email);
        tilHoten = findViewById(R.id.dk_til_name);

        btnDk = findViewById(R.id.dk_btn_dk);

        btnDk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int temp = 0;
                if (edtEmail.getText().toString().isEmpty()){
                    tilEmail.setError("Email không được để trống");
                    temp++;
                }else {
                    if(!isEmailValid(edtEmail.getText().toString())){
                        tilEmail.setError("Email không đúng");
                        temp++;
                    }else {
                        tilEmail.setError("");
                    }
                }
                if (edtUsername.getText().toString().isEmpty()){
                    tilUsername.setError("Tài khoản không được để trống");
                    temp++;
                }else {
                    tilUsername.setError("");
                }
                if (edtHoten.getText().toString().isEmpty()){
                    tilHoten.setError("Họ tên không được để trống");
                    temp++;
                }else {
                    tilHoten.setError("");
                }
                if (edtPassword.getText().toString().isEmpty()){
                    tilPassword.setError("Mật khẩu không được để trống");
                    temp++;
                }else {
                    tilPassword.setError("");
                }
                if (edtRePassword.getText().toString().isEmpty()){
                    tilRePassword.setError("Nhập lại mật khẩu không được để trống");
                    temp++;
                }else {
                    tilRePassword.setError("");
                }
                if (temp == 0){
                    if (!edtRePassword.getText().toString().equals(edtPassword.getText().toString())){
                        tilPassword.setError("Mật Khẩu không trùng khớp");
                        tilRePassword.setError("Mật Khẩu không trùng khớp");
                    }else {
                        tilPassword.setError("");
                        tilRePassword.setError("");
                        User user = new User(edtUsername.getText().toString(),
                                edtPassword.getText().toString(),
                                edtEmail.getText().toString(),
                                edtHoten.getText().toString(),
                                new String[]{});
                        UserRitrofit userRitrofit = new UserRitrofit(RegisterActivity.this,url);
                        userRitrofit.checktk(edtUsername.getText().toString(),RegisterActivity.this,user);
                    }
                }

            }
        });
    }
    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}