package com.example.covid19tracker.PackageFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.covid19tracker.PackageHelpers.FragmentNavigation;
import com.example.covid19tracker.PackageSessionVariables.Constants;
import com.example.covid19tracker.PackageSessionVariables.SuperGlobals;
import com.example.covid19tracker.R;


public class FragmentAbout extends Fragment {


    private View view;

    private Button btnAdviceForThePublic;
    private Button btnMythBusters;
    private Button btnQuestionsAndAnswers;


    public FragmentAbout() {}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_about, container, false);

        updateViews();

        return view;

    }


    private void updateViews() {

        btnAdviceForThePublic = view.findViewById(R.id.btnAdviceForThePublic);
        btnMythBusters = view.findViewById(R.id.btnMythBusters);
        btnQuestionsAndAnswers = view.findViewById(R.id.btnQuestionsAndAnswers);

        btnAdviceForThePublic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                goToFragmentMoreInformation(Constants.ADVICE_FOR_THE_PUBLIC);

            }
        });

        btnMythBusters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                goToFragmentMoreInformation(Constants.MYTH_BUSTERS);

            }
        });

        btnQuestionsAndAnswers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                goToFragmentMoreInformation(Constants.QUESTIONS_AND_ANSWERS);

            }
        });

    }


    private void goToFragmentMoreInformation(String type) {

        SuperGlobals.selectedMoreInformationType = type;
        FragmentNavigation.goToFragmentMoreInformation(view, Constants.ABOUT);

    }


}
