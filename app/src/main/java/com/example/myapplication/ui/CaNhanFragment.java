package com.example.myapplication.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myapplication.Login.LoginActivity;
import com.example.myapplication.Login.PasswordActivity;
import com.example.myapplication.Model.User;
import com.example.myapplication.R;
import com.example.myapplication.TruyenYeuThich;
import com.example.myapplication.databinding.FragmentCanhanBinding;
import com.google.gson.Gson;

public class CaNhanFragment extends Fragment {

    private FragmentCanhanBinding binding;

    TextView tenU,U;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCanhanBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        tenU = root.findViewById(R.id.fcanhan_tv_tenu);
        U = root.findViewById(R.id.fcanhan_tv_u);

        SharedPreferences pre = getActivity().getSharedPreferences("INFOR_USER",getActivity().MODE_PRIVATE);
        String u = pre.getString("USER","");

        Gson gson = new Gson();
        User user = gson.fromJson(u,User.class);

        if (user != null){
            tenU.setText(user.getFullname());
            U.setText(user.getUsername());
        }

        root.findViewById(R.id.fcanhan_btn_truyenyt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), TruyenYeuThich.class));
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
                getActivity().finish();
            }
        });

        return root;
    }

}