package com.example.covid19tracker.PackageTabs;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.covid19tracker.PackageHelpers.FragmentNavigation;
import com.example.covid19tracker.PackageHelpers.UtilMethods;
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
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;


public class TabDashboard extends Fragment {


    private int deaths;
    private int recovered;

    private ArrayList<Date> dateArrayList = new ArrayList<>();

    private JSONObject casesJSONObject;
    private JSONObject deathsJSONObject;
    private JSONObject recoveredJSONObject;
    private ArrayList<String> jsonObjectKeys = new ArrayList<>();

    private View view;

    private TextView lblDateLastUpdated;

    private TextView lblNumberCases;

    private TextView lblNumberDeaths;
    private TextView lblNumberRecovered;

    private LineChart lineChartMyCountry;

    private TextView lblNumberTests;
    private TextView lblNumberTodayCases;
    private TextView lblNumberTodayDeaths;

    private Button btnTimelineWorld;

    private RelativeLayout progressBar;


    public TabDashboard() {}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.tab_dashboard, container, false);

        updateViews();

        queryCurrentStatus();

        return view;

    }


    private void updateViews() {

        lblDateLastUpdated = view.findViewById(R.id.lblDateLastUpdated);

        lblNumberCases = view.findViewById(R.id.lblNumberCases);

        lblNumberDeaths = view.findViewById(R.id.lblNumberDeaths);
        lblNumberRecovered = view.findViewById(R.id.lblNumberRecovered);

        lblNumberTests = view.findViewById(R.id.lblNumberTests);
        lblNumberTodayCases = view.findViewById(R.id.lblNumberTodayCases);
        lblNumberTodayDeaths = view.findViewById(R.id.lblNumberTodayDeaths);

        btnTimelineWorld = view.findViewById(R.id.btnTimelineWorld);

        progressBar = view.findViewById(R.id.progressBar);

        btnTimelineWorld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                goToFragmentTimeline();

            }
        });

    }


    private void goToFragmentTimeline() {

        SuperGlobals.selectedCountryTimeline = "Philippines";
        FragmentNavigation.goToFragmentTimeline(view, Constants.MY_COUNTRY);

    }


    private void queryCurrentStatus() {

        progressBar.setVisibility(View.VISIBLE);

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        StringRequest stringRequest = new StringRequest(Request.Method.GET, ConstantsVolley.MY_COUNTRY_STATUS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONObject jsonObject = new JSONObject(response);

                            String updated = UtilMethods.millisecondToReadableDateFormat(Long.parseLong(jsonObject.getString(ConstantsVolley.UPDATED)));

                            int numberCases = Integer.parseInt(jsonObject.getString(ConstantsVolley.CASES));

                            deaths = Integer.parseInt(jsonObject.getString(ConstantsVolley.DEATHS));
                            recovered = Integer.parseInt(jsonObject.getString(ConstantsVolley.RECOVERED));

                            int numberTests = Integer.parseInt(jsonObject.getString(ConstantsVolley.TESTS));
                            int numberTodayCases = Integer.parseInt(jsonObject.getString(ConstantsVolley.TODAY_CASES));
                            int numberTodayDeaths = Integer.parseInt(jsonObject.getString(ConstantsVolley.TODAY_DEATHS));

                            UtilMethods.animateNumberWithComma(numberCases, lblNumberCases);

                            UtilMethods.animateNumberWithComma(deaths, lblNumberDeaths);
                            UtilMethods.animateNumberWithComma(recovered, lblNumberRecovered);

                            UtilMethods.animateNumberWithComma(numberTests, lblNumberTests);
                            UtilMethods.animateNumberWithComma(numberTodayCases, lblNumberTodayCases);
                            UtilMethods.animateNumberWithComma(numberTodayDeaths, lblNumberTodayDeaths);

                            lblDateLastUpdated.setText(updated);

                            queryDates();

                        } catch (JSONException e) {

                            e.printStackTrace();
                            queryCurrentStatus();

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.e(Constants.TAG_FRAGMENT_WORLD, error.toString());
                        queryCurrentStatus();

                    }
                }
        );

        requestQueue.add(stringRequest);

    }


    private void queryDates() {

        progressBar.setVisibility(View.VISIBLE);

        dateArrayList.clear();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, ConstantsVolley.HISTORICAL + Constants.MY_COUNTRY_NAME,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (response != null) {

                            Log.e(Constants.TAG_FRAGMENT_MY_COUNTRY, response);

                            try {

                                JSONObject jsonObject = new JSONObject(response);

                                jsonObject = jsonObject.getJSONObject(ConstantsVolley.TIMELINE);

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

                                prepareLineChartView();

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
                        queryDates();

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
        lineDataSet1.setLineWidth(5f);
        lineDataSet1.setColor(getResources().getColor(R.color.red));
        lineDataSet1.setFillAlpha(110);
        lineDataSet1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        lineDataSet1.setDrawCircles(false);
        lineDataSet1.setDrawValues(false);

        LineDataSet lineDataSet2 = new LineDataSet(recoveredArrayList, "Recovered");
        lineDataSet2.setLineWidth(5f);
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
        description.setText(String.format("*chart data from the last %s days", deathsArrayList.size()));
        description.setTextColor(getResources().getColor(R.color.currentText2));

        lineChartMyCountry = view.findViewById(R.id.lineChartMyCountry);

        // Animation
        lineChartMyCountry.animateXY(Constants.ANIMATION_DURATION, Constants.ANIMATION_DURATION);

        // Get axis
        XAxis xAxis = lineChartMyCountry.getXAxis();
        YAxis yAxisLeft = lineChartMyCountry.getAxisLeft();
        YAxis yAxisRight = lineChartMyCountry.getAxisRight();

        // Set data
        lineChartMyCountry.setData(lineData);
        lineChartMyCountry.setDescription(description);

        // Set legends
        lineChartMyCountry.getLegend().setTextColor(getResources().getColor(R.color.currentText2));

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
        lineChartMyCountry.getData().setHighlightEnabled(false);
        lineChartMyCountry.setDoubleTapToZoomEnabled(false);
        lineChartMyCountry.setDragEnabled(true);
        lineChartMyCountry.setScaleEnabled(true);

        // Show
        lineChartMyCountry.invalidate();

        progressBar.setVisibility(View.GONE);

    }


}
