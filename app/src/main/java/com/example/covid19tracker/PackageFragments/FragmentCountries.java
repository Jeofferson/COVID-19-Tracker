package com.example.covid19tracker.PackageFragments;

import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.covid19tracker.PackageRecyclerViewAdapters.CountryAdapter;
import com.example.covid19tracker.PackageObjectModels.Country;
import com.example.covid19tracker.PackageSessionVariables.Constants;
import com.example.covid19tracker.PackageSessionVariables.ConstantsVolley;
import com.example.covid19tracker.PackageSessionVariables.SuperGlobals;
import com.example.covid19tracker.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;


public class FragmentCountries extends Fragment {


    private String sortBy;
    private String queryUrl;

    private CountryAdapter countryAdapter;

    private View view;

    private Spinner spinner;
    private SearchView searchView;

    private RecyclerView recyclerViewCountries;

    private RelativeLayout progressBar;


    public FragmentCountries() {}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_countries, container, false);

        prepareRecyclerView();
        prepareSpinner();
        prepareSearchView();
        updateViews();

        return view;

    }


    private void prepareRecyclerView() {

        recyclerViewCountries = view.findViewById(R.id.recyclerViewCountries);
        recyclerViewCountries.setLayoutManager(new LinearLayoutManager(getActivity()));

    }


    private void prepareSpinner() {

        spinner = view.findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(view.getContext(), R.array.sortOptions, R.layout.tool_spinner_item);
//        adapter.setDropDownViewResource(R.layout.tool_spinner_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                sortCountries();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

    }


    private void prepareSearchView() {

        searchView = view.findViewById(R.id.searchView);
        searchView.setMaxWidth(Integer.MAX_VALUE);

        EditText txtSearch = searchView.findViewById(androidx.appcompat.R.id.search_src_text);
        txtSearch.setHint("Search countries...");
        txtSearch.setHintTextColor(getResources().getColor(R.color.currentText2));
        txtSearch.setTextColor(getResources().getColor(R.color.currentText));

        View searchPlate = searchView.findViewById(androidx.appcompat.R.id.search_plate);
        searchPlate.setBackgroundColor(Color.parseColor("#00000000"));

        ImageView searchIcon = searchView.findViewById(androidx.appcompat.R.id.search_button);
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

                if (SuperGlobals.countryArrayList.isEmpty()) {

                    return false;

                }

                countryAdapter.getFilter().filter(newText);

                return false;

            }
        });

    }


    private void updateViews() {

        progressBar = view.findViewById(R.id.progressBar);

    }


    private void sortCountries() {

        sortBy = spinner.getSelectedItem().toString();

        switch (sortBy) {

            case "Cases":
                queryUrl = ConstantsVolley.COUNTRIES_MOST_CASES;
                break;

            case "Country":
                queryUrl = ConstantsVolley.COUNTRIES;
                break;

            case "Active":
                queryUrl = ConstantsVolley.COUNTRIES_MOST_ACTIVE;
                break;

            case "Deaths":
                queryUrl = ConstantsVolley.COUNTRIES_MOST_DEATHS;
                break;

            case "Recovered":
                queryUrl = ConstantsVolley.COUNTRIES_MOST_RECOVERED;
                break;

            case "Critical":
                queryUrl = ConstantsVolley.COUNTRIES_MOST_CRITICAL;
                break;

            case "Today's Cases":
                queryUrl = ConstantsVolley.COUNTRIES_MOST_TODAY_CASES;
                break;

            case "Today's Deaths":
                queryUrl = ConstantsVolley.COUNTRIES_MOST_TODAY_DEATHS;
                break;

            default:
                return;

        }

        queryCountries();

    }


    private void queryCountries() {

        progressBar.setVisibility(View.VISIBLE);

        SuperGlobals.countryArrayList.clear();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, queryUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (response != null) {

                            Log.e(Constants.TAG_FRAGMENT_COUNTRIES, response);

                            try {

                                JSONArray jsonArray = new JSONArray(response);

                                int counter = 0;
                                int reverseCounter = jsonArray.length() - 1;

                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                                    int id = !sortBy.equalsIgnoreCase("Country") ? counter : reverseCounter;

                                    String countryName = jsonObject.getString(ConstantsVolley.COUNTRY);
                                    if (countryName.equalsIgnoreCase("World")) { continue; }

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

                                    SuperGlobals.countryArrayList.add(country);

                                    counter++;
                                    reverseCounter--;

                                }

                                if (sortBy.equalsIgnoreCase("Country")) {

                                    Collections.reverse(SuperGlobals.countryArrayList);

                                }

                                updateRecyclerView();

                                progressBar.setVisibility(View.GONE);

                            } catch(JSONException e) {

                                e.printStackTrace();
                                queryCountries();

                            }

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.e(Constants.TAG_FRAGMENT_COUNTRIES, error.toString());
                        queryCountries();

                    }
                }
        );

        Volley.newRequestQueue(getActivity()).add(stringRequest);

    }


    private void updateRecyclerView() {

        countryAdapter = new CountryAdapter(SuperGlobals.countryArrayList);
        recyclerViewCountries.setAdapter(countryAdapter);

    }


}
