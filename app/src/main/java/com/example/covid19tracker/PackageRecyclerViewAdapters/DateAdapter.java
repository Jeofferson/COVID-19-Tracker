package com.example.covid19tracker.PackageRecyclerViewAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid19tracker.PackageObjectModels.Date;
import com.example.covid19tracker.PackageSessionVariables.Constants;
import com.example.covid19tracker.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class DateAdapter extends RecyclerView.Adapter<DateAdapter.DateViewHolder> {


    private List<Date> dateList;

    private Context context;


    public DateAdapter(List<Date> dateList) {
        this.dateList = dateList;
    }


    @NonNull
    @Override
    public DateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();

        View view = LayoutInflater.from(context).inflate(R.layout.row_date, parent, false);

        return new DateViewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull DateViewHolder holder, int position) {

        Date date = dateList.get(position);

        SimpleDateFormat format1 = new SimpleDateFormat(Constants.DATE_FORMAT_FROM_API);
        SimpleDateFormat format2 = new SimpleDateFormat(Constants.DATE_FORMAT);
        java.util.Date date1 = null;
        try {
            date1 = format1.parse(date.getDateString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.lblDateString.setText(format2.format(date1));

        holder.lblNumberCases.setText(Constants.FORMAT_WITH_COMMA.format(date.getCases()));
        holder.lblNumberDeaths.setText(Constants.FORMAT_WITH_COMMA.format(date.getDeaths()));
        holder.lblNumberRecovered.setText(Constants.FORMAT_WITH_COMMA.format(date.getRecovered()));

    }


    @Override
    public int getItemCount() {
        return dateList.size();
    }


    public class DateViewHolder extends RecyclerView.ViewHolder {

        private TextView lblDateString;
        private TextView lblNumberCases;
        private TextView lblNumberDeaths;
        private TextView lblNumberRecovered;

        public DateViewHolder(@NonNull View itemView) {
            super(itemView);

            lblDateString = itemView.findViewById(R.id.lblDateString);
            lblNumberCases = itemView.findViewById(R.id.lblNumberCases);
            lblNumberDeaths = itemView.findViewById(R.id.lblNumberDeaths);
            lblNumberRecovered = itemView.findViewById(R.id.lblNumberRecovered);

        }

    }


}
