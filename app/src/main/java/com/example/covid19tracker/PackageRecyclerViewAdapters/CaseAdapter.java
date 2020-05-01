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

import com.example.covid19tracker.PackageObjectModels.Case;
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

public class CaseAdapter extends RecyclerView.Adapter<CaseAdapter.CaseViewHolder> implements Filterable {


    private List<Case> caseList;
    private List<Case> caseListFull;

    private Context context;


    public CaseAdapter(List<Case> caseList) {
        this.caseList = new ArrayList<>(caseList);
        caseListFull = new ArrayList<>(caseList);
    }


    @NonNull
    @Override
    public CaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();

        View view = LayoutInflater.from(context).inflate(R.layout.row_case, parent, false);

        return new CaseViewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull CaseViewHolder holder, int position) {

        try {

            Case mCase = caseList.get(position);

            String sex = mCase.getSex();
            JSONObject rawData = mCase.getMetadata().getJSONObject(ConstantsVolley.RAW_DATA);

            SimpleDateFormat format1 = new SimpleDateFormat(Constants.DATE_FORMAT_FROM_API_2);
            SimpleDateFormat format2 = new SimpleDateFormat(Constants.DATE_FORMAT_FROM_API_3);
            SimpleDateFormat format3 = new SimpleDateFormat(Constants.DATE_FORMAT);

            Date date1 = format1.parse(rawData.getString(ConstantsVolley.DATE_CONFIRMED));
            String dateConfirmedString =  format3.format(date1);

            String dateDeceasedString;
            if (mCase.getDateDeceased().equalsIgnoreCase("null")) {

                dateDeceasedString = "N/A";

            } else {

                Date date2 = format2.parse(mCase.getDateDeceased());
                dateDeceasedString = format3.format(date2);

            }

            String dateRecoveredString;
            if (mCase.getDateRecovered().equalsIgnoreCase("null")) {

                dateRecoveredString = "N/A";

            } else {

                Date date2 = format2.parse(mCase.getDateRecovered());
                dateRecoveredString = format3.format(date2);

            }

            if (sex.equalsIgnoreCase("Male")) {

                holder.imgCaseDp.setImageResource(R.drawable.ic_006_sneeze);

            } else if (sex.equalsIgnoreCase("Female")) {

                holder.imgCaseDp.setImageResource(R.drawable.ic_013_cough);

            } else {

                holder.imgCaseDp.setImageResource(R.drawable.unknown);

            }

            holder.lblCaseId.setText(mCase.getCaseId());

            holder.lblAge.setText("Age: " + ((mCase.getAge() == -1 || mCase.getAge() == 0) ? "TBA" : String.valueOf(mCase.getAge())));
            holder.lblSex.setText("Sex: " + sex);
            holder.lblDateConfirmed.setText("Date Confirmed: " + dateConfirmedString);
            holder.lblStatus.setText("Status: " + (rawData.getString(ConstantsVolley.STATUS).trim().isEmpty() ? "TBA" : rawData.getString(ConstantsVolley.STATUS)));
            holder.lblNationality.setText("Nationality: " + rawData.getString(ConstantsVolley.NATIONALITY));
            holder.lblResidence.setText("Residence: " + (rawData.getString(ConstantsVolley.RESIDENCE).equalsIgnoreCase("None") ? "N/A" : rawData.getString(ConstantsVolley.RESIDENCE)));
            holder.lblTravelHistory.setText("Travel History: " + (rawData.getString(ConstantsVolley.TRAVEL_HISTORY).trim().isEmpty() ? "N/A" : rawData.getString(ConstantsVolley.TRAVEL_HISTORY)));
            holder.lblFacility.setText("Facility: " + rawData.getString(ConstantsVolley.FACILITY));
            holder.lblDateDeceased.setText("Date Deceased: " + dateDeceasedString);
            holder.lblDateRecovered.setText("Date Recovered: " + dateRecoveredString);

        } catch (JSONException | ParseException e) {

            e.printStackTrace();

        }

    }


    @Override
    public int getItemCount() {
        return caseList.size();
    }


    @Override
    public Filter getFilter() {
        return caseFilter;
    }


    private Filter caseFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

                List<Case> filteredList = new ArrayList<>();

                if (constraint == null || constraint.length() == 0) {

                    filteredList.addAll(caseListFull);

                } else {

                    String filterPattern = constraint.toString().trim().toLowerCase();

                    for (int i = 0; i < caseListFull.size(); i++) {

                        Case mCase = caseListFull.get(i);

                        try {

                            JSONObject rawData = mCase.getMetadata().getJSONObject(ConstantsVolley.RAW_DATA);

                            if (
                                    mCase.getCaseId().toLowerCase().equalsIgnoreCase(filterPattern) ||
                                    String.valueOf(mCase.getAge()).toLowerCase().contains(filterPattern) ||
                                    mCase.getSex().toLowerCase().contains(filterPattern) ||
                                    rawData.getString(ConstantsVolley.STATUS).toLowerCase().contains(filterPattern) ||
                                    rawData.getString(ConstantsVolley.NATIONALITY).toLowerCase().contains(filterPattern) ||
                                    rawData.getString(ConstantsVolley.RESIDENCE).toLowerCase().contains(filterPattern) ||
                                    rawData.getString(ConstantsVolley.TRAVEL_HISTORY).toLowerCase().contains(filterPattern) ||
                                    rawData.getString(ConstantsVolley.FACILITY).toLowerCase().contains(filterPattern)
                            ) {

                                filteredList.add(mCase);

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

            caseList.clear();
            caseList.addAll((List) results.values);

            notifyDataSetChanged();

        }
    };


    public class CaseViewHolder extends RecyclerView.ViewHolder {


        private ImageView imgCaseDp;
        private TextView lblCaseId;

        private TextView lblAge;
        private TextView lblSex;
        private TextView lblDateConfirmed;
        private TextView lblStatus;
        private TextView lblNationality;
        private TextView lblResidence;
        private TextView lblTravelHistory;
        private TextView lblFacility;
        private TextView lblDateDeceased;
        private TextView lblDateRecovered;


        public CaseViewHolder(@NonNull View itemView) {
            super(itemView);

            imgCaseDp = itemView.findViewById(R.id.imgCaseDp);
            lblCaseId = itemView.findViewById(R.id.lblCaseId);

            lblAge = itemView.findViewById(R.id.lblAge);
            lblSex = itemView.findViewById(R.id.lblSex);
            lblDateConfirmed = itemView.findViewById(R.id.lblDateConfirmed);
            lblStatus = itemView.findViewById(R.id.lblStatus);
            lblNationality = itemView.findViewById(R.id.lblNationality);
            lblResidence = itemView.findViewById(R.id.lblResidence);
            lblTravelHistory = itemView.findViewById(R.id.lblTravelHistory);
            lblFacility = itemView.findViewById(R.id.lblFacility);
            lblDateDeceased = itemView.findViewById(R.id.lblDateDeceased);
            lblDateRecovered = itemView.findViewById(R.id.lblDateRecovered);

        }

    }


}
