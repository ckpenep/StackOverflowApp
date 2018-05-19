package com.example.ckpenep.stackoverflow.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.ckpenep.stackoverflow.ui.adapters.factories.DetailsRowType;
import com.example.ckpenep.stackoverflow.ui.adapters.factories.ViewHolderDetailsFactory;

import java.util.ArrayList;
import java.util.List;

public class QuestionDetailsAdapter extends RecyclerView.Adapter {

    private List<DetailsRowType> details;

    public QuestionDetailsAdapter() {
        details = new ArrayList<>();
    }

    @Override
    public int getItemViewType(int position) {
        return details.get(position).getItemViewType();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = ViewHolderDetailsFactory.create(parent, viewType);

        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        details.get(position).onBindViewHolder(holder);
    }

    @Override
    public int getItemCount() {
        return details.size();
    }

    public void updateDetails(List<DetailsRowType> questions) {
        this.details.clear();
        this.details.addAll(questions);

        notifyDataSetChanged();
    }
}
