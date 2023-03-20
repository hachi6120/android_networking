package com.example.myapplication.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.Adapter.AdapterRecyclerViewTruyen;
import com.example.myapplication.Model.Truyen;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class TuanFragment extends Fragment {

    RecyclerView recyclerView;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tuan, container, false);

        recyclerView = view.findViewById(R.id.toptuan_rcv);

        String a[] = null;
        List<Truyen> truyenList = new ArrayList<>();
        truyenList.add(new Truyen(1,"Thất đại tội","Truyện Hay","Tác Giả a",2008,"",a));
        truyenList.add(new Truyen(2,"naruto","Truyện Hay","kishimoto",2002,"",a));
        truyenList.add(new Truyen(3,"7 viên ngọc rồng","Truyện Hay","Tác Giả c",2000,"",a));
        truyenList.add(new Truyen(3,"7 viên ngọc rồng","Truyện Hay","Tác Giả c",2000,"",a));
        truyenList.add(new Truyen(3,"7 viên ngọc rồng","Truyện Hay","Tác Giả c",2000,"",a));
        truyenList.add(new Truyen(3,"7 viên ngọc rồng","Truyện Hay","Tác Giả c",2000,"",a));
        truyenList.add(new Truyen(3,"7 viên ngọc rồng","Truyện Hay","Tác Giả c",2000,"",a));

        AdapterRecyclerViewTruyen adapterRecyclerViewTruyen = new AdapterRecyclerViewTruyen(getActivity());
        adapterRecyclerViewTruyen.setData(truyenList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapterRecyclerViewTruyen);

        return view;
    }
}