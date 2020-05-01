package com.example.covid19tracker.PackageActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.covid19tracker.PackageFragments.FragmentAbout;
import com.example.covid19tracker.PackageFragments.FragmentCountries;
import com.example.covid19tracker.PackageFragments.FragmentWorld;
import com.example.covid19tracker.PackageFragments.FragmentMyCountry;
import com.example.covid19tracker.PackageFragments.FragmentDashboard;
import com.example.covid19tracker.PackageSessionVariables.Constants;
import com.example.covid19tracker.PackageSessionVariables.SuperGlobals;
import com.example.covid19tracker.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {


    private FragmentManager fragmentManager;

    private BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prepareBottomNavigationView();

    }


    @Override
    public void onBackPressed() {

        if (SuperGlobals.tabLinkedHashMap.get(SuperGlobals.currentTab).size() <= 1) {

            super.onBackPressed();

        } else {

            Fragment fragment = SuperGlobals.tabLinkedHashMap.get(SuperGlobals.currentTab).get(SuperGlobals.tabLinkedHashMap.get(SuperGlobals.currentTab).size() - 2);
            fragmentManager
                    .beginTransaction()
                    .hide(SuperGlobals.currentFragment)
                    .show(fragment)
                    .commit();

            SuperGlobals.currentFragment = fragment;
            SuperGlobals.tabLinkedHashMap.get(SuperGlobals.currentTab).remove(SuperGlobals.tabLinkedHashMap.get(SuperGlobals.currentTab).size() - 1);

        }

    }

    private void prepareBottomNavigationView() {

        fragmentManager = getSupportFragmentManager();
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        prepareTabs();

//        bottomNavigationView.setItemIconTintList(null);
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        bottomNavigationView.setOnNavigationItemReselectedListener(onNavigationItemReselectedListener);

    }


    private void prepareTabs() {

        Fragment fragment = new FragmentDashboard();

        SuperGlobals.tabLinkedHashMap.put(Constants.DASHBOARD, new ArrayList<>(Arrays.asList(
                fragment
        )));
        SuperGlobals.tabLinkedHashMap.put(Constants.WORLD, new ArrayList<Fragment>());
        SuperGlobals.tabLinkedHashMap.put(Constants.COUNTRIES, new ArrayList<Fragment>());
        SuperGlobals.tabLinkedHashMap.put(Constants.MY_COUNTRY, new ArrayList<Fragment>());
        SuperGlobals.tabLinkedHashMap.put(Constants.ABOUT, new ArrayList<Fragment>());

        SuperGlobals.currentTab = Constants.DASHBOARD;

        fragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainer, fragment)
                .show(fragment)
                .commit();

        bottomNavigationView.getMenu().getItem(0).setIcon(R.drawable.home);

        SuperGlobals.currentFragment = fragment;

    }


    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            bottomNavigationView.getMenu().getItem(0).setIcon(R.drawable.home_outline);
            bottomNavigationView.getMenu().getItem(1).setIcon(R.drawable.world_outline);
            bottomNavigationView.getMenu().getItem(2).setIcon(R.drawable.countries_outline);
            bottomNavigationView.getMenu().getItem(3).setIcon(R.drawable.philippines_outline);
            bottomNavigationView.getMenu().getItem(4).setIcon(R.drawable.about_outline);

            switch (item.getItemId()) {

                case R.id.home:
                    item.setIcon(R.drawable.home);
                    selectTab(item, Constants.DASHBOARD);
                    return true;

                case R.id.world:
                    item.setIcon(R.drawable.world);
                    selectTab(item, Constants.WORLD);
                    return true;

                case R.id.countries:
                    item.setIcon(R.drawable.countries);
                    selectTab(item, Constants.COUNTRIES);
                    return true;

                case R.id.myCountry:
                    item.setIcon(R.drawable.philippines);
                    selectTab(item, Constants.MY_COUNTRY);
                    return true;

                case R.id.about:
                    item.setIcon(R.drawable.about);
                    selectTab(item, Constants.ABOUT);
                    return true;

                default:
                    return false;

            }

        }
    };


    private void selectTab(MenuItem item, String tabName) {

        SuperGlobals.currentTab = tabName;

        if (SuperGlobals.tabLinkedHashMap.get(tabName).isEmpty()) {

            switch (tabName) {

                case Constants.DASHBOARD:
                    SuperGlobals.tabLinkedHashMap.get(tabName).add(new FragmentDashboard());
                    break;

                case Constants.WORLD:
                    SuperGlobals.tabLinkedHashMap.get(tabName).add(new FragmentWorld());
                    break;

                case Constants.COUNTRIES:
                    SuperGlobals.tabLinkedHashMap.get(tabName).add(new FragmentCountries());
                    break;

                case Constants.MY_COUNTRY:
                    SuperGlobals.tabLinkedHashMap.get(tabName).add(new FragmentMyCountry());
                    break;

                case Constants.ABOUT:
                    SuperGlobals.tabLinkedHashMap.get(tabName).add(new FragmentAbout());
                    break;

            }

            Fragment fragment = SuperGlobals.tabLinkedHashMap.get(tabName).get(SuperGlobals.tabLinkedHashMap.get(tabName).size() - 1);
            fragmentManager
                    .beginTransaction()
                    .add(R.id.fragmentContainer, fragment)
                    .hide(SuperGlobals.currentFragment)
                    .show(fragment)
                    .commit();

            SuperGlobals.currentFragment = fragment;

        } else {

            Fragment fragment = SuperGlobals.tabLinkedHashMap.get(tabName).get(SuperGlobals.tabLinkedHashMap.get(tabName).size() - 1);
            fragmentManager
                    .beginTransaction()
                    .hide(SuperGlobals.currentFragment)
                    .show(fragment)
                    .commit();

            SuperGlobals.currentFragment = fragment;

        }

    }


    private BottomNavigationView.OnNavigationItemReselectedListener onNavigationItemReselectedListener = new BottomNavigationView.OnNavigationItemReselectedListener() {
        @Override
        public void onNavigationItemReselected(@NonNull MenuItem item) {

            switch (item.getItemId()) {

                case R.id.home:
                    reselectTab(Constants.DASHBOARD);
                    break;

                case R.id.world:
                    reselectTab(Constants.WORLD);
                    break;

                case R.id.countries:
                    reselectTab(Constants.COUNTRIES);
                    break;

                case R.id.myCountry:
                    reselectTab(Constants.MY_COUNTRY);
                    break;

                case R.id.about:
                    reselectTab(Constants.ABOUT);
                    break;

            }

        }
    };


    private void reselectTab(String tabName) {

        SuperGlobals.currentTab = tabName;

        for (int i = SuperGlobals.tabLinkedHashMap.get(tabName).size() - 1; i >= 1; i--) {

            SuperGlobals.tabLinkedHashMap.get(tabName).remove(i);

        }

        Fragment fragment = SuperGlobals.tabLinkedHashMap.get(tabName).get(SuperGlobals.tabLinkedHashMap.get(tabName).size() - 1);
        fragmentManager
                .beginTransaction()
                .hide(SuperGlobals.currentFragment)
                .show(fragment)
                .commit();

        SuperGlobals.currentFragment = fragment;

    }


}
