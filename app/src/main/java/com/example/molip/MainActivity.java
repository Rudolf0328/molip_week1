package com.example.molip;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.molip.phonePage.PhoneActivity;
import com.example.molip.picturePage.Pictures;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main_Activity";
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout=findViewById(R.id.tabs);
        viewPager=findViewById(R.id.view_pager);
        adapter=new Adapter(getSupportFragmentManager(),1);

        //FragmentAdapter에 컬렉션 담기
        adapter.addFragment(new PhoneActivity());
        adapter.addFragment(new Pictures());
//        adapter.addFragment(new Frag3());

        //ViewPager Fragment 연결
        viewPager.setAdapter(adapter);

        //ViewPager과 TabLayout 연결
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setText("첫 번째");
        tabLayout.getTabAt(1).setText("두 번째");
//        tabLayout.getTabAt(2).setText("세 번째");
    }
}