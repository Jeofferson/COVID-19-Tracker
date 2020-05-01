package com.example.covid19tracker.PackageRecyclerViewAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid19tracker.PackageObjectModels.City;
import com.example.covid19tracker.R;

import java.util.ArrayList;
import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHolder> implements Filterable {


    private List<City> cityList;
    private List<City> cityListFull;

    private Context context;


    public CityAdapter(List<City> cityList) {
        this.cityList = new ArrayList<>(cityList);
        cityListFull = new ArrayList<>(cityList);
    }


    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();

        View view = LayoutInflater.from(context).inflate(R.layout.row_city, parent, false);

        return new CityViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder holder, int position) {

        City city = cityList.get(position);

        holder.lblCityAddress.setText(city.getCityAddress());
        holder.lblCases.setText(String.valueOf(city.getCases()));

    }


    @Override
    public int getItemCount() {
        return cityList.size();
    }


    @Override
    public Filter getFilter() {
        return cityFilter;
    }


    private Filter cityFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            List<City> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {

                filteredList.addAll(cityListFull);

            } else {

                String filterPattern = constraint.toString().trim().toLowerCase();

                for (int i = 0; i < cityListFull.size(); i++) {

                    City city = cityListFull.get(i);

                    if (city.getCityAddress().toLowerCase().contains(filterPattern)) {

                        filteredList.add(city);

                    }

                }

            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;

            return filterResults;

        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            cityList.clear();
            cityList.addAll((List) results.values);

            notifyDataSetChanged();

        }
    };


    public class CityViewHolder extends RecyclerView.ViewHolder {

        private TextView lblCityAddress;
        private TextView lblCases;

        public CityViewHolder(@NonNull View itemView) {
            super(itemView);

            lblCityAddress = itemView.findViewById(R.id.lblCityAddress);
            lblCases = itemView.findViewById(R.id.lblCases);

        }

    }


}
