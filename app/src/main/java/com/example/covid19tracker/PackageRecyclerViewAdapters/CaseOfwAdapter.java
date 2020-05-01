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

import com.example.covid19tracker.PackageObjectModels.CaseOfw;
import com.example.covid19tracker.PackageSessionVariables.ConstantsVolley;
import com.example.covid19tracker.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CaseOfwAdapter extends RecyclerView.Adapter<CaseOfwAdapter.CaseOfwViewHolder> implements Filterable {


    private List<CaseOfw> caseOfwList;
    private List<CaseOfw> caseOfwListFull;

    private Context context;


    public CaseOfwAdapter(List<CaseOfw> caseOfwList) {
        this.caseOfwList = new ArrayList<>(caseOfwList);
        caseOfwListFull = new ArrayList<>(caseOfwList);
    }


    @NonNull
    @Override
    public CaseOfwViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();

        View view = LayoutInflater.from(context).inflate(R.layout.row_case_ofw, parent, false);

        return new CaseOfwViewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull CaseOfwViewHolder holder, int position) {

        try {

            CaseOfw caseOfw = caseOfwList.get(position);

            String sex = caseOfw.getSex();
            JSONObject rawData = caseOfw.getMetadata().getJSONObject(ConstantsVolley.RAW_DATA);

            if (sex.equalsIgnoreCase("Male")) {

                holder.imgCaseDp.setImageResource(R.drawable.ic_006_sneeze);

            } else if (sex.equalsIgnoreCase("Female")) {

                holder.imgCaseDp.setImageResource(R.drawable.ic_013_cough);

            } else {

                holder.imgCaseDp.setImageResource(R.drawable.unknown);

            }

            holder.lblCaseId.setText(caseOfw.getCaseId());

            holder.lblAge.setText("Age: " + ((caseOfw.getAge() == -1 || caseOfw.getAge() == 0) ? "TBA" : String.valueOf(caseOfw.getAge())));
            holder.lblSex.setText("Sex: " + sex);
            holder.lblCountry.setText("Country: " + rawData.getString(ConstantsVolley.COUNTRY));
            holder.lblDateReported.setText("Date Reported: " + rawData.getString(ConstantsVolley.DATE_REPORTED));
            holder.lblDateConfirmed.setText("Date Confirmed: " + rawData.getString(ConstantsVolley.DATE_CONFIRMED));
            holder.lblStatus.setText("Status: " + (rawData.getString(ConstantsVolley.STATUS).trim().isEmpty() ? "TBA" : rawData.getString(ConstantsVolley.STATUS)));
            holder.lblRemarks.setText("Remarks: " + (rawData.getString(ConstantsVolley.REMARKS).trim().isEmpty() ? "TBA" : rawData.getString(ConstantsVolley.REMARKS)));

        } catch (JSONException e) {

            e.printStackTrace();

        }

    }


    @Override
    public int getItemCount() {
        return caseOfwList.size();
    }


    @Override
    public Filter getFilter() {
        return caseOfwFilter;
    }


    private Filter caseOfwFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            List<CaseOfw> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {

                filteredList.addAll(caseOfwListFull);

            } else {

                String filterPattern = constraint.toString().trim().toLowerCase();

                for (int i = 0; i < caseOfwListFull.size(); i++) {

                    CaseOfw caseOfw = caseOfwListFull.get(i);

                    try {

                        JSONObject rawData = caseOfw.getMetadata().getJSONObject(ConstantsVolley.RAW_DATA);

                        if (
                                caseOfw.getCaseId().toLowerCase().equalsIgnoreCase(filterPattern) ||
                                String.valueOf(caseOfw.getAge()).toLowerCase().contains(filterPattern) ||
                                caseOfw.getSex().toLowerCase().contains(filterPattern) ||
                                rawData.getString(ConstantsVolley.COUNTRY).toLowerCase().contains(filterPattern) ||
                                rawData.getString(ConstantsVolley.DATE_REPORTED).toLowerCase().contains(filterPattern) ||
                                rawData.getString(ConstantsVolley.DATE_CONFIRMED).toLowerCase().contains(filterPattern) ||
                                rawData.getString(ConstantsVolley.STATUS).toLowerCase().contains(filterPattern) ||
                                rawData.getString(ConstantsVolley.REMARKS).toLowerCase().contains(filterPattern)
                        ) {

                            filteredList.add(caseOfw);

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

            caseOfwList.clear();
            caseOfwList.addAll((List) results.values);

            notifyDataSetChanged();

        }
    };


    public class CaseOfwViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgCaseDp;
        private TextView lblCaseId;

        private TextView lblAge;
        private TextView lblSex;
        private TextView lblCountry;
        private TextView lblDateReported;
        private TextView lblDateConfirmed;
        private TextView lblStatus;
        private TextView lblRemarks;

        public CaseOfwViewHolder(@NonNull View itemView) {
            super(itemView);

            imgCaseDp = itemView.findViewById(R.id.imgCaseDp);
            lblCaseId = itemView.findViewById(R.id.lblCaseId);

            lblAge = itemView.findViewById(R.id.lblAge);
            lblSex = itemView.findViewById(R.id.lblSex);
            lblCountry = itemView.findViewById(R.id.lblCountry);
            lblDateReported = itemView.findViewById(R.id.lblDateReported);
            lblDateConfirmed = itemView.findViewById(R.id.lblDateConfirmed);
            lblStatus = itemView.findViewById(R.id.lblStatus);
            lblRemarks = itemView.findViewById(R.id.lblRemarks);

        }

    }


}
