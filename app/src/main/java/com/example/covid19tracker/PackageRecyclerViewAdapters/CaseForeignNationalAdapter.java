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

import com.example.covid19tracker.PackageObjectModels.CaseForeignNational;
import com.example.covid19tracker.PackageSessionVariables.Constants;
import com.example.covid19tracker.PackageSessionVariables.ConstantsVolley;
import com.example.covid19tracker.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CaseForeignNationalAdapter extends RecyclerView.Adapter<CaseForeignNationalAdapter.CaseForeignNationalViewHolder> implements Filterable {


    private List<CaseForeignNational> caseForeignNationalList;
    private List<CaseForeignNational> caseForeignNationalListFull;

    private Context context;


    public CaseForeignNationalAdapter(List<CaseForeignNational> caseForeignNationalList) {
        this.caseForeignNationalList = new ArrayList<>(caseForeignNationalList);
        caseForeignNationalListFull = new ArrayList<>(caseForeignNationalList);
    }


    @NonNull
    @Override
    public CaseForeignNationalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();

        View view = LayoutInflater.from(context).inflate(R.layout.row_case_foreign_national, parent, false);

        return new CaseForeignNationalViewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull CaseForeignNationalViewHolder holder, int position) {

        try {

            CaseForeignNational caseForeignNational = caseForeignNationalList.get(position);

            String sex = caseForeignNational.getSex();
            JSONObject rawData = caseForeignNational.getMetadata().getJSONObject(ConstantsVolley.RAW_DATA);

            SimpleDateFormat format1 = new SimpleDateFormat(Constants.DATE_FORMAT_FROM_API_3);
            SimpleDateFormat format2 = new SimpleDateFormat(Constants.DATE_FORMAT);

            String dateConfirmedString;
            if (caseForeignNational.getDateConfirmed().equalsIgnoreCase("null")) {

                dateConfirmedString = "N/A";

            } else {

                Date date2 = format1.parse(caseForeignNational.getDateConfirmed());
                dateConfirmedString = format2.format(date2);

            }

            if (sex.equalsIgnoreCase("Male")) {

                holder.imgCaseDp.setImageResource(R.drawable.ic_006_sneeze);

            } else if (sex.equalsIgnoreCase("Female")) {

                holder.imgCaseDp.setImageResource(R.drawable.ic_013_cough);

            } else {

                holder.imgCaseDp.setImageResource(R.drawable.unknown);

            }

            holder.lblCaseId.setText(caseForeignNational.getCaseId());

            holder.lblAge.setText("Age: " + ((caseForeignNational.getAge() == -1 || caseForeignNational.getAge() == 0) ? "TBA" : String.valueOf(caseForeignNational.getAge())));
            holder.lblSex.setText("Sex: " + sex);
            holder.lblNationality.setText("Nationality: " + rawData.getString(ConstantsVolley.NATIONALITY));
            holder.lblTravelDate.setText("Travel Date: " + rawData.getString(ConstantsVolley.TRAVEL_DATE));
            holder.lblTravelHistory.setText("Travel History: " + rawData.getString(ConstantsVolley.TRAVEL_HISTORY));
            holder.lblDateConfirmed.setText("Date Confirmed: " + dateConfirmedString);
            holder.lblWhereNow.setText("Where Now: " + (rawData.getString(ConstantsVolley.WHERE_NOW).trim().isEmpty() ? "TBA" : rawData.getString(ConstantsVolley.WHERE_NOW)));
            holder.lblStatus.setText("Status: " + (rawData.getString(ConstantsVolley.STATUS).trim().isEmpty() ? "TBA" : rawData.getString(ConstantsVolley.STATUS)));

        } catch (JSONException | ParseException e) {

            e.printStackTrace();

        }

    }


    @Override
    public int getItemCount() {
        return caseForeignNationalList.size();
    }


    @Override
    public Filter getFilter() {
        return caseForeignNationalFilter;
    }


    private Filter caseForeignNationalFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            List<CaseForeignNational> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {

                filteredList.addAll(caseForeignNationalListFull);

            } else {

                String filterPattern = constraint.toString().trim().toLowerCase();

                for (int i = 0; i < caseForeignNationalListFull.size(); i++) {

                    CaseForeignNational caseForeignNational = caseForeignNationalListFull.get(i);

                    try {

                        JSONObject rawData = caseForeignNational.getMetadata().getJSONObject(ConstantsVolley.RAW_DATA);

                        if (
                                caseForeignNational.getCaseId().toLowerCase().equalsIgnoreCase(filterPattern) ||
                                String.valueOf(caseForeignNational.getAge()).toLowerCase().contains(filterPattern) ||
                                caseForeignNational.getSex().toLowerCase().contains(filterPattern) ||
                                rawData.getString(ConstantsVolley.NATIONALITY).toLowerCase().contains(filterPattern) ||
                                rawData.getString(ConstantsVolley.TRAVEL_DATE).toLowerCase().contains(filterPattern) ||
                                rawData.getString(ConstantsVolley.TRAVEL_HISTORY).toLowerCase().contains(filterPattern) ||
                                rawData.getString(ConstantsVolley.WHERE_NOW).toLowerCase().contains(filterPattern) ||
                                rawData.getString(ConstantsVolley.STATUS).toLowerCase().contains(filterPattern)
                        ) {

                            filteredList.add(caseForeignNational);

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

            caseForeignNationalList.clear();
            caseForeignNationalList.addAll((List) results.values);

            notifyDataSetChanged();

        }
    };


    public class CaseForeignNationalViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgCaseDp;
        private TextView lblCaseId;

        private TextView lblAge;
        private TextView lblSex;
        private TextView lblNationality;
        private TextView lblTravelDate;
        private TextView lblTravelHistory;
        private TextView lblDateConfirmed;
        private TextView lblWhereNow;
        private TextView lblStatus;


        public CaseForeignNationalViewHolder(@NonNull View itemView) {
            super(itemView);

            imgCaseDp = itemView.findViewById(R.id.imgCaseDp);
            lblCaseId = itemView.findViewById(R.id.lblCaseId);

            lblAge = itemView.findViewById(R.id.lblAge);
            lblSex = itemView.findViewById(R.id.lblSex);
            lblNationality = itemView.findViewById(R.id.lblNationality);
            lblTravelDate = itemView.findViewById(R.id.lblTravelDate);
            lblTravelHistory = itemView.findViewById(R.id.lblTravelHistory);
            lblDateConfirmed = itemView.findViewById(R.id.lblDateConfirmed);
            lblWhereNow = itemView.findViewById(R.id.lblWhereNow);
            lblStatus = itemView.findViewById(R.id.lblStatus);

        }

    }


}
