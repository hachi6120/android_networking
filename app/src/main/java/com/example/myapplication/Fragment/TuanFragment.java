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
        truyenList.add(new Truyen(1,"Naruto","Truyện Hay","kishimoto",2008,"https://animehunch.com/wp-content/uploads/2021/09/Untitled-design-2-768x431.jpg",a));
        truyenList.add(new Truyen(2,"Akame ga kill","Truyện Hay","A",2002,"https://storage.googleapis.com/happy-quiz-vn.appspot.com/zq/quizzes/a2e05a00-a5f9-11e5-b6b7-050901070303-raw.jpeg",a));
        truyenList.add(new Truyen(3,"Jujutsu kaisen","Truyện Hay","Tác Giả c",2000,"https://nguvan.vn/wp-content/uploads/2021/03/Xtrafondos-1.jpg",a));
        truyenList.add(new Truyen(4,"Boku no hero","Truyện Hay","Tác Giả c",2000,"https://sieuimba.com/wp-content/uploads/2016/07/b5-1.jpg",a));
        truyenList.add(new Truyen(5,"Kimetsu no yaiba","Truyện Hay","Tác Giả c",2000,"https://gamek.mediacdn.vn/133514250583805952/2020/4/26/photo-2-1587918273424118655688.jpg",a));
        truyenList.add(new Truyen(6,"Nanatsu no taizai","Truyện Hay","Tác Giả c",2000,"https://piunikaweb.com/wp-content/uploads/2020/03/nanatsu-no-taizai-min.jpg",a));

        AdapterRecyclerViewTruyen adapterRecyclerViewTruyen = new AdapterRecyclerViewTruyen(getActivity());
        adapterRecyclerViewTruyen.setData(truyenList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapterRecyclerViewTruyen);

        return view;
    }
}