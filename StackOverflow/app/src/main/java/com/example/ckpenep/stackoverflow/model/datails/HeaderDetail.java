package com.example.ckpenep.stackoverflow.model.datails;

import android.support.v7.widget.RecyclerView;

import com.example.ckpenep.stackoverflow.ui.adapters.factories.DetailsRowType;

public class HeaderDetail implements DetailsRowType {

    @Override
    public int getItemViewType() {
        return DetailsRowType.HEADER_ROW_TYPE;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder) {

    }
}
