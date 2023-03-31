package com.example.myapplication.Ritrofit;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.AdapterGridView;
import com.example.myapplication.Adapter.AdapterRecyclerViewTruyen;
import com.example.myapplication.ExpandableHeightGridView;
import com.example.myapplication.Interface.ClassInterface;
import com.example.myapplication.Login.LoginActivity;
import com.example.myapplication.Model.BinhLuan;
import com.example.myapplication.Model.Truyen;
import com.example.myapplication.Model.User;
import com.example.myapplication.ThongTinTruyenActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BinhLuanRitrofit {

    Context context;
    String url;


    public BinhLuanRitrofit(Context context, String url) {
        this.context = context;
        this.url = url;
    }

    public void BinhLuanGetRetrofit(RecyclerView recyclerView, int idTruyen) {
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        //sử dụng interface
        ClassInterface userInterface = retrofit.create(ClassInterface.class);
        Call<List<BinhLuan>> objCall = userInterface.list_cmt(idTruyen);

        //gọi GET
        objCall.enqueue(new Callback<List<BinhLuan>>() {
            @Override
            public void onResponse(Call<List<BinhLuan>> call, retrofit2.Response<List<BinhLuan>> response) {
                if (response.isSuccessful()) {
                    List<BinhLuan> list = response.body();
                    UserRitrofit userRitrofit = new UserRitrofit(context,url);
                    userRitrofit.BinhLuanUser(recyclerView,list);
                    // tự sử lý list
                } else {
                    Log.d("TAG", "onResponse: Lỗi: " + response.errorBody());
                }
            }
            @Override
            public void onFailure(Call<List<BinhLuan>> call, Throwable t) {
                Log.e("TAG E", "onFailure: " + t.getMessage());
            }
        });
    }

    public void BLPortRetrofit(BinhLuan binhLuan, RecyclerView recyclerView, int idTruyen){
        ProgressDialog dialog = ProgressDialog.show(context,"","Loading...");
        Gson gson = new GsonBuilder().setLenient().create();

        //Tạo Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        //Khởi tạo Interface
        ClassInterface userInterface = retrofit.create(ClassInterface.class);

        //Tạo Call
        Call<BinhLuan> objCall = userInterface.add_cmt(binhLuan);

        //Thực hiện gửi dữ liệu lên server
        objCall.enqueue(new Callback<BinhLuan>() {
            @Override
            public void onResponse(Call<BinhLuan> call, retrofit2.Response<BinhLuan> response) {
                dialog.dismiss();
                if (response.isSuccessful()){
                    BinhLuan obj = response.body();
                    BinhLuanGetRetrofit(recyclerView,idTruyen);
                    Log.d("TAG", "onResponse: Kết Quả: "+obj.toString());
                }else {
                    Log.d("TAG", "onResponse: Lỗi "+response.message());
                }
            }

            @Override
            public void onFailure(Call<BinhLuan> call, Throwable t) {
                dialog.dismiss();
                Log.e("TAG", "onFailure: "+t.getMessage());
            }
        });
    }
}
