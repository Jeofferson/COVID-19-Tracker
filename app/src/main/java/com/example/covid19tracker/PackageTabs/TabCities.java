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
import android.widget.RelativeLayout;

import com.example.covid19tracker.PackageFragments.FragmentMyCountry;
import com.example.covid19tracker.PackageObjectModels.City;
import com.example.covid19tracker.PackageRecyclerViewAdapters.CityAdapter;
import com.example.covid19tracker.R;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


public class TabCities extends Fragment {


    private List<City> cityList = new ArrayList<>();

    private CityAdapter cityAdapter;

    private View view;

    private SearchView searchView;
    private RecyclerView recyclerViewCities;

    private RelativeLayout progressBar;


    public TabCities() {}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.tab_cities, container, false);

        prepareRecyclerView();
        prepareSearchView();
        updateViews();

        return view;

    }


    private void prepareRecyclerView() {

        recyclerViewCities = view.findViewById(R.id.recyclerViewCities);
        recyclerViewCities.setLayoutManager(new LinearLayoutManager(getActivity()));

    }


    private void prepareSearchView() {

        searchView = view.findViewById(R.id.searchView);
        searchView.setMaxWidth(Integer.MAX_VALUE);

        EditText txtSearch = searchView.findViewById(androidx.appcompat.R.id.search_src_text);
        txtSearch.setHint("Search cities...");
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

                if (cityList.isEmpty()) {

                    return false;

                }

                cityAdapter.getFilter().filter(newText);

                return false;

            }
        });

    }


    private void updateViews() {

        progressBar = view.findViewById(R.id.progressBar);

    }


    public void updateRecyclerView() {

        progressBar.setVisibility(View.GONE);

        cityList.clear();

        for (LinkedHashMap.Entry<String, Integer> entry : FragmentMyCountry.cityLinkedHashMap.entrySet()) {

            String cityAddress = entry.getKey();
            int cases = entry.getValue();
            City city = new City(cityAddress, cases);

            cityList.add(city);

        }

        cityAdapter = new CityAdapter(cityList);
        recyclerViewCities.setAdapter(cityAdapter);

    }


}
