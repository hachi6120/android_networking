package com.example.myapplication.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myapplication.Adapter.AdapterGridView;
import com.example.myapplication.Adapter.AdapterImageSlide;
import com.example.myapplication.ExpandableHeightGridView;
import com.example.myapplication.MainActivity;
import com.example.myapplication.Model.Photo;
import com.example.myapplication.Model.Truyen;
import com.example.myapplication.R;
import com.example.myapplication.Ritrofit.TruyenRitrofit;
import com.example.myapplication.ThongTinTruyenActivity;
import com.example.myapplication.TruyenYeuThich;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.databinding.FragmentHomeBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;

public class HomeFragment extends Fragment {

    ImageButton btnTT;

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

    String url = "https://mli72h-8080.csb.app/";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        setHasOptionsMenu(true);

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

        //btn truyện yêu thích
        root.findViewById(R.id.home_frm_yt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), TruyenYeuThich.class));
            }
        });

        //btn fragment danh sách
        root.findViewById(R.id.home_frm_list).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) requireActivity()).danhSachFragment();
            }
        });
        //btn fragment cá nhân
        root.findViewById(R.id.home_frm_tt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) requireActivity()).caNhanFragment();
            }
        });

        gridView = (ExpandableHeightGridView) root.findViewById(R.id.home_gridview);

        TruyenRitrofit truyenRitrofit = new TruyenRitrofit(getContext(),url);
        truyenRitrofit.TruyenGetRetrofit(gridView);

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