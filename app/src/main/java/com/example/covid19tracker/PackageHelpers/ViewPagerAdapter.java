package com.example.covid19tracker.PackageHelpers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {


    private final List<Fragment> tabList = new ArrayList<>();
    private final List<String> tabTitleList = new ArrayList<>();


    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return tabList.get(position);
    }


    @Override
    public int getCount() {
        return tabTitleList.size();
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitleList.get(position);
    }


    public void addTab(Fragment fragment, String title) {

        tabList.add(fragment);
        tabTitleList.add(title);

    }


}
