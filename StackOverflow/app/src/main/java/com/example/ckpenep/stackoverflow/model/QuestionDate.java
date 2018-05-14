package com.example.ckpenep.stackoverflow.model;

import android.support.v7.widget.RecyclerView;

import com.example.ckpenep.stackoverflow.ui.adapters.HistoryRowType;
import com.example.ckpenep.stackoverflow.ui.adapters.ViewHolderHistoryFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class QuestionDate implements HistoryRowType {
    Long date;

    public QuestionDate(Long date) {
        this.date = date;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public String getDateByString()
    {
        Calendar cal = Calendar.getInstance();

        cal.setTimeInMillis(getDate());

        SimpleDateFormat date = new SimpleDateFormat("d MMMM yyyy");
        return date.format(cal.getTime());
    }

    @Override
    public int getItemViewType() {
        return HistoryRowType.DATE_ROW_TYPE;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder) {
        ViewHolderHistoryFactory.HistoryWithDateViewHolder dateViewHolder = (ViewHolderHistoryFactory.HistoryWithDateViewHolder) viewHolder;

        dateViewHolder.date.setText(getDateByString());
    }
}
