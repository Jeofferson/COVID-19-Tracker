package com.example.covid19tracker.PackageSessionVariables;

import androidx.fragment.app.Fragment;

import com.example.covid19tracker.PackageObjectModels.Country;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class SuperGlobals {


    public static String currentTab;
    public static Fragment currentFragment;

    public static Fragment tab1;
    public static Fragment tab2;
    public static Fragment tab3;
    public static Fragment tab4;

    public static String selectedMoreInformationType;

    public static int totalCases;

    public static int totalActive;
    public static int totalDeaths;
    public static int totalRecovered;

    public static int totalCritical;
    public static int totalTodayCases;
    public static int totalTodayDeaths;

    public static Country selectedCountry;
    public static String selectedCountryTimeline;

    public static LinkedHashMap<String, ArrayList<Fragment>> tabLinkedHashMap = new LinkedHashMap<>();

    public static ArrayList<Country> countryArrayList = new ArrayList<>();


}
