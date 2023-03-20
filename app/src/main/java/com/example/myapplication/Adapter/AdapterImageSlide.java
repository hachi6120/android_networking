package com.example.myapplication.Adapter;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;


import com.example.myapplication.Fragment.ImageSlideFragment;
import com.example.myapplication.Model.Photo;

import java.util.List;

public class AdapterImageSlide extends FragmentStateAdapter {

    private List<Photo> photoList;

    public AdapterImageSlide(FragmentActivity fragmentActivity, List<Photo> list) {
        super(fragmentActivity);
        this.photoList = list;
    }


    @Override
    public Fragment createFragment(int position) {

        Photo photo = photoList.get(position);
        Bundle bundle = new Bundle();
        bundle.putSerializable("photo",photo);

        ImageSlideFragment imageSlideFragment = new ImageSlideFragment();
        imageSlideFragment.setArguments(bundle);

        return imageSlideFragment;
    }

    @Override
    public int getItemCount() {
        if (photoList!=null){
            return photoList.size();
        }
        return 0;
    }
}
