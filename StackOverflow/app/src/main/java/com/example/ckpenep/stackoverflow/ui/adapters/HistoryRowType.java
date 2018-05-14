package com.example.ckpenep.stackoverflow.ui.adapters;

import android.support.v7.widget.RecyclerView;

public interface HistoryRowType {
    int HISTORY_ROW_TYPE =   0;
    int DATE_ROW_TYPE = 1;

    int getItemViewType();

    void onBindViewHolder(RecyclerView.ViewHolder viewHolder);
}
