package com.example.covid19tracker.PackageFragments;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.covid19tracker.PackageRecyclerViewAdapters.MoreInformationItemAdapter;
import com.example.covid19tracker.PackageSessionVariables.Constants;
import com.example.covid19tracker.PackageSessionVariables.SuperGlobals;
import com.example.covid19tracker.R;


public class FragmentMoreInformation extends Fragment {


    private MoreInformationItemAdapter moreInformationItemAdapter;

    private View view;

    private Toolbar toolbar;
    private SearchView searchView;

    private RecyclerView recyclerViewMoreInformationItems;


    public FragmentMoreInformation() {}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_more_information, container, false);

        prepareToolbar(SuperGlobals.selectedMoreInformationType);
        prepareRecyclerView();
        updateRecyclerView();

        return view;

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


    private void prepareToolbar(String title) {

        toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(title);
        setHasOptionsMenu(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

    }


    private void prepareRecyclerView() {

        recyclerViewMoreInformationItems = view.findViewById(R.id.recyclerViewMoreInformationItems);
        recyclerViewMoreInformationItems.setLayoutManager(new LinearLayoutManager(getActivity()));

    }


    private void prepareSearchView() {

        searchView = view.findViewById(R.id.searchView);
        searchView.setMaxWidth(Integer.MAX_VALUE);

        EditText txtSearch = searchView.findViewById(androidx.appcompat.R.id.search_src_text);
        txtSearch.setHint("Search...");
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

                moreInformationItemAdapter.getFilter().filter(newText);

                return false;

            }
        });

    }


    public void updateRecyclerView() {

        switch (SuperGlobals.selectedMoreInformationType) {

            case Constants.ADVICE_FOR_THE_PUBLIC:
                moreInformationItemAdapter = new MoreInformationItemAdapter(Constants.ADVICE_FOR_THE_PUBLIC_LIST);
                break;

            case Constants.MYTH_BUSTERS:
                moreInformationItemAdapter = new MoreInformationItemAdapter(Constants.MYTH_BUSTER_LIST);
                break;

            case Constants.QUESTIONS_AND_ANSWERS:
                moreInformationItemAdapter = new MoreInformationItemAdapter(Constants.QUESTION_AND_ANSWER_LIST);
                break;

        }

        prepareSearchView();
        recyclerViewMoreInformationItems.setAdapter(moreInformationItemAdapter);

    }


}
