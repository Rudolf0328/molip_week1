package com.example.molip.randomPage;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.molip.R;

public class RandomFrag extends Fragment {
    Button button1;
    Button button2;
    ImageView imageView;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup imgview = (ViewGroup) inflater.inflate(R.layout.randomfrag, container, false);
        imageView = imgview.findViewById(R.id.gifview);
        Glide.with(this).load(R.raw.whateat).into(imageView);
        button1 = (Button) imgview.findViewById(R.id.button1);
        button2 = (Button) imgview.findViewById(R.id.button2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), RouletteActivity.class);
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MoneyActivity.class);
                startActivity(intent);
            }
        });
        return imgview;
    }
}
