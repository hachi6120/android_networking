package com.example.myapplication.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.Adapter.AdapterRecyclerViewTruyen;
import com.example.myapplication.Model.Truyen;
import com.example.myapplication.R;
import com.example.myapplication.Ritrofit.TruyenRitrofit;

import java.util.ArrayList;
import java.util.List;

public class ThangFragment extends Fragment {

    RecyclerView recyclerView;
    String url = "https://mli72h-8080.csb.app/";

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thang, container, false);

        recyclerView = view.findViewById(R.id.topthang_rcv);

        TruyenRitrofit truyenRitrofit = new TruyenRitrofit(getContext(),url);
        truyenRitrofit.TruyenGetRetrofitRecyclerView(recyclerView);

        return view;
    }
}