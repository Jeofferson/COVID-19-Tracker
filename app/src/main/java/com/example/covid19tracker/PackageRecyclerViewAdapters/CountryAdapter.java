package com.example.covid19tracker.PackageRecyclerViewAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.covid19tracker.PackageHelpers.FragmentNavigation;
import com.example.covid19tracker.PackageObjectModels.Country;
import com.example.covid19tracker.PackageSessionVariables.Constants;
import com.example.covid19tracker.PackageSessionVariables.ConstantsVolley;
import com.example.covid19tracker.PackageSessionVariables.SuperGlobals;
import com.example.covid19tracker.R;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> implements Filterable {


    private List<Country> countryList;
    private List<Country> countryListFull;

    private Context context;


    public CountryAdapter(List<Country> countryList) {
        this.countryList = new ArrayList<>(countryList);
        countryListFull = new ArrayList<>(countryList);
    }


    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();

        View view = LayoutInflater.from(context).inflate(R.layout.row_country, parent, false);

        return new CountryViewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {

        try {

            Country country = countryList.get(position);

            holder.lblNumber.setText(String.valueOf(country.getId() + 1) + ".");
            holder.lblCountryName.setText(country.getCountryName());

            Glide.with(context)
                    .load(country.getCountryInfo().getString(ConstantsVolley.FLAG))
                    .placeholder(R.drawable.unknown_flag)
                    .into(holder.imgFlag);

            holder.lblNumberCases.setText(Constants.FORMAT_WITH_COMMA.format(country.getCases()));
            holder.lblNumberDeaths.setText(Constants.FORMAT_WITH_COMMA.format(country.getDeaths()));
            holder.lblNumberRecovered.setText(Constants.FORMAT_WITH_COMMA.format(country.getRecovered()));

        } catch (JSONException e) {

            e.printStackTrace();

        }

    }


    @Override
    public int getItemCount() {
        return countryList.size();
    }


    @Override
    public Filter getFilter() {
        return countryFilter;
    }


    private Filter countryFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            List<Country> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {

                filteredList.addAll(countryListFull);

            } else {

                String filterPattern = constraint.toString().trim().toLowerCase();

                for (int i = 0; i < countryListFull.size(); i++) {

                    Country country = countryListFull.get(i);

                    try {

                        if (country.getCountryName().toLowerCase().contains(filterPattern) ||
                                country.getCountryInfo().getString(ConstantsVolley.ISO_2).toLowerCase().contains(filterPattern) ||
                                country.getCountryInfo().getString(ConstantsVolley.ISO_3).toLowerCase().contains(filterPattern)
                        ) {

                            filteredList.add(country);

                        }

                    } catch (JSONException e) {

                        e.printStackTrace();

                    }

                }

            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;

            return filterResults;

        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            countryList.clear();
            countryList.addAll((List) results.values);

            notifyDataSetChanged();

        }
    };


    public class CountryViewHolder extends RecyclerView.ViewHolder {

        private TextView lblNumber;
        private TextView lblCountryName;

        private ImageView imgFlag;

        private TextView lblNumberCases;
        private TextView lblNumberDeaths;
        private TextView lblNumberRecovered;

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);

            lblNumber = itemView.findViewById(R.id.lblNumber);
            lblCountryName = itemView.findViewById(R.id.lblCountryName);

            imgFlag = itemView.findViewById(R.id.imgFlag);

            lblNumberCases = itemView.findViewById(R.id.lblNumberCases);
            lblNumberDeaths = itemView.findViewById(R.id.lblNumberDeaths);
            lblNumberRecovered = itemView.findViewById(R.id.lblNumberRecovered);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    goToFragmentCountry(v);

                }
            });

        }

        private void goToFragmentCountry(View view) {

            SuperGlobals.selectedCountry = countryList.get(getAdapterPosition());
            FragmentNavigation.goToFragmentCountry(view, Constants.COUNTRIES);

        }

    }


}
