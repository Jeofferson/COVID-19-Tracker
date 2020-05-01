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

import com.example.covid19tracker.PackageObjectModels.Facility;
import com.example.covid19tracker.R;

import java.util.ArrayList;
import java.util.List;

public class FacilityAdapter extends RecyclerView.Adapter<FacilityAdapter.FacilityViewHolder> implements Filterable {


    private List<Facility> facilityList;
    private List<Facility> facilityListFull;

    private Context context;


    public FacilityAdapter(List<Facility> facilityList) {
        this.facilityList = new ArrayList<>(facilityList);
        facilityListFull = new ArrayList<>(facilityList);
    }


    @NonNull
    @Override
    public FacilityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();

        View view = LayoutInflater.from(context).inflate(R.layout.row_facility, parent, false);

        return new FacilityViewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull FacilityViewHolder holder, int position) {

        Facility facility = facilityList.get(position);

        holder.lblFacilityName.setText(facility.getFacilityName());
        holder.lblCases.setText(String.valueOf(facility.getCases()));

    }


    @Override
    public int getItemCount() {
        return facilityList.size();
    }


    @Override
    public Filter getFilter() {
        return facilityFilter;
    }


    private Filter facilityFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            List<Facility> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {

                filteredList.addAll(facilityListFull);

            } else {

                String filterPattern = constraint.toString().trim().toLowerCase();

                for (int i = 0; i < facilityListFull.size(); i++) {

                    Facility facility = facilityListFull.get(i);

                    if (facility.getFacilityName().toLowerCase().contains(filterPattern)) {

                        filteredList.add(facility);

                    }

                }

            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;

            return filterResults;

        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            facilityList.clear();
            facilityList.addAll((List) results.values);

            notifyDataSetChanged();

        }
    };


    public class FacilityViewHolder extends RecyclerView.ViewHolder {

        private TextView lblFacilityName;
        private TextView lblCases;

        public FacilityViewHolder(@NonNull View itemView) {
            super(itemView);

            lblFacilityName = itemView.findViewById(R.id.lblFacilityName);
            lblCases = itemView.findViewById(R.id.lblCases);

        }

    }


}
