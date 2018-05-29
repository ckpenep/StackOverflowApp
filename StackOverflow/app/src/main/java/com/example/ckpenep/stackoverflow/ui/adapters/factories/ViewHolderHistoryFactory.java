package com.example.ckpenep.stackoverflow.ui.adapters.factories;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ckpenep.stackoverflow.R;

public class ViewHolderHistoryFactory {

    public class HistoryViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public TextView count_answers;
        public TextView tags;

        public HistoryViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.title);
            count_answers = (TextView) itemView.findViewById(R.id.count_answers);
            tags = (TextView) itemView.findViewById(R.id.tags);
        }
    }

    public class HistoryWithDateViewHolder extends RecyclerView.ViewHolder {
        public TextView date;

        public HistoryWithDateViewHolder(View itemView) {
            super(itemView);

            date = (TextView) itemView.findViewById(R.id.textview_date);
        }
    }

    public RecyclerView.ViewHolder create(ViewGroup parent, int viewType) {

        switch (viewType) {
            case HistoryRowType.QUESTION_ROW_TYPE:
                View historyTypeView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
                return new ViewHolderHistoryFactory.HistoryViewHolder(historyTypeView);

            case HistoryRowType.DATE_ROW_TYPE:
                View dateTypeView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_date_separator, parent, false);
                return new ViewHolderHistoryFactory.HistoryWithDateViewHolder(dateTypeView);

            default:
                return null;
        }
    }
}
