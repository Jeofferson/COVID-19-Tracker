package com.example.covid19tracker.PackageFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.covid19tracker.PackageHelpers.UtilMethods;
import com.example.covid19tracker.PackageHelpers.ViewPagerAdapter;
import com.example.covid19tracker.PackageObjectModels.Case;
import com.example.covid19tracker.PackageObjectModels.CaseForeignNational;
import com.example.covid19tracker.PackageObjectModels.CaseOfw;
import com.example.covid19tracker.PackageSessionVariables.Constants;
import com.example.covid19tracker.PackageSessionVariables.ConstantsVolley;
import com.example.covid19tracker.PackageSessionVariables.SuperGlobals;
import com.example.covid19tracker.PackageTabs.TabCases;
import com.example.covid19tracker.PackageTabs.TabCities;
import com.example.covid19tracker.PackageTabs.TabDashboard;
import com.example.covid19tracker.PackageTabs.TabFacilities;
import com.example.covid19tracker.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;


public class FragmentMyCountry extends Fragment {


    private final String RESIDENCE = "residence";
    private final String FACILITY = "facility";

    public static ArrayList<Case> caseArrayList = new ArrayList<>();
    public static ArrayList<CaseOfw> caseOfwArrayList = new ArrayList<>();
    public static ArrayList<CaseForeignNational> caseForeignNationalArrayList = new ArrayList<>();
    public static LinkedHashMap<String, Integer> cityLinkedHashMap = new LinkedHashMap<>();
    public static LinkedHashMap<String, Integer> facilityLinkedHashMap = new LinkedHashMap<>();

    private View view;

    private TabLayout tabLayout;
    private ViewPager viewPager;


    public FragmentMyCountry() {}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_my_country, container, false);

        prepareTabLayout();

        new Thread(new Runnable() {
            @Override
            public void run() {

//                queryFilipinosWithinTheCountry();

            }
        }).start();

        return view;

    }


    private void prepareTabLayout() {

        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewPager);

        SuperGlobals.tab1 = new TabDashboard();
        SuperGlobals.tab2 = new TabCities();
        SuperGlobals.tab3 = new TabFacilities();
        SuperGlobals.tab4 = new TabCases();

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(((FragmentActivity) view.getContext()).getSupportFragmentManager(), 0);
        viewPagerAdapter.addTab(SuperGlobals.tab1, "Dashboard");
//        viewPagerAdapter.addTab(SuperGlobals.tab2, "Cities");
//        viewPagerAdapter.addTab(SuperGlobals.tab3, "Facilities");
//        viewPagerAdapter.addTab(SuperGlobals.tab4, "Cases");

        viewPager.setOffscreenPageLimit(viewPagerAdapter.getCount());
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                switch (tab.getPosition()) {

                    case 0:
                        turnOffToolbarScrolling();
                        break;

                    default:
                        turnOnToolbarScrolling();

                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }


    public void turnOffToolbarScrolling() {

        //turn off scrolling
        AppBarLayout.LayoutParams toolbarLayoutParams = (AppBarLayout.LayoutParams) tabLayout.getLayoutParams();
        toolbarLayoutParams.setScrollFlags(0);
        tabLayout.setLayoutParams(toolbarLayoutParams);

    }


    public void turnOnToolbarScrolling() {

        //turn on scrolling
        AppBarLayout.LayoutParams toolbarLayoutParams = (AppBarLayout.LayoutParams) tabLayout.getLayoutParams();
        toolbarLayoutParams.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
        tabLayout.setLayoutParams(toolbarLayoutParams);

    }


    private void queryFilipinosWithinTheCountry() {

        caseArrayList.clear();
        cityLinkedHashMap.clear();
        facilityLinkedHashMap.clear();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, ConstantsVolley.MY_COUNTRY_CASES,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (response != null) {

                            Log.e(Constants.TAG_FRAGMENT_MY_COUNTRY, response);

                            try {

                                JSONArray jsonArray = new JSONArray(response);

                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    JSONObject rawData = jsonObject.getJSONObject(ConstantsVolley.METADATA).getJSONObject(ConstantsVolley.RAW_DATA);

                                    int id = i;

                                    String caseId =  jsonObject.getString(ConstantsVolley.CASE_ID);

                                    int age = !UtilMethods.isInteger(jsonObject.getString(ConstantsVolley.AGE)) ? -1 : Integer.parseInt(jsonObject.getString(ConstantsVolley.AGE));
                                    String sex = !jsonObject.has(ConstantsVolley.SEX) ? "TBA" : jsonObject.getString(ConstantsVolley.SEX);
                                    String dateConfirmed = jsonObject.getString(ConstantsVolley.DATE_CONFIRMED);
                                    JSONObject coordinates = jsonObject.getJSONObject(ConstantsVolley.COORDINATES);
                                    String status = jsonObject.getString(ConstantsVolley.STATUS);
                                    String nationality = jsonObject.getString(ConstantsVolley.NATIONALITY);
                                    JSONObject residence = jsonObject.optJSONObject(ConstantsVolley.RESIDENCE);
                                    JSONArray travelHistory = jsonObject.getJSONArray(ConstantsVolley.TRAVEL_HISTORY);
                                    JSONArray symptoms = jsonObject.getJSONArray(ConstantsVolley.SYMPTOMS);
                                    String facility = jsonObject.getString(ConstantsVolley.FACILITY);
                                    String dateDeceased = jsonObject.getString(ConstantsVolley.DATE_DECEASED);
                                    String dateRecovered = jsonObject.getString(ConstantsVolley.DATE_RECOVERED);
                                    JSONObject metadata = jsonObject.getJSONObject(ConstantsVolley.METADATA);

                                    Case mCase = new Case(
                                            id,
                                            caseId,
                                            age,
                                            sex,
                                            dateConfirmed,
                                            coordinates,
                                            status,
                                            nationality,
                                            residence,
                                            travelHistory,
                                            symptoms,
                                            facility,
                                            dateDeceased,
                                            dateRecovered,
                                            metadata
                                    );

                                    caseArrayList.add(mCase);

                                    if (rawData.getString(RESIDENCE).trim().isEmpty()) {
                                        if (!cityLinkedHashMap.containsKey(ConstantsVolley.FOR_VALIDATION)) {
                                            cityLinkedHashMap.put(ConstantsVolley.FOR_VALIDATION, 1);
                                        } else {
                                            int newValue = cityLinkedHashMap.get(ConstantsVolley.FOR_VALIDATION) + 1;
                                            cityLinkedHashMap.put(ConstantsVolley.FOR_VALIDATION, newValue);
                                        }
                                    } else if (!cityLinkedHashMap.containsKey(rawData.getString(RESIDENCE))) {
                                        cityLinkedHashMap.put(rawData.getString(ConstantsVolley.RESIDENCE), 1);
                                    } else {
                                        int newValue = cityLinkedHashMap.get(rawData.getString(RESIDENCE)) + 1;
                                        cityLinkedHashMap.put(rawData.getString(RESIDENCE), newValue);
                                    }

                                    if (rawData.getString(FACILITY).trim().isEmpty()) {
                                        if (!facilityLinkedHashMap.containsKey(ConstantsVolley.FOR_VALIDATION)) {
                                            facilityLinkedHashMap.put(ConstantsVolley.FOR_VALIDATION, 1);
                                        } else {
                                            int newValue = facilityLinkedHashMap.get(ConstantsVolley.FOR_VALIDATION) + 1;
                                            facilityLinkedHashMap.put(ConstantsVolley.FOR_VALIDATION, newValue);
                                        }
                                    } else if (!facilityLinkedHashMap.containsKey(rawData.getString(FACILITY))) {
                                        facilityLinkedHashMap.put(rawData.getString(ConstantsVolley.FACILITY), 1);
                                    } else {
                                        int newValue = facilityLinkedHashMap.get(rawData.getString(FACILITY)) + 1;
                                        facilityLinkedHashMap.put(rawData.getString(FACILITY), newValue);
                                    }

                                }

                                cityLinkedHashMap.remove("None");
                                if (cityLinkedHashMap.isEmpty() || facilityLinkedHashMap.isEmpty()) {

                                    queryFilipinosWithinTheCountry();

                                }

                                cityLinkedHashMap = UtilMethods.reverseHashMap(UtilMethods.sortByValue(cityLinkedHashMap));
                                facilityLinkedHashMap = UtilMethods.reverseHashMap(UtilMethods.sortByValue(facilityLinkedHashMap));
                                ((TabCities) SuperGlobals.tab2).updateRecyclerView();
                                ((TabFacilities) SuperGlobals.tab3).updateRecyclerView();

                                Collections.reverse(caseArrayList);
                                queryOfws();

                            } catch(JSONException e) {

                                e.printStackTrace();
                                queryFilipinosWithinTheCountry();

                            }

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.e(Constants.TAG_FRAGMENT_MY_COUNTRY, error.toString());
                        queryFilipinosWithinTheCountry();

                    }
                }
        );

        Volley.newRequestQueue(view.getContext()).add(stringRequest);

    }


    private void queryOfws() {

        caseOfwArrayList.clear();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, ConstantsVolley.MY_COUNTRY_CASES_OFW,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (response != null) {

                            Log.e(Constants.TAG_FRAGMENT_MY_COUNTRY, response);

                            try {

                                JSONArray jsonArray = new JSONArray(response);

                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                                    int id = i;

                                    String caseId =  jsonObject.getString(ConstantsVolley.CASE_ID);

                                    int age = !UtilMethods.isInteger(jsonObject.getString(ConstantsVolley.AGE)) ? -1 : Integer.parseInt(jsonObject.getString(ConstantsVolley.AGE));
                                    String sex = !jsonObject.has(ConstantsVolley.SEX) ? "TBA" : jsonObject.getString(ConstantsVolley.SEX);
                                    String country = jsonObject.getString(ConstantsVolley.COUNTRY);
                                    JSONObject coordinates = jsonObject.getJSONObject(ConstantsVolley.COORDINATES);
                                    String dateReported = jsonObject.getString(ConstantsVolley.DATE_REPORTED);
                                    String dateConfirmed = jsonObject.getString(ConstantsVolley.DATE_CONFIRMED);
                                    String status = jsonObject.getString(ConstantsVolley.STATUS);
                                    String remarks = jsonObject.getString(ConstantsVolley.REMARKS);
                                    JSONObject metadata = jsonObject.getJSONObject(ConstantsVolley.METADATA);

                                    CaseOfw caseOfw = new CaseOfw(
                                            id,
                                            caseId,
                                            age,
                                            sex,
                                            country,
                                            coordinates,
                                            dateReported,
                                            dateConfirmed,
                                            status,
                                            remarks,
                                            metadata
                                    );

                                    caseOfwArrayList.add(caseOfw);

                                }

                                Collections.reverse(caseOfwArrayList);
                                queryForeignNationals();

                            } catch(JSONException e) {

                                e.printStackTrace();
                                queryOfws();

                            }

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.e(Constants.TAG_FRAGMENT_MY_COUNTRY, error.toString());
                        queryOfws();

                    }
                }
        );

        Volley.newRequestQueue(view.getContext()).add(stringRequest);

    }


    private void queryForeignNationals() {

        caseForeignNationalArrayList.clear();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, ConstantsVolley.MY_COUNTRY_CASES_FOREIGN_NATIONAL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (response != null) {

                            Log.e(Constants.TAG_FRAGMENT_MY_COUNTRY, response);

                            try {

                                JSONArray jsonArray = new JSONArray(response);

                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                                    int id = i;

                                    String caseId =  jsonObject.getString(ConstantsVolley.CASE_ID);

                                    int age = !UtilMethods.isInteger(jsonObject.getString(ConstantsVolley.AGE)) ? -1 : Integer.parseInt(jsonObject.getString(ConstantsVolley.AGE));
                                    String sex = !jsonObject.has(ConstantsVolley.SEX) ? "TBA" : jsonObject.getString(ConstantsVolley.SEX);
                                    String nationality = jsonObject.getString(ConstantsVolley.NATIONALITY);
                                    JSONObject travelDate = jsonObject.getJSONObject(ConstantsVolley.TRAVEL_DATE);
                                    JSONArray travelHistory = jsonObject.getJSONArray(ConstantsVolley.TRAVEL_HISTORY);
                                    String dateConfirmed = jsonObject.getString(ConstantsVolley.DATE_CONFIRMED);
                                    String whereNow = jsonObject.getString(ConstantsVolley.WHERE_NOW);
                                    JSONObject coordinates = jsonObject.getJSONObject(ConstantsVolley.COORDINATES);
                                    String status = jsonObject.getString(ConstantsVolley.STATUS);
                                    JSONObject metadata = jsonObject.getJSONObject(ConstantsVolley.METADATA);

                                    CaseForeignNational caseForeignNational = new CaseForeignNational(
                                            id,
                                            caseId,
                                            age,
                                            sex,
                                            nationality,
                                            travelDate,
                                            travelHistory,
                                            dateConfirmed,
                                            whereNow,
                                            coordinates,
                                            status,
                                            metadata
                                    );

                                    caseForeignNationalArrayList.add(caseForeignNational);

                                }

                                Collections.reverse(caseForeignNationalArrayList);
                                ((TabCases) SuperGlobals.tab4).prepareSpinner();

                            } catch(JSONException e) {

                                e.printStackTrace();
                                queryForeignNationals();

                            }

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.e(Constants.TAG_FRAGMENT_MY_COUNTRY, error.toString());
                        queryForeignNationals();

                    }
                }
        );

        Volley.newRequestQueue(view.getContext()).add(stringRequest);

    }


}
