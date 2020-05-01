package com.example.covid19tracker.PackageFragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.covid19tracker.PackageHelpers.FragmentNavigation;
import com.example.covid19tracker.PackageHelpers.UtilMethods;
import com.example.covid19tracker.PackageObjectModels.Country;
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

import java.util.ArrayList;
import java.util.List;


public class FragmentCountry extends Fragment {


    private Country selectedCountry;

    private View view;

    private Toolbar toolbar;

    private ImageView imgFlag;
    private TextView lblCountryName;
    private TextView lblIso;
    private TextView lblDateLastUpdated;

    private TextView lblNumberCases;

    private TextView lblNumberActive;
    private TextView lblNumberDeaths;
    private TextView lblNumberRecovered;
    private TextView lblNumberCritical;

    private PieChart pieChartCountry;

    private TextView lblNumberTodayCases;
    private TextView lblNumberTodayDeaths;

    private TextView lblFatalityRate;
    private TextView lblRecoveryRate;

    private Button btnTimelineCountry;


    public FragmentCountry() {}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.fragment_country, container, false);

        prepareToolbar("");
        selectedCountry = SuperGlobals.selectedCountry;

        updateViews();

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


    private void updateViews() {

        try {

            imgFlag = view.findViewById(R.id.imgFlag);
            lblCountryName = view.findViewById(R.id.lblCountryName);
            lblIso = view.findViewById(R.id.lblIso);
            lblDateLastUpdated = view.findViewById(R.id.lblDateLastUpdated);

            lblNumberCases = view.findViewById(R.id.lblNumberCases);

            lblNumberActive = view.findViewById(R.id.lblNumberActive);
            lblNumberDeaths = view.findViewById(R.id.lblNumberDeaths);
            lblNumberRecovered = view.findViewById(R.id.lblNumberRecovered);
            lblNumberCritical = view.findViewById(R.id.lblNumberCritical);

            lblNumberTodayCases = view.findViewById(R.id.lblNumberTodayCases);
            lblNumberTodayDeaths = view.findViewById(R.id.lblNumberTodayDeaths);

            lblFatalityRate = view.findViewById(R.id.lblFatalityRate);
            lblRecoveryRate = view.findViewById(R.id.lblRecoveryRate);

            btnTimelineCountry = view.findViewById(R.id.btnTimelineCountry);

            btnTimelineCountry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    goToFragmentTimeline();

                }
            });

            Glide.with(view)
                    .load(selectedCountry.getCountryInfo().getString(ConstantsVolley.FLAG))
                    .placeholder(R.drawable.unknown_flag)
                    .into(imgFlag);
            lblCountryName.setText(selectedCountry.getCountryName());
            lblIso.setText(UtilMethods.convertToIsoFormat(
                    selectedCountry.getCountryInfo().getString(ConstantsVolley.ISO_2),
                    selectedCountry.getCountryInfo().getString(ConstantsVolley.ISO_3)
            ));
            lblDateLastUpdated.setText("Last Updated: " + UtilMethods.millisecondToReadableDateFormat(selectedCountry.getUpdated()));

            UtilMethods.animateNumberWithComma(selectedCountry.getCases(), lblNumberCases);

            UtilMethods.animateNumberWithComma(selectedCountry.getActive(), lblNumberActive);
            UtilMethods.animateNumberWithComma(selectedCountry.getDeaths(), lblNumberDeaths);
            UtilMethods.animateNumberWithComma(selectedCountry.getRecovered(), lblNumberRecovered);
            UtilMethods.animateNumberWithComma(selectedCountry.getCritical(), lblNumberCritical);

            UtilMethods.animateNumberWithComma(selectedCountry.getTodayCases(), lblNumberTodayCases);
            UtilMethods.animateNumberWithComma(selectedCountry.getTodayDeaths(), lblNumberTodayDeaths);

            compute();

            preparePieChartView();

        } catch (JSONException e) {

            e.printStackTrace();

        }

    }


    private void goToFragmentTimeline() {

        SuperGlobals.selectedCountryTimeline = selectedCountry.getCountryName();
        FragmentNavigation.goToFragmentTimeline(view, SuperGlobals.currentTab);

    }


    private void compute() {

        double fatalityRate = ((double) selectedCountry.getDeaths() / (double) selectedCountry.getCases()) * 100;
        double recoveryRate = ((double) selectedCountry.getRecovered() / (double) selectedCountry.getCases()) * 100;

        UtilMethods.animateNumberWithPercent(fatalityRate, lblFatalityRate);
        UtilMethods.animateNumberWithPercent(recoveryRate, lblRecoveryRate);

    }


    private void preparePieChartView() {

        List<PieEntry> pieEntryArrayList = new ArrayList<>();
        pieEntryArrayList.add(new PieEntry(selectedCountry.getActive(), Constants.ACTIVE));
        pieEntryArrayList.add(new PieEntry(selectedCountry.getDeaths(), Constants.DEATHS));
        pieEntryArrayList.add(new PieEntry(selectedCountry.getRecovered(), Constants.RECOVERED));

        PieDataSet pieDataSet = new PieDataSet(pieEntryArrayList, "");
//        pieDataSet.setSliceSpace(8f);
        pieDataSet.setColors(new int[] {getResources().getColor(R.color.blue), getResources().getColor(R.color.red), getResources().getColor(R.color.green)});

        PieData pieData = new PieData(pieDataSet);
        pieData.setValueFormatter(new PercentFormatter(pieChartCountry));
        pieData.setDrawValues(false);

        Description description = new Description();
        description.setText("");

        pieChartCountry = view.findViewById(R.id.pieChartCountry);

        pieChartCountry.setData(pieData);
        pieChartCountry.setDescription(description);

        pieChartCountry.animateXY(Constants.ANIMATION_DURATION, Constants.ANIMATION_DURATION);

//        pieChartGlobal.getLegend().setTextSize(16f);
        pieChartCountry.getLegend().setTextColor(getResources().getColor(R.color.currentText2));

        pieChartCountry.setUsePercentValues(true);
        pieChartCountry.setDrawEntryLabels(false);

        pieChartCountry.setDrawHoleEnabled(false);
        pieChartCountry.setCenterTextSize(16f);
//        pieChartGlobal.setCenterText("Worldwide");

        pieChartCountry.invalidate();

//        pieChartCountry = view.findViewById(R.id.pieChartCountry);
//
//        Pie pie = AnyChart.pie();
//        pie.legend().fontSize(8);
//
//        List<DataEntry> dataEntryList = new ArrayList<>();
//        dataEntryList.add(new ValueDataEntry(Constants.ACTIVE, selectedCountry.getActive()));
//        dataEntryList.add(new ValueDataEntry(Constants.DEATHS, selectedCountry.getDeaths()));
//        dataEntryList.add(new ValueDataEntry(Constants.RECOVERED, selectedCountry.getRecovered()));
//
//        pie.data(dataEntryList);
//
//        pieChartCountry.setChart(pie);

    }


}
