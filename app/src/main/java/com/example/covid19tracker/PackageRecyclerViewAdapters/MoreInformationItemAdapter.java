package com.example.covid19tracker.PackageRecyclerViewAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid19tracker.PackageAnimations.Animations;
import com.example.covid19tracker.PackageObjectModels.MoreInformationItem;
import com.example.covid19tracker.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MoreInformationItemAdapter extends RecyclerView.Adapter<MoreInformationItemAdapter.MoreInformationItemViewHolder> implements Filterable {


    private List<MoreInformationItem> moreInformationItemList;
    private List<MoreInformationItem> moreInformationItemListFull;

    private Context context;


    public MoreInformationItemAdapter(List<MoreInformationItem> moreInformationItemList) {
        this.moreInformationItemList = new ArrayList<>(moreInformationItemList);
        moreInformationItemListFull = new ArrayList<>(moreInformationItemList);
    }


    @NonNull
    @Override
    public MoreInformationItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();

        View view = LayoutInflater.from(context).inflate(R.layout.row_more_information_item, parent, false);

        return new MoreInformationItemViewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull final MoreInformationItemViewHolder holder, final int position) {

        final MoreInformationItem moreInformationItem = moreInformationItemList.get(position);

        holder.lblTitle.setText(moreInformationItem.getTitle());
        holder.lblMessage.setText(moreInformationItem.getMessage());

        if (!moreInformationItem.isExpanded()) {

            holder.lblMessage.setVisibility(View.GONE);
            holder.lblTitle.setTextColor(context.getResources().getColor(R.color.currentText));

        } else {

            holder.lblMessage.setVisibility(View.VISIBLE);
            holder.lblTitle.setTextColor(context.getResources().getColor(R.color.green2));
            
        }

        holder.layoutTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                expand(position, holder.btnShowMessage, holder.lblMessage, holder.lblTitle);

            }
        });

        holder.btnShowMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                expand(position, v, holder.lblMessage, holder.lblTitle);

            }
        });

    }


    private void expand(int position, View v, TextView layoutExpand, TextView lblTitle) {

        MoreInformationItem moreInformationItem = moreInformationItemList.get(position);
        boolean isExpanded = moreInformationItem.isExpanded();

        boolean show = toggleLayout(!isExpanded, v, layoutExpand);
        moreInformationItemList.get(position).setExpanded(show);

        if (isExpanded) {

            lblTitle.setTextColor(context.getResources().getColor(R.color.currentText));

        } else {

            lblTitle.setTextColor(context.getResources().getColor(R.color.green2));

        }

    }


    private boolean toggleLayout(boolean isExpanded, View v, TextView layoutExpand) {

        Animations.toggleArrow(v, isExpanded);
        if (isExpanded) {
            Animations.expand(layoutExpand);
        } else {
            Animations.collapse(layoutExpand);
        }
        return isExpanded;

    }


    @Override
    public int getItemCount() {
        return moreInformationItemList.size();
    }


    @Override
    public Filter getFilter() {
        return moreInformationItemFilter;
    }


    private Filter moreInformationItemFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            List<MoreInformationItem> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {

                filteredList.addAll(moreInformationItemListFull);

            } else {

                String filterPattern = constraint.toString().trim().toLowerCase();

                for (int i = 0; i < moreInformationItemListFull.size(); i++) {

                    MoreInformationItem moreInformationItem = moreInformationItemListFull.get(i);

                    if (
                            moreInformationItem.getTitle().toLowerCase().contains(filterPattern) ||
                            moreInformationItem.getMessage().toLowerCase().contains(filterPattern)
                    ) {

                        filteredList.add(moreInformationItem);

                    }

                }

            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;

            return filterResults;

        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            moreInformationItemList.clear();
            moreInformationItemList.addAll((List) results.values);

            notifyDataSetChanged();

        }
    };


    public class MoreInformationItemViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout layoutTitle;
        private TextView lblTitle;
        private ImageButton btnShowMessage;

        private TextView lblMessage;

        public MoreInformationItemViewHolder(@NonNull View itemView) {
            super(itemView);

            layoutTitle = itemView.findViewById(R.id.layoutTitle);
            lblTitle = itemView.findViewById(R.id.lblTitle);
            btnShowMessage = itemView.findViewById(R.id.btnShowMessage);

            lblMessage = itemView.findViewById(R.id.lblMessage);

        }

    }


}
