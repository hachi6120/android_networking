package com.example.myapplication.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myapplication.Adapter.AdapterGridView;
import com.example.myapplication.Adapter.AdapterImageSlide;
import com.example.myapplication.ExpandableHeightGridView;
import com.example.myapplication.Model.Photo;
import com.example.myapplication.Model.Truyen;
import com.example.myapplication.R;
import com.example.myapplication.ThongTinTruyenActivity;
import com.example.myapplication.databinding.FragmentHomeBinding;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;

public class HomeFragment extends Fragment {


    private ViewPager2 mViewPager2;
    private CircleIndicator3 mCircleIndicator3;

    private List<Photo> mPhotoList;

    private Handler handler = new Handler(Looper.getMainLooper());
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            int currentPosition = mViewPager2.getCurrentItem();
            if (currentPosition == mPhotoList.size()-1){
                mViewPager2.setCurrentItem(0);
            }else {
                mViewPager2.setCurrentItem(currentPosition + 1);
            }
        }
    };

    //
    ExpandableHeightGridView gridView;

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mViewPager2 = root.findViewById(R.id.home_view_page2);
        mCircleIndicator3 = root.findViewById(R.id.home_circle_indicator);

        mPhotoList = getListPhoto();

        AdapterImageSlide adapterImageSlide = new AdapterImageSlide(getActivity(),mPhotoList);
        mViewPager2.setAdapter(adapterImageSlide);
        mCircleIndicator3.setViewPager(mViewPager2);

        mViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable,3000);
            }
        });

        String a[] = new String[]{"https://doctruyentranh.net.vn/ckfinder/userfiles/images/doc_truyen_tranh_naruto_chap_13_5.jpg",
                "https://doctruyentranh.net.vn/ckfinder/userfiles/images/doc_truyen_tranh_naruto_chap_13_6.jpg",
                "https://doctruyentranh.net.vn/ckfinder/userfiles/images/doc_truyen_tranh_naruto_chap_13_7.jpg",
                "https://doctruyentranh.net.vn/ckfinder/userfiles/images/doc_truyen_tranh_naruto_chap_13_8.jpg",
                "https://doctruyentranh.net.vn/ckfinder/userfiles/images/doc_truyen_tranh_naruto_chap_13_9.jpg",
                "https://doctruyentranh.net.vn/ckfinder/userfiles/images/doc_truyen_tranh_naruto_chap_13_10.jpg",
                "https://doctruyentranh.net.vn/ckfinder/userfiles/images/doc_truyen_tranh_naruto_chap_13_11.jpg",
                "https://doctruyentranh.net.vn/ckfinder/userfiles/images/doc_truyen_tranh_naruto_chap_13_12.jpg",
                "https://doctruyentranh.net.vn/ckfinder/userfiles/images/doc_truyen_tranh_naruto_chap_13_13.jpg",
                "https://doctruyentranh.net.vn/ckfinder/userfiles/images/doc_truyen_tranh_naruto_chap_13_14.jpg",
                "https://doctruyentranh.net.vn/ckfinder/userfiles/images/doc_truyen_tranh_naruto_chap_13_15.jpg",
                "https://doctruyentranh.net.vn/ckfinder/userfiles/images/doc_truyen_tranh_naruto_chap_13_16.jpg",
                "https://doctruyentranh.net.vn/ckfinder/userfiles/images/doc_truyen_tranh_naruto_chap_13_17.jpg",
                "https://doctruyentranh.net.vn/ckfinder/userfiles/images/doc_truyen_tranh_naruto_chap_13_18.jpg",
                "https://doctruyentranh.net.vn/ckfinder/userfiles/images/doc_truyen_tranh_naruto_chap_13_19.jpg",
                "https://doctruyentranh.net.vn/ckfinder/userfiles/images/doc_truyen_tranh_naruto_chap_13_20.jpg",
                "https://doctruyentranh.net.vn/ckfinder/userfiles/images/doc_truyen_tranh_naruto_chap_13_21.jpg",};
        //
        List<Truyen> truyenList = new ArrayList<>();
        truyenList.add(new Truyen(1,"Naruto","Truyện Hay","kishimoto",2008,"https://animehunch.com/wp-content/uploads/2021/09/Untitled-design-2-768x431.jpg",a));
        truyenList.add(new Truyen(2,"Akame ga kill","Truyện Hay","A",2002,"https://storage.googleapis.com/happy-quiz-vn.appspot.com/zq/quizzes/a2e05a00-a5f9-11e5-b6b7-050901070303-raw.jpeg",a));
        truyenList.add(new Truyen(3,"Jujutsu kaisen","Truyện Hay","Tác Giả c",2000,"https://nguvan.vn/wp-content/uploads/2021/03/Xtrafondos-1.jpg",a));
        truyenList.add(new Truyen(4,"Boku no hero","Truyện Hay","Tác Giả c",2000,"https://sieuimba.com/wp-content/uploads/2016/07/b5-1.jpg",a));
        truyenList.add(new Truyen(5,"Kimetsu no yaiba","Truyện Hay","Tác Giả c",2000,"https://gamek.mediacdn.vn/133514250583805952/2020/4/26/photo-2-1587918273424118655688.jpg",a));
        truyenList.add(new Truyen(6,"Nanatsu no taizai","Truyện Hay","Tác Giả c",2000,"https://piunikaweb.com/wp-content/uploads/2020/03/nanatsu-no-taizai-min.jpg",a));

        gridView = (ExpandableHeightGridView) root.findViewById(R.id.home_gridview);
        AdapterGridView adapterGridView = new AdapterGridView(getActivity(),truyenList);
        gridView.setAdapter(adapterGridView);
        gridView.setExpanded(true);


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // chuyển về string
                Gson gson = new Gson();
                String sTruyen = gson.toJson(truyenList.get(i));
                //lưu dữ liệu
                SharedPreferences pref = getActivity().getSharedPreferences("INFOR_TRUYEN", getActivity().MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("TRUYEN", sTruyen);
                editor.commit();

                Intent intent = new Intent(getActivity(), ThongTinTruyenActivity.class);
                startActivity(intent);
            }
        });
        return root;
    }

    private List<Photo> getListPhoto(){
        List<Photo> list = new ArrayList<>();
        list.add(new Photo(R.drawable.bannertruyen));
        list.add(new Photo(R.drawable.c));
        list.add(new Photo(R.drawable.m));
        list.add(new Photo(R.drawable.s));
        return list;
    }
}