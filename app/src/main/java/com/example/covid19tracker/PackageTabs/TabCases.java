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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.example.covid19tracker.PackageFragments.FragmentMyCountry;
import com.example.covid19tracker.PackageRecyclerViewAdapters.CaseAdapter;
import com.example.covid19tracker.PackageRecyclerViewAdapters.CaseForeignNationalAdapter;
import com.example.covid19tracker.PackageRecyclerViewAdapters.CaseOfwAdapter;
import com.example.covid19tracker.R;


public class TabCases extends Fragment {


    private CaseAdapter caseAdapter;
    private CaseOfwAdapter caseOfwAdapter;
    private CaseForeignNationalAdapter caseForeignNationalAdapter;

    private View view;

    private Spinner spinner;
    private SearchView searchView;

    private RecyclerView recyclerViewCases;

    private RelativeLayout progressBar;


    public TabCases() {}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.tab_cases, container, false);

        prepareRecyclerView();
        updateViews();

        return view;

    }


    private void prepareRecyclerView() {

        recyclerViewCases = view.findViewById(R.id.recyclerViewCases);
        recyclerViewCases.setLayoutManager(new LinearLayoutManager(getActivity()));

    }


    public void prepareSpinner() {

        progressBar.setVisibility(View.GONE);

        spinner = view.findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(view.getContext(), R.array.myCountryCasesType, R.layout.tool_spinner_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (spinner.getSelectedItem().toString().equalsIgnoreCase(getResources().getString(R.string.confirmed))) {

                    updateRecyclerView(0);
                    prepareSearchView(0);

                } else if (spinner.getSelectedItem().toString().equalsIgnoreCase(getResources().getString(R.string.ofw))) {

                    updateRecyclerView(1);
                    prepareSearchView(1);

                } else if (spinner.getSelectedItem().toString().equalsIgnoreCase(getResources().getString(R.string.fn))) {

                    updateRecyclerView(2);
                    prepareSearchView(2);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

    }


    private void prepareSearchView(final int type) {

        searchView = view.findViewById(R.id.searchView);
        searchView.setMaxWidth(Integer.MAX_VALUE);

        EditText txtSearch = searchView.findViewById(androidx.appcompat.R.id.search_src_text);
        txtSearch.setHint("Search cases...");
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

                switch (type) {

                    case 0:
                        if (FragmentMyCountry.caseArrayList.isEmpty()) {

                            return false;

                        }
                        caseAdapter.getFilter().filter(newText);
                        break;

                    case 1:
                        if (FragmentMyCountry.caseOfwArrayList.isEmpty()) {

                            return false;

                        }
                        caseOfwAdapter.getFilter().filter(newText);
                        break;

                    case 2:
                        if (FragmentMyCountry.caseForeignNationalArrayList.isEmpty()) {

                            return false;

                        }
                        caseForeignNationalAdapter.getFilter().filter(newText);
                        break;

                }

                return false;

            }
        });

    }


    private void updateViews() {

        progressBar = view.findViewById(R.id.progressBar);

    }


    private void updateRecyclerView(int type) {

        switch (type) {

            case 0:
                caseAdapter = new CaseAdapter(FragmentMyCountry.caseArrayList);
                recyclerViewCases.setAdapter(caseAdapter);
                break;

            case 1:
                caseOfwAdapter = new CaseOfwAdapter(FragmentMyCountry.caseOfwArrayList);
                recyclerViewCases.setAdapter(caseOfwAdapter);
                break;

            case 2:
                caseForeignNationalAdapter = new CaseForeignNationalAdapter(FragmentMyCountry.caseForeignNationalArrayList);
                recyclerViewCases.setAdapter(caseForeignNationalAdapter);
                break;

        }

    }


}
