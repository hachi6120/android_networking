package com.example.myapplication.ui;

import android.content.Intent;
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

        String a[] = null;
        //
        List<Truyen> truyenList = new ArrayList<>();
        truyenList.add(new Truyen(1,"Thất đại tội","Truyện Hay","Tác Giả a",2008,"",a));
        truyenList.add(new Truyen(2,"naruto","Truyện Hay","kishimoto",2002,"",a));
        truyenList.add(new Truyen(3,"7 viên ngọc rồng","Truyện Hay","Tác Giả c",2000,"",a));
        truyenList.add(new Truyen(3,"7 viên ngọc rồng","Truyện Hay","Tác Giả c",2000,"",a));
        truyenList.add(new Truyen(3,"7 viên ngọc rồng","Truyện Hay","Tác Giả c",2000,"",a));
        truyenList.add(new Truyen(3,"7 viên ngọc rồng","Truyện Hay","Tác Giả c",2000,"",a));
        truyenList.add(new Truyen(3,"7 viên ngọc rồng","Truyện Hay","Tác Giả c",2000,"",a));

        gridView = (ExpandableHeightGridView) root.findViewById(R.id.home_gridview);
        AdapterGridView adapterGridView = new AdapterGridView(getActivity(),truyenList);
        gridView.setAdapter(adapterGridView);
        gridView.setExpanded(true);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), ThongTinTruyenActivity.class);
                intent.putExtra("i",i);
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