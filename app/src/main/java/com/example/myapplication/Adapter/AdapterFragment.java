package com.example.myapplication.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapplication.Fragment.ThangFragment;
import com.example.myapplication.Fragment.TuanFragment;

public class AdapterFragment extends FragmentStateAdapter {

    public AdapterFragment(FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new TuanFragment();
            case 1:
                return new ThangFragment();
            default:
                return new TuanFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
