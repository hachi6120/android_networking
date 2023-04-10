package com.example.myapplication.Interface;

import com.example.myapplication.Model.BinhLuan;
import com.example.myapplication.Model.Truyen;
import com.example.myapplication.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ClassInterface {
    //get: lấy danh sách user;
    //post: tạo mới;

    //truyen
    @GET ("/truyen")
    Call<List<Truyen>> lay_danh_sach(); // trả về 1 danh sách

    @GET ("/truyen")
    Call<List<Truyen>> lay_truyen_id(@Query("id") List<String> id);

    @POST("/truyen")
    Call<Truyen> them_moi_truyen(@Body Truyen truyen);

    //User
    @GET ("/users")
    Call<List<User>> get_all();
    @GET ("/users")
    Call<List<User>> lay_user(@Query("username") String username,@Query("password") String pass);

    @GET ("/users")
    Call<List<User>> check_user(@Query("username") String username);


    @POST("/users")
    Call<User> them_moi_user(@Body User user);

    @PUT("/users/{id}")
    Call<User> updateUser(@Path("id") String id, @Body User user);

    //Bình Luận
    @GET ("/cmt")
    Call<List<BinhLuan>> list_cmt(@Query("idTruyen") int idTruyen);

    @POST("/cmt")
    Call<BinhLuan> add_cmt(@Body BinhLuan binhLuan);
}
