package com.example.covid19tracker.PackageFragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
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
import com.example.covid19tracker.PackageSessionVariables.Constants;
import com.example.covid19tracker.PackageSessionVariables.ConstantsVolley;
import com.example.covid19tracker.PackageSessionVariables.SuperGlobals;
import com.example.covid19tracker.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class FragmentWorld extends Fragment {


    private View view;

    private TextView lblDateLastUpdated;

    private TextView lblTotalCases;

    private TextView lblTotalActive;
    private TextView lblTotalDeaths;
    private TextView lblTotalRecovered;

    private TextView lblTotalCritical;
    private TextView lblTotalTodayCases;
    private TextView lblTotalTodayDeaths;

    private PieChart pieChartGlobal;

    private TextView lblFatalityRate, lblRecoveryRate;

    private Button btnTimelineWorld;

    private RelativeLayout progressBar;


    public FragmentWorld() {}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_world, container, false);

        updateViews();

        queryCurrentStatus();

        return  view;

    }


    private void updateViews() {

        lblDateLastUpdated = view.findViewById(R.id.lblDateLastUpdated);

        lblTotalCases = view.findViewById(R.id.lblTotalCases);

        lblTotalActive = view.findViewById(R.id.lblTotalActive);
        lblTotalDeaths = view.findViewById(R.id.lblTotalDeaths);
        lblTotalRecovered = view.findViewById(R.id.lblTotalRecovered);

        lblTotalCritical = view.findViewById(R.id.lblTotalCritical);
        lblTotalTodayCases = view.findViewById(R.id.lblTotalTodayCases);
        lblTotalTodayDeaths = view.findViewById(R.id.lblTotalTodayDeaths);

        lblFatalityRate = view.findViewById(R.id.lblFatalityRate);
        lblRecoveryRate = view.findViewById(R.id.lblRecoveryRate);

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

        SuperGlobals.selectedCountryTimeline = "World";
        FragmentNavigation.goToFragmentTimeline(view, Constants.WORLD);

    }


    private void queryCurrentStatus() {

        progressBar.setVisibility(View.VISIBLE);

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        StringRequest stringRequest = new StringRequest(Request.Method.GET, ConstantsVolley.WORLD_STATUS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONObject jsonObject = new JSONObject(response);

                            String updated = "Last Updated: " + UtilMethods.millisecondToReadableDateFormat(Long.parseLong(jsonObject.getString(ConstantsVolley.UPDATED)));

                            SuperGlobals.totalCases = Integer.parseInt(jsonObject.getString(ConstantsVolley.CASES));

                            SuperGlobals.totalActive = Integer.parseInt(jsonObject.getString(ConstantsVolley.ACTIVE));
                            SuperGlobals.totalDeaths = Integer.parseInt(jsonObject.getString(ConstantsVolley.DEATHS));
                            SuperGlobals.totalRecovered = Integer.parseInt(jsonObject.getString(ConstantsVolley.RECOVERED));

                            SuperGlobals.totalCritical = Integer.parseInt(jsonObject.getString(ConstantsVolley.CRITICAL));
                            SuperGlobals.totalTodayCases = Integer.parseInt(jsonObject.getString(ConstantsVolley.TODAY_CASES));
                            SuperGlobals.totalTodayDeaths = Integer.parseInt(jsonObject.getString(ConstantsVolley.TODAY_DEATHS));

                            UtilMethods.animateNumberWithComma(SuperGlobals.totalCases, lblTotalCases);

                            UtilMethods.animateNumberWithComma(SuperGlobals.totalActive, lblTotalActive);
                            UtilMethods.animateNumberWithComma(SuperGlobals.totalDeaths, lblTotalDeaths);
                            UtilMethods.animateNumberWithComma(SuperGlobals.totalRecovered, lblTotalRecovered);

                            UtilMethods.animateNumberWithComma(SuperGlobals.totalCritical, lblTotalCritical);
                            UtilMethods.animateNumberWithComma(SuperGlobals.totalTodayCases, lblTotalTodayCases);
                            UtilMethods.animateNumberWithComma(SuperGlobals.totalTodayDeaths, lblTotalTodayDeaths);

                            lblDateLastUpdated.setText(updated);

                            compute();

                            preparePieChartView();

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


    private void compute() {

        double fatalityRate = ((double) SuperGlobals.totalDeaths / (double) SuperGlobals.totalCases) * 100;
        double recoveryRate = ((double) SuperGlobals.totalRecovered / (double) SuperGlobals.totalCases) * 100;

        UtilMethods.animateNumberWithPercent(fatalityRate, lblFatalityRate);
        UtilMethods.animateNumberWithPercent(recoveryRate, lblRecoveryRate);

    }


    private void preparePieChartView() {

        List<PieEntry> pieEntryArrayList = new ArrayList<>();
        pieEntryArrayList.add(new PieEntry(SuperGlobals.totalActive, Constants.ACTIVE));
        pieEntryArrayList.add(new PieEntry(SuperGlobals.totalDeaths, Constants.DEATHS));
        pieEntryArrayList.add(new PieEntry(SuperGlobals.totalRecovered, Constants.RECOVERED));

        PieDataSet pieDataSet = new PieDataSet(pieEntryArrayList, "");
//        pieDataSet.setSliceSpace(8f);
        pieDataSet.setColors(new int[] {getResources().getColor(R.color.blue), getResources().getColor(R.color.red), getResources().getColor(R.color.green)});

        PieData pieData = new PieData(pieDataSet);
        pieData.setValueFormatter(new PercentFormatter(pieChartGlobal));
        pieData.setDrawValues(false);

        Description description = new Description();
        description.setText("");

        pieChartGlobal = view.findViewById(R.id.pieChartGlobal);

        pieChartGlobal.setData(pieData);
        pieChartGlobal.setDescription(description);

        pieChartGlobal.animateXY(Constants.ANIMATION_DURATION, Constants.ANIMATION_DURATION);

//        pieChartGlobal.getLegend().setTextSize(16f);
        pieChartGlobal.getLegend().setTextColor(getResources().getColor(R.color.currentText2));

        pieChartGlobal.setUsePercentValues(true);
        pieChartGlobal.setDrawEntryLabels(false);

//        pieChartGlobal.setDrawHoleEnabled(false);
        pieChartGlobal.setTransparentCircleRadius(0f);
        pieChartGlobal.setHoleColor(Color.TRANSPARENT);
        pieChartGlobal.setCenterTextSize(16f);
        pieChartGlobal.setCenterText("All\nCases");
        pieChartGlobal.setCenterTextColor(getResources().getColor(R.color.currentText));

        pieChartGlobal.invalidate();

        progressBar.setVisibility(View.GONE);

    }


}
