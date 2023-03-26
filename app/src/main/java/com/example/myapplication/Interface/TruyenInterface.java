package com.example.myapplication.Interface;

import com.example.myapplication.Model.Truyen;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface TruyenInterface {
    //get: lấy danh sách user;
    //post: tạo mới;

    @GET ("/truyen")
    Call<List<Truyen>> lay_danh_sach(); // trả về 1 danh sách


    @POST("/truyen")
    Call<Truyen> them_moi_user(@Body Truyen userDTO); // thêm mới sẽ truyền vào 1 đối tượng
}
