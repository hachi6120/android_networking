package com.example.myapplication.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myapplication.Adapter.AdapterFragment;
import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentListTruyenBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ListTruyenFragment extends Fragment {

    ViewPager2 viewPager2;
    TabLayout tabLayout;

    AdapterFragment adapterFragment;

    private FragmentListTruyenBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentListTruyenBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        viewPager2 = root.findViewById(R.id.lt_viewpager);
        tabLayout = root.findViewById(R.id.lt_tablayout);

        adapterFragment = new AdapterFragment(getActivity());
        viewPager2.setAdapter(adapterFragment);

        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:
                        tab.setText("Top Tuần");
                        break;
                    case 1:
                        tab.setText("Top Tháng");
                        break;
                }
            }
        }).attach();

        return root;
    }
}