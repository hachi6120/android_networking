package com.example.myapplication.Ritrofit;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.AdapterRecyclerViewBinhLuan;
import com.example.myapplication.Interface.ClassInterface;
import com.example.myapplication.Login.LoginActivity;
import com.example.myapplication.Login.RegisterActivity;
import com.example.myapplication.MainActivity;
import com.example.myapplication.Model.BinhLuan;
import com.example.myapplication.Model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserRitrofit {

    Context context;
    String url;


    public UserRitrofit(Context context, String url) {
        this.context = context;
        this.url = url;
    }

    public  void UserGetRetrofit(String username, String pass, LoginActivity activity){
        ProgressDialog dialog = ProgressDialog.show(context,"","Waiting for login...");
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        //sử dụng interface
        ClassInterface userInterface = retrofit.create(ClassInterface.class);

        Call<List<User>> objCall = userInterface.lay_user(username,pass);

        //gọi GET
        objCall.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, retrofit2.Response<List<User>> response) {
                dialog.dismiss();
                if (response.isSuccessful()){
                    List<User> list = response.body();
                    if (list.size() == 0){
                        activity.tilUserName.setError("Tài khoản hoặc mật khẩu không đúng");
                        activity.tilPass.setError("Tài khoản hoặc mật khẩu không đúng");
                    }else {
                        boolean a = false;
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).getUsername().equals(username)&&list.get(i).getPassword().equals(pass)){
                                Gson gson = new Gson();
                                String sUser = gson.toJson(list.get(i));
                                //lưu dữ liệu
                                SharedPreferences pref = context.getSharedPreferences("INFOR_USER", context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = pref.edit();
                                editor.putString("USER", sUser);
                                editor.commit();
                                a = true;
                                break;
                            }
                        }
                        if (a){
                            Toast.makeText(activity, "Đăng Nhập Thành Công", Toast.LENGTH_SHORT).show();
                            activity.startActivity(new Intent(activity,MainActivity.class));
                        }else {
                            activity.tilUserName.setError("Tài khoản hoặc mật khẩu không đúng");
                            activity.tilPass.setError("Tài khoản hoặc mật khẩu không đúng");
                        }
                    }
                    
                    // tự sử lý list
                }else {
                    Log.d("TAG", "onResponse: Lỗi: "+response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e("TAG E", "onFailure: "+t.getMessage() );
            }
        });
    }

    public  void BinhLuanUser(RecyclerView recyclerView, List<BinhLuan> listBl){
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        //sử dụng interface
        ClassInterface userInterface = retrofit.create(ClassInterface.class);

        Call<List<User>> objCall = userInterface.get_all();

        //gọi GET
        objCall.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, retrofit2.Response<List<User>> response) {
                if (response.isSuccessful()){
                    List<User> list = response.body();

                    List<BinhLuan> newListBl = new ArrayList<>();

                    for (int i = 0; i < listBl.size(); i++) {
                        for (int j = 0; j < list.size(); j++) {
                            if (Integer.parseInt(listBl.get(i).getIdUser()) == list.get(j).getId()){
                                BinhLuan binhLuan = new BinhLuan();
                                binhLuan.setIdUser(list.get(j).getUsername());
                                binhLuan.setIdTruyen(listBl.get(i).getIdTruyen());
                                binhLuan.setCmt(listBl.get(i).getCmt());
                                binhLuan.setNgay(listBl.get(i).getNgay());
                                newListBl.add(binhLuan);
                            }
                        }
                    }

                    AdapterRecyclerViewBinhLuan adapterRecyclerViewBinhLuan = new AdapterRecyclerViewBinhLuan();
                    adapterRecyclerViewBinhLuan.setData(newListBl);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context,RecyclerView.VERTICAL,false);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(adapterRecyclerViewBinhLuan);

                    // tự sử lý list
                }else {
                    Log.d("TAG", "onResponse: Lỗi: "+response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e("TAG E", "onFailure: "+t.getMessage() );
            }
        });
    }

    public  void checktk(String username, RegisterActivity activity, User user){
        ProgressDialog dialog = ProgressDialog.show(context,"","Waiting for register...");
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        //sử dụng interface
        ClassInterface userInterface = retrofit.create(ClassInterface.class);

        Call<List<User>> objCall = userInterface.check_user(username);

        //gọi GET
        objCall.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, retrofit2.Response<List<User>> response) {
                dialog.dismiss();
                if (response.isSuccessful()){
                    List<User> list = response.body();
                    if (list.size() == 0){
                        UserPostRetrofit(user);
                    }else {
                        activity.tilUsername.setError("Tài khoản đã tồn tại");
                    }

                    // tự sử lý list
                }else {
                    Log.d("TAG", "onResponse: Lỗi: "+response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e("TAG E", "onFailure: "+t.getMessage() );
            }
        });
    }


    public void UserPostRetrofit(User user){
        ProgressDialog dialog = ProgressDialog.show(context,"","Đang Tạo Tài Khoản...");
        Gson gson = new GsonBuilder().setLenient().create();

        //Tạo Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        //Khởi tạo Interface
        ClassInterface userInterface = retrofit.create(ClassInterface.class);

        //Tạo Call
        Call<User> objCall = userInterface.them_moi_user(user);

        //Thực hiện gửi dữ liệu lên server
        objCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, retrofit2.Response<User> response) {
                dialog.dismiss();
                if (response.isSuccessful()){
                    User obj = response.body();
                    Toast.makeText(context, "Tạo tài khoản thành công", Toast.LENGTH_SHORT).show();
                    context.startActivity(new Intent(context,LoginActivity.class));
                    Log.d("TAG", "onResponse: Kết Quả: "+obj.toString());
                }else {
                    Log.d("TAG", "onResponse: Lỗi "+response.message());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                dialog.dismiss();
                Log.e("TAG", "onFailure: "+t.getMessage());
            }
        });
    }

    public void userUpdate(User user){
        ProgressDialog dialog = ProgressDialog.show(context,"","Waiting for update...");
        Gson gson = new GsonBuilder().setLenient().create();

        //Tạo Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        //Khởi tạo Interface
        ClassInterface userInterface = retrofit.create(ClassInterface.class);

        //Tạo Call
        Call<User> objCall = userInterface.updateUser(String.valueOf(user.getId()),user);

        //Thực hiện gửi dữ liệu lên server
        objCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, retrofit2.Response<User> response) {
                dialog.dismiss();
                if (response.isSuccessful()){
                    User obj = response.body();
                    Gson gson = new Gson();
                    String sUser = gson.toJson(obj);
                    //lưu dữ liệu
                    SharedPreferences pref = context.getSharedPreferences("INFOR_USER", context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("USER", sUser);
                    editor.commit();
                    Log.d("TAG", "onResponse: Kết Quả: "+obj.toString());
                }else {
                    Log.d("TAG", "onResponse: Lỗi "+response.message());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                dialog.dismiss();
                Log.e("TAG", "onFailure: "+t.getMessage());
            }
        });
    }
}
