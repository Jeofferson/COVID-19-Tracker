package com.example.covid19tracker.PackageTabs;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.example.covid19tracker.PackageFragments.FragmentMyCountry;
import com.example.covid19tracker.PackageObjectModels.Facility;
import com.example.covid19tracker.PackageRecyclerViewAdapters.FacilityAdapter;
import com.example.covid19tracker.R;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


public class TabFacilities extends Fragment {


    private List<Facility> facilityList = new ArrayList<>();

    private FacilityAdapter facilityAdapter;

    private View view;

    private SearchView searchView;
    private RecyclerView recyclerViewFacilities;

    private RelativeLayout progressBar;


    public TabFacilities() {}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.tab_facilities, container, false);

        prepareRecyclerView();
        prepareSearchView();
        updateViews();

        return view;

    }


    private void prepareRecyclerView() {

        recyclerViewFacilities = view.findViewById(R.id.recyclerViewFacilities);
        recyclerViewFacilities.setLayoutManager(new LinearLayoutManager(getActivity()));

    }


    private void prepareSearchView() {

        searchView = view.findViewById(R.id.searchView);
        searchView.setMaxWidth(Integer.MAX_VALUE);

        EditText txtSearch = searchView.findViewById(androidx.appcompat.R.id.search_src_text);
        txtSearch.setHint("Search facilities...");
        txtSearch.setHintTextColor(getResources().getColor(R.color.currentText2));
        txtSearch.setTextColor(getResources().getColor(R.color.currentText));

        View searchPlate = searchView.findViewById(androidx.appcompat.R.id.search_plate);
        searchPlate.setBackgroundColor(Color.parseColor("#00000000"));

        ImageView searchIcon = searchView.findViewById(androidx.appcompat.R.id.search_mag_icon);
        Drawable whiteSearchIcon = searchIcon.getDrawable();
        whiteSearchIcon.setTint(getResources().getColor(R.color.currentText));
        searchIcon.setImageDrawable(whiteSearchIcon);

        ImageView closeIcon = searchView.findViewById(androidx.appcompat.R.id.search_close_btn);
        Drawable whiteCloseIcon = closeIcon.getDrawable();
        whiteCloseIcon.setTint(getResources().getColor(R.color.currentText));
        closeIcon.setImageDrawable(whiteCloseIcon);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if (facilityList.isEmpty()) {

                    return false;

                }

                facilityAdapter.getFilter().filter(newText);

                return false;

            }
        });

    }


    private void updateViews() {

        progressBar = view.findViewById(R.id.progressBar);

    }


    public void updateRecyclerView() {

        progressBar.setVisibility(View.GONE);

        facilityList.clear();

        for (LinkedHashMap.Entry<String, Integer> entry : FragmentMyCountry.facilityLinkedHashMap.entrySet()) {

            String facilityName = entry.getKey();
            int cases = entry.getValue();
            Facility facility = new Facility(facilityName, cases);

            facilityList.add(facility);

        }

        facilityAdapter = new FacilityAdapter(facilityList);
        recyclerViewFacilities.setAdapter(facilityAdapter);

    }


}
