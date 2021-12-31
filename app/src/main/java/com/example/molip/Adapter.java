package com.example.molip;

//FragmentPagerAdapter -> 모든 프래그먼트를 메모리에 미리 로딩 시키고 소멸X
//FragmentStatePagerAdapter -> default 3. 자기를 포함한 양 옆까지 메모리에 로딩

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends FragmentPagerAdapter {

    private ArrayList<List<Fragment>> fragmentList = new ArrayList<>();

    public Adapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    public void addFragment(Fragment fragment){
        fragmentList.add((List<Fragment>) fragment);
    }

    public void addFragmentInTab (Fragment fragment, int index ) {
        List<Fragment> fragments = fragmentList.get(index);
        fragments.add(fragment);
        fragmentList.add(fragments);
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position).get(0);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}