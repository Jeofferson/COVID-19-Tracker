package com.example.covid19tracker.PackageHelpers;

import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.covid19tracker.PackageFragments.FragmentCountry;
import com.example.covid19tracker.PackageFragments.FragmentMoreInformation;
import com.example.covid19tracker.PackageFragments.FragmentTimeline;
import com.example.covid19tracker.PackageSessionVariables.SuperGlobals;
import com.example.covid19tracker.R;

public class FragmentNavigation {


    public static void goToFragmentTimeline(View view, String fromTab) {

        SuperGlobals.currentTab = fromTab;
        SuperGlobals.tabLinkedHashMap.get(fromTab).add(new FragmentTimeline());

        Fragment fragment = SuperGlobals.tabLinkedHashMap.get(fromTab).get(SuperGlobals.tabLinkedHashMap.get(fromTab).size() - 1);
        ((FragmentActivity) view.getContext()).getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragmentContainer, fragment)
                .hide(SuperGlobals.currentFragment)
                .show(fragment)
                .commit();

        SuperGlobals.currentFragment = fragment;

    }


    public static void goToFragmentCountry(View view, String fromTab) {

        SuperGlobals.currentTab = fromTab;
        SuperGlobals.tabLinkedHashMap.get(fromTab).add(new FragmentCountry());

        Fragment fragment = SuperGlobals.tabLinkedHashMap.get(fromTab).get(SuperGlobals.tabLinkedHashMap.get(fromTab).size() - 1);
        ((FragmentActivity) view.getContext()).getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragmentContainer, fragment)
                .hide(SuperGlobals.currentFragment)
                .show(fragment)
                .commit();

        SuperGlobals.currentFragment = fragment;

    }


    public static void goToFragmentMoreInformation(View view, String fromTab) {

        SuperGlobals.currentTab = fromTab;
        SuperGlobals.tabLinkedHashMap.get(fromTab).add(new FragmentMoreInformation());

        Fragment fragment = SuperGlobals.tabLinkedHashMap.get(fromTab).get(SuperGlobals.tabLinkedHashMap.get(fromTab).size() - 1);
        ((FragmentActivity) view.getContext()).getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragmentContainer, fragment)
                .hide(SuperGlobals.currentFragment)
                .show(fragment)
                .commit();

        SuperGlobals.currentFragment = fragment;

    }


}
