package com.example.covid19tracker.PackageFragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.covid19tracker.PackageRecyclerViewAdapters.DateAdapter;
import com.example.covid19tracker.PackageObjectModels.Date;
import com.example.covid19tracker.PackageSessionVariables.Constants;
import com.example.covid19tracker.PackageSessionVariables.ConstantsVolley;
import com.example.covid19tracker.PackageSessionVariables.SuperGlobals;
import com.example.covid19tracker.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;


public class FragmentTimeline extends Fragment {


    private String selectedCountryTimeline;

    private ArrayList<Date> dateArrayList = new ArrayList<>();

    private JSONObject casesJSONObject;
    private JSONObject deathsJSONObject;
    private JSONObject recoveredJSONObject;
    private ArrayList<String> jsonObjectKeys = new ArrayList<>();

    private View view;

    private Toolbar toolbar;

    private RecyclerView recyclerViewTimeline;

    private RelativeLayout progressBar;


    public FragmentTimeline() {}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_timeline, container, false);

        selectedCountryTimeline = SuperGlobals.selectedCountryTimeline;

        prepareToolbar(selectedCountryTimeline.equals(Constants.WORLD) ? "World Timeline" : selectedCountryTimeline);
        prepareRecyclerView();
        updateViews();

        queryDates();

        return view;

    }


    private void prepareToolbar(String title) {

        toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity)view.getContext()).setSupportActionBar(toolbar);

        ((AppCompatActivity)view.getContext()).getSupportActionBar().setTitle(title);
        setHasOptionsMenu(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                ((Activity) view.getContext()).onBackPressed();
                return true;

        }

        return super.onOptionsItemSelected(item);

    }


    private void prepareRecyclerView() {

        recyclerViewTimeline = view.findViewById(R.id.recyclerViewTimeline);
        recyclerViewTimeline.setLayoutManager(new LinearLayoutManager(getActivity()));

    }


    private void updateViews() {

        progressBar = view.findViewById(R.id.progressBar);

    }


    private void queryDates() {

        progressBar.setVisibility(View.VISIBLE);

        dateArrayList.clear();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, selectedCountryTimeline.equals("World") ? ConstantsVolley.HISTORICAL_WORLD : ConstantsVolley.HISTORICAL + selectedCountryTimeline,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (response != null) {

                            Log.e(Constants.TAG_FRAGMENT_TIMELINE, response);

                            try {

                                JSONObject jsonObject = new JSONObject(response);

                                if (!selectedCountryTimeline.equals("World")) {

                                    jsonObject = jsonObject.getJSONObject(ConstantsVolley.TIMELINE);

                                }

                                casesJSONObject = jsonObject.getJSONObject(ConstantsVolley.CASES);
                                deathsJSONObject = jsonObject.getJSONObject(ConstantsVolley.DEATHS);
                                recoveredJSONObject = jsonObject.getJSONObject(ConstantsVolley.RECOVERED);

                                Iterator<String> keys = casesJSONObject.keys();
                                while(keys.hasNext()) {

                                    String key = keys.next();
                                    jsonObjectKeys.add(key);

                                }
                                Collections.reverse(jsonObjectKeys);

                                for (int i = 0; i < jsonObjectKeys.size(); i++) {

                                    String jsonObjectKey = jsonObjectKeys.get(i);

                                    int id = i;

                                    String dateString = jsonObjectKey;

                                    int cases = Integer.parseInt(casesJSONObject.getString(jsonObjectKey).equalsIgnoreCase("null") ? "0" : casesJSONObject.getString(jsonObjectKey));
                                    int deaths = Integer.parseInt(deathsJSONObject.getString(jsonObjectKey).equalsIgnoreCase("null") ? "0" : deathsJSONObject.getString(jsonObjectKey));
                                    int recovered = Integer.parseInt(recoveredJSONObject.getString(jsonObjectKey).equalsIgnoreCase("null") ? "0" : recoveredJSONObject.getString(jsonObjectKey));

                                    Date date = new Date(id, dateString, cases, deaths, recovered);

                                    dateArrayList.add(date);

                                }

                                updateRecyclerView();

                                progressBar.setVisibility(View.GONE);

                            } catch(JSONException e) {

                                e.printStackTrace();
                                queryDates();

                            }

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.e(Constants.TAG_FRAGMENT_COUNTRIES, error.toString());
                        Toast.makeText(view.getContext(), "No Historical Data Available", Toast.LENGTH_SHORT).show();

                    }
                }
        );

        Volley.newRequestQueue(view.getContext()).add(stringRequest);

    }


    private void updateRecyclerView() {

        DateAdapter dateAdapter = new DateAdapter(dateArrayList);
        recyclerViewTimeline.setAdapter(dateAdapter);

    }


}
