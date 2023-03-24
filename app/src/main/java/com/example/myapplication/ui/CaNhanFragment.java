package com.example.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myapplication.Login.LoginActivity;
import com.example.myapplication.Login.PasswordActivity;
import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentCanhanBinding;

public class CaNhanFragment extends Fragment {

    private FragmentCanhanBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCanhanBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        root.findViewById(R.id.fcanhan_btn_truyenyt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        root.findViewById(R.id.fcanhan_btn_doimk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), PasswordActivity.class));
            }
        });

        root.findViewById(R.id.fcanhan_btn_dx).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });

        return root;
    }

}