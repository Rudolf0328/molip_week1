package com.example.molip;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    FragmentManager manager;
    FragmentTransaction ft;

    Phone phone;
    Pictures pictures;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = getSupportFragmentManager();

        Button phoneTab = findViewById(R.id.one);
        Button picturesTab = findViewById(R.id.two);

        phone = new Phone();
        pictures = new Pictures();

        ft = manager.beginTransaction();
        ft.add(R.id.fragment_container, phone);
        ft.addToBackStack(null);
        ft.commit();

        phoneTab.setOnClickListener(this);
        picturesTab.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        ft = manager.beginTransaction();

        int id = v.getId();
        switch (id) {
            case R.id.one:
                ft.replace(R.id.fragment_container, phone);
                ft.commit();
                break;
            case R.id.two:
                ft.replace(R.id.fragment_container, pictures);
                ft.commit();
                break;
            default:
                break;
        }
    }
}