package com.example.myapplication.Ritrofit;

import android.content.Context;
import android.util.Log;
import android.widget.GridView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.AdapterGridView;
import com.example.myapplication.Adapter.AdapterRecyclerViewTruyen;
import com.example.myapplication.ExpandableHeightGridView;
import com.example.myapplication.Interface.TruyenInterface;
import com.example.myapplication.Model.Truyen;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TruyenRitrofit {

    Context context;
    String url;
    ExpandableHeightGridView gridView;

    RecyclerView recyclerView;

    int check;


    public TruyenRitrofit(Context context, String url, ExpandableHeightGridView gridView, RecyclerView recyclerView, int check) {
        this.context = context;
        this.url = url;
        this.gridView = gridView;
        this.recyclerView = recyclerView;
        this.check = check;
    }

    public  void TruyenGetRetrofit(){
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        //sử dụng interface
        TruyenInterface userInterface = retrofit.create(TruyenInterface.class);
        Call<List<Truyen>> objCall = userInterface.lay_danh_sach();

        //gọi GET
        objCall.enqueue(new Callback<List<Truyen>>() {
            @Override
            public void onResponse(Call<List<Truyen>> call, retrofit2.Response<List<Truyen>> response) {
                if (response.isSuccessful()){
                    List<Truyen> list = response.body();

                    if (check == 1){
                        AdapterGridView adapterGridView = new AdapterGridView(context,list);
                        gridView.setAdapter(adapterGridView);
                        gridView.setExpanded(true);
                    } else if (check == 2) {
                        AdapterRecyclerViewTruyen adapterRecyclerViewTruyen = new AdapterRecyclerViewTruyen(context);
                        adapterRecyclerViewTruyen.setData(list);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context,RecyclerView.VERTICAL,false);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setAdapter(adapterRecyclerViewTruyen);
                    }


                    /*Log.d("TAG", "onResponse: size: "+list.size());*/
                    // tự sử lý list
                }else {
                    Log.d("TAG", "onResponse: Lỗi: "+response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<Truyen>> call, Throwable t) {
                Log.e("TAG E", "onFailure: "+t.getMessage() );
            }
        });
    }

    /*void TruyenPostRetrofit(){
        //Tạo đối trượng chuyển đổi

        Gson gson = new GsonBuilder().setLenient().create();

        //Tạo Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://63db6922a3ac95cec5a10e24.mockapi.io/demo-api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        //Khởi tạo Interface
        TruyenInterface userInterface = retrofit.create(TruyenInterface.class);

        //Tạo đối tượng DTO để gửi lên API
        Truyen userDTO = new Truyen();

        //Tạo Call
        Call<Truyen> objCall = userInterface.them_moi_user(userDTO);

        //Thực hiện gửi dữ liệu lên server
        objCall.enqueue(new Callback<Truyen>() {
            @Override
            public void onResponse(Call<Truyen> call, retrofit2.Response<Truyen> response) {
                if (response.isSuccessful()){
                    Truyen obj = response.body();
                    Log.d("TAG", "onResponse: Kết Quả: ");
                }else {
                    Log.d("TAG", "onResponse: Lỗi "+response.message());
                }
            }

            @Override
            public void onFailure(Call<Truyen> call, Throwable t) {
                Log.e("TAG", "onFailure: "+t.getMessage());
            }
        });
    }*/
}
