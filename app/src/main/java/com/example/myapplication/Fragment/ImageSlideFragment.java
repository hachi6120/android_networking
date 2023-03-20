package com.example.myapplication.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.myapplication.Model.Photo;
import com.example.myapplication.R;

public class ImageSlideFragment extends Fragment {

    ImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_slide, container, false);
        Bundle bundle = getArguments();
        Photo photo = (Photo) bundle.get("photo");

        imageView = view.findViewById(R.id.img_slide);

        imageView.setImageResource(photo.getResourceID());
        return view;
    }
}