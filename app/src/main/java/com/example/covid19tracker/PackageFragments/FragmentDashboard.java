package com.example.covid19tracker.PackageFragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.covid19tracker.PackageHelpers.FragmentNavigation;
import com.example.covid19tracker.PackageHelpers.UtilMethods;
import com.example.covid19tracker.PackageObjectModels.Country;
import com.example.covid19tracker.PackageObjectModels.Date;
import com.example.covid19tracker.PackageSessionVariables.Constants;
import com.example.covid19tracker.PackageSessionVariables.ConstantsVolley;
import com.example.covid19tracker.PackageSessionVariables.SuperGlobals;
import com.example.covid19tracker.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;


public class FragmentDashboard extends Fragment {


    private int deaths;
    private int recovered;

    private Country mostCases;
    private Country mostActive;
    private Country mostDeaths;
    private Country mostRecoveries;
    private Country mostCritical;
    private Country mostTodayCases;
    private Country mostTodayDeaths;

    private ArrayList<Date> dateArrayList = new ArrayList<>();

    private JSONObject casesJSONObject;
    private JSONObject deathsJSONObject;
    private JSONObject recoveredJSONObject;
    private ArrayList<String> jsonObjectKeys = new ArrayList<>();

    private View view;

    private CardView layoutMostCases;
    private ImageView imgFlagMostCases;
    private TextView lblCountryNameMostCases;
    private TextView lblNumberMostCases;

    private CardView layoutMostActive;
    private ImageView imgFlagMostActive;
    private TextView lblCountryNameMostActive;
    private TextView lblNumberMostActive;

    private CardView layoutMostDeaths;
    private ImageView imgFlagMostDeaths;
    private TextView lblCountryNameMostDeaths;
    private TextView lblNumberMostDeaths;

    private CardView layoutMostRecoveries;
    private ImageView imgFlagMostRecoveries;
    private TextView lblCountryNameMostRecoveries;
    private TextView lblNumberMostRecoveries;

    private CardView layoutMostCritical;
    private ImageView imgFlagMostCritical;
    private TextView lblCountryNameMostCritical;
    private TextView lblNumberMostCritical;

    private CardView layoutMostTodayCases;
    private ImageView imgFlagMostTodayCases;
    private TextView lblCountryNameMostTodayCases;
    private TextView lblNumberMostTodayCases;

    private CardView layoutMostTodayDeaths;
    private ImageView imgFlagMostTodayDeaths;
    private TextView lblCountryNameMostTodayDeaths;
    private TextView lblNumberMostTodayDeaths;

    private LineChart lineChartWorld;

    private RelativeLayout progressBar;


    public FragmentDashboard() {}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        updateViews();
        queryDates();

        return view;

    }


    private void updateViews() {

        layoutMostCases = view.findViewById(R.id.layoutMostCases);
        imgFlagMostCases = view.findViewById(R.id.imgFlagMostCases);
        lblCountryNameMostCases = view.findViewById(R.id.lblCountryNameMostCases);
        lblNumberMostCases = view.findViewById(R.id.lblNumberMostCases);

        layoutMostActive = view.findViewById(R.id.layoutMostActive);
        imgFlagMostActive = view.findViewById(R.id.imgFlagMostActive);
        lblCountryNameMostActive = view.findViewById(R.id.lblCountryNameMostActive);
        lblNumberMostActive = view.findViewById(R.id.lblNumberMostActive);

        layoutMostDeaths = view.findViewById(R.id.layoutMostDeaths);
        imgFlagMostDeaths = view.findViewById(R.id.imgFlagMostDeaths);
        lblCountryNameMostDeaths = view.findViewById(R.id.lblCountryNameMostDeaths);
        lblNumberMostDeaths = view.findViewById(R.id.lblNumberMostDeaths);

        layoutMostRecoveries = view.findViewById(R.id.layoutMostRecoveries);
        imgFlagMostRecoveries = view.findViewById(R.id.imgFlagMostRecoveries);
        lblCountryNameMostRecoveries = view.findViewById(R.id.lblCountryNameMostRecoveries);
        lblNumberMostRecoveries = view.findViewById(R.id.lblNumberMostRecoveries);

        layoutMostCritical = view.findViewById(R.id.layoutMostCritical);
        imgFlagMostCritical = view.findViewById(R.id.imgFlagMostCritical);
        lblCountryNameMostCritical = view.findViewById(R.id.lblCountryNameMostCritical);
        lblNumberMostCritical = view.findViewById(R.id.lblNumberMostCritical);

        layoutMostTodayCases = view.findViewById(R.id.layoutMostTodayCases);
        imgFlagMostTodayCases = view.findViewById(R.id.imgFlagMostTodayCases);
        lblCountryNameMostTodayCases = view.findViewById(R.id.lblCountryNameMostTodayCases);
        lblNumberMostTodayCases = view.findViewById(R.id.lblNumberMostTodayCases);

        layoutMostTodayDeaths = view.findViewById(R.id.layoutMostTodayDeaths);
        imgFlagMostTodayDeaths = view.findViewById(R.id.imgFlagMostTodayDeaths);
        lblCountryNameMostTodayDeaths = view.findViewById(R.id.lblCountryNameMostTodayDeaths);
        lblNumberMostTodayDeaths = view.findViewById(R.id.lblNumberMostTodayDeaths);

        progressBar = view.findViewById(R.id.progressBar);

        layoutMostCases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFragmentCountry(ConstantsVolley.CASES);
            }
        });

        layoutMostActive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFragmentCountry(ConstantsVolley.ACTIVE);
            }
        });

        layoutMostDeaths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFragmentCountry(ConstantsVolley.DEATHS);
            }
        });

        layoutMostRecoveries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFragmentCountry(ConstantsVolley.RECOVERED);
            }
        });

        layoutMostCritical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFragmentCountry(ConstantsVolley.CRITICAL);
            }
        });

        layoutMostTodayCases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFragmentCountry(ConstantsVolley.TODAY_CASES);
            }
        });

        layoutMostTodayDeaths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFragmentCountry(ConstantsVolley.TODAY_DEATHS);
            }
        });

        queryMosts(ConstantsVolley.COUNTRIES_MOST_CASES, ConstantsVolley.CASES, imgFlagMostCases, lblCountryNameMostCases, lblNumberMostCases);

        queryMosts(ConstantsVolley.COUNTRIES_MOST_ACTIVE, ConstantsVolley.ACTIVE, imgFlagMostActive, lblCountryNameMostActive, lblNumberMostActive);
        queryMosts(ConstantsVolley.COUNTRIES_MOST_DEATHS, ConstantsVolley.DEATHS, imgFlagMostDeaths, lblCountryNameMostDeaths, lblNumberMostDeaths);
        queryMosts(ConstantsVolley.COUNTRIES_MOST_RECOVERED, ConstantsVolley.RECOVERED, imgFlagMostRecoveries, lblCountryNameMostRecoveries, lblNumberMostRecoveries);

        queryMosts(ConstantsVolley.COUNTRIES_MOST_CRITICAL, ConstantsVolley.CRITICAL, imgFlagMostCritical, lblCountryNameMostCritical, lblNumberMostCritical);
        queryMosts(ConstantsVolley.COUNTRIES_MOST_TODAY_CASES, ConstantsVolley.TODAY_CASES, imgFlagMostTodayCases, lblCountryNameMostTodayCases, lblNumberMostTodayCases);
        queryMosts(ConstantsVolley.COUNTRIES_MOST_TODAY_DEATHS, ConstantsVolley.TODAY_DEATHS, imgFlagMostTodayDeaths, lblCountryNameMostTodayDeaths, lblNumberMostTodayDeaths);

    }


    private void queryMosts(final String url, final String key, final ImageView imgFlagMost, final TextView lblCountryNameMost, final TextView lblNumberMost) {

        progressBar.setVisibility(View.VISIBLE);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (response != null) {

                            Log.e(Constants.TAG_FRAGMENT_DASHBOARD, response);

                            try {

                                JSONArray jsonArray = new JSONArray(response);

                                JSONObject jsonObject = jsonArray.getJSONObject(0);

                                int id = 0;

                                String countryName = jsonObject.getString(ConstantsVolley.COUNTRY);

                                JSONObject countryInfo = jsonObject.getJSONObject(ConstantsVolley.COUNTRY_INFO);

                                int cases = Integer.parseInt(jsonObject.getString(ConstantsVolley.CASES).equalsIgnoreCase("null") ? "0" : jsonObject.getString(ConstantsVolley.CASES));
                                int todayCases = Integer.parseInt(jsonObject.getString(ConstantsVolley.TODAY_CASES).equalsIgnoreCase("null") ? "0" : jsonObject.getString(ConstantsVolley.TODAY_CASES));

                                int deaths = Integer.parseInt(jsonObject.getString(ConstantsVolley.DEATHS).equalsIgnoreCase("null") ? "0" : jsonObject.getString(ConstantsVolley.DEATHS));
                                int todayDeaths = Integer.parseInt(jsonObject.getString(ConstantsVolley.TODAY_DEATHS).equalsIgnoreCase("null") ? "0" : jsonObject.getString(ConstantsVolley.TODAY_DEATHS));

                                int recovered = Integer.parseInt(jsonObject.getString(ConstantsVolley.RECOVERED).equalsIgnoreCase("null") ? "0" : jsonObject.getString(ConstantsVolley.RECOVERED));
                                int active = Integer.parseInt(jsonObject.getString(ConstantsVolley.ACTIVE).equalsIgnoreCase("null") ? "0" : jsonObject.getString(ConstantsVolley.ACTIVE));
                                int critical = Integer.parseInt(jsonObject.getString(ConstantsVolley.CRITICAL).equalsIgnoreCase("null") ? "0" : jsonObject.getString(ConstantsVolley.CRITICAL));

                                double casesPerOneMillion = Double.parseDouble(jsonObject.getString(ConstantsVolley.CASES_PER_ONE_MILLION).equalsIgnoreCase("null") ? "0.0" : jsonObject.getString(ConstantsVolley.CASES_PER_ONE_MILLION));
                                double deathsPerOneMillion = Double.parseDouble(jsonObject.getString(ConstantsVolley.DEATHS_PER_ONE_MILLION).equalsIgnoreCase("null") ? "0.0" : jsonObject.getString(ConstantsVolley.DEATHS_PER_ONE_MILLION));

                                long updated = Long.parseLong(jsonObject.getString(ConstantsVolley.UPDATED).equalsIgnoreCase("null") ? "0.0" : jsonObject.getString(ConstantsVolley.UPDATED));

                                int number = Integer.parseInt(jsonObject.getString(key));

                                Country country = new Country(
                                        id,
                                        countryName,
                                        countryInfo,
                                        cases,
                                        todayCases,
                                        deaths,
                                        todayDeaths,
                                        recovered,
                                        active,
                                        critical,
                                        casesPerOneMillion,
                                        deathsPerOneMillion,
                                        updated
                                );

                                Glide.with(view)
                                        .load(countryInfo.getString(ConstantsVolley.FLAG))
                                        .placeholder(R.drawable.unknown_flag)
                                        .into(imgFlagMost);
                                lblCountryNameMost.setText(countryName);
                                UtilMethods.animateNumberWithComma(number, lblNumberMost);

                                switch (key) {
                                    case ConstantsVolley.CASES:
                                        mostCases = country;
                                        break;

                                    case ConstantsVolley.ACTIVE:
                                        mostActive = country;
                                        break;

                                    case ConstantsVolley.DEATHS:
                                        mostDeaths = country;
                                        break;

                                    case ConstantsVolley.RECOVERED:
                                        mostRecoveries = country;
                                        break;

                                    case ConstantsVolley.CRITICAL:
                                        mostCritical = country;
                                        break;

                                    case ConstantsVolley.TODAY_CASES:
                                        mostTodayCases = country;
                                        break;

                                    case ConstantsVolley.TODAY_DEATHS:
                                        mostTodayDeaths = country;
                                        break;

                                }

                                progressBar.setVisibility(View.GONE);

                            } catch(JSONException e) {

                                e.printStackTrace();
                                queryMosts(url, key, imgFlagMost, lblCountryNameMost, lblNumberMost);

                            }

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.e(Constants.TAG_FRAGMENT_DASHBOARD, error.toString());
                        queryMosts(url, key, imgFlagMost, lblCountryNameMost, lblNumberMost);

                    }
                }
        );

        Volley.newRequestQueue(getActivity()).add(stringRequest);

    }


    private void goToFragmentCountry(String key) {

        switch (key) {
            case ConstantsVolley.CASES:
                SuperGlobals.selectedCountry = mostCases;
                break;

            case ConstantsVolley.ACTIVE:
                SuperGlobals.selectedCountry = mostActive;
                break;

            case ConstantsVolley.DEATHS:
                SuperGlobals.selectedCountry = mostDeaths;
                break;

            case ConstantsVolley.RECOVERED:
                SuperGlobals.selectedCountry = mostRecoveries;
                break;

            case ConstantsVolley.CRITICAL:
                SuperGlobals.selectedCountry = mostCritical;
                break;

            case ConstantsVolley.TODAY_CASES:
                SuperGlobals.selectedCountry = mostTodayCases;
                break;

            case ConstantsVolley.TODAY_DEATHS:
                SuperGlobals.selectedCountry = mostTodayDeaths;
                break;

        }

        FragmentNavigation.goToFragmentCountry(view, Constants.DASHBOARD);

    }


    private void queryDates() {

        progressBar.setVisibility(View.VISIBLE);

        dateArrayList.clear();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, ConstantsVolley.HISTORICAL_WORLD,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (response != null) {

                            Log.e(Constants.TAG_FRAGMENT_DASHBOARD, response);

                            try {

                                JSONObject jsonObject = new JSONObject(response);

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

                                Collections.reverse(dateArrayList);

                                queryWorldStatusToday();

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

                        Log.e(Constants.TAG_FRAGMENT_DASHBOARD, error.toString());
                        queryDates();

                    }
                }
        );

        Volley.newRequestQueue(view.getContext()).add(stringRequest);

    }


    private void queryWorldStatusToday() {

        progressBar.setVisibility(View.VISIBLE);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, ConstantsVolley.CURRENT_STATUS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (response != null) {

                            Log.e(Constants.TAG_FRAGMENT_DASHBOARD, response);

                            try {

                                JSONObject jsonObject = new JSONObject(response);

                                deaths = Integer.parseInt(jsonObject.getString(ConstantsVolley.DEATHS).equalsIgnoreCase("null") ? "0" : jsonObject.getString(ConstantsVolley.DEATHS));
                                recovered = Integer.parseInt(jsonObject.getString(ConstantsVolley.RECOVERED).equalsIgnoreCase("null") ? "0" : jsonObject.getString(ConstantsVolley.RECOVERED));

                                prepareLineChartView();

                            } catch(JSONException e) {

                                e.printStackTrace();
                                queryWorldStatusToday();

                            }

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.e(Constants.TAG_FRAGMENT_DASHBOARD, error.toString());
                        queryWorldStatusToday();

                    }
                }
        );

        Volley.newRequestQueue(view.getContext()).add(stringRequest);

    }


    private void prepareLineChartView() {

        ArrayList<Entry> deathsArrayList = new ArrayList<>();
        ArrayList<Entry> recoveredArrayList = new ArrayList<>();

        for (int i = 0; i < dateArrayList.size(); i++) {

            Date date = dateArrayList.get(i);

            deathsArrayList.add(new Entry(i, date.getDeaths()));
            recoveredArrayList.add(new Entry(i, date.getRecovered()));

        }

        if (deaths != 0) {

            deathsArrayList.add(new Entry(deathsArrayList.size(), deaths));

        }

        if (recovered != 0) {

            recoveredArrayList.add(new Entry(recoveredArrayList.size(), recovered));

        }

        Log.e("jeo d", deaths + "");
        Log.e("jeo r", recovered + "");

        LineDataSet lineDataSet1 = new LineDataSet(deathsArrayList, "Deaths");
        lineDataSet1.setLineWidth(3f);
        lineDataSet1.setColor(getResources().getColor(R.color.red));
        lineDataSet1.setFillAlpha(110);
        lineDataSet1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        lineDataSet1.setDrawCircles(false);
        lineDataSet1.setDrawValues(false);

        LineDataSet lineDataSet2 = new LineDataSet(recoveredArrayList, "Recovered");
        lineDataSet2.setLineWidth(3f);
        lineDataSet2.setColor(getResources().getColor(R.color.green));
        lineDataSet2.setFillAlpha(110);
        lineDataSet2.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        lineDataSet2.setDrawCircles(false);
        lineDataSet2.setDrawValues(false);

        ArrayList<ILineDataSet> iLineDataSetArrayList = new ArrayList<>();
        iLineDataSetArrayList.add(lineDataSet1);
        iLineDataSetArrayList.add(lineDataSet2);

        LineData lineData = new LineData(iLineDataSetArrayList);

        Description description = new Description();
        description.setText("");

        lineChartWorld = view.findViewById(R.id.lineChartWorld);

        // Animation
        lineChartWorld.animateXY(Constants.ANIMATION_DURATION, Constants.ANIMATION_DURATION);

        // Get axis
        XAxis xAxis = lineChartWorld.getXAxis();
        YAxis yAxisLeft = lineChartWorld.getAxisLeft();
        YAxis yAxisRight = lineChartWorld.getAxisRight();

        // Set data
        lineChartWorld.setData(lineData);
        lineChartWorld.setDescription(description);

        // Set legends
        lineChartWorld.getLegend().setTextColor(getResources().getColor(R.color.currentText2));

        // Set labels
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawLabels(false);
        yAxisLeft.setDrawLabels(false);
        yAxisRight.setTextColor(getResources().getColor(R.color.currentText2));

        // Set borders
        xAxis.setDrawAxisLine(false);
        yAxisLeft.setDrawAxisLine(false);
        yAxisRight.setDrawAxisLine(false);

        // Set grid lines
//        xAxis.setDrawGridLines(false);
        xAxis.setGridColor(getResources().getColor(R.color.currentText2));
//        yAxisLeft.setDrawGridLines(false);
//        yAxisRight.setDrawGridLines(false);
        yAxisLeft.setGridColor(getResources().getColor(R.color.currentText2));
        yAxisRight.setGridColor(getResources().getColor(R.color.currentText2));

        // Set behavior
        lineChartWorld.getData().setHighlightEnabled(false);
        lineChartWorld.setDoubleTapToZoomEnabled(false);
        lineChartWorld.setDragEnabled(true);
        lineChartWorld.setScaleEnabled(true);

        // Show
        lineChartWorld.invalidate();

        progressBar.setVisibility(View.GONE);

    }


}
