package com.example.ckpenep.stackoverflow.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.ckpenep.stackoverflow.app.App;
import com.example.ckpenep.stackoverflow.ui.adapters.factories.DetailsRowType;
import com.example.ckpenep.stackoverflow.ui.adapters.factories.ViewHolderDetailsFactory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class QuestionDetailsAdapter extends RecyclerView.Adapter {

    @Inject
    ViewHolderDetailsFactory detailFactory;

    private List<DetailsRowType> details;

    public QuestionDetailsAdapter() {
        App.getAppComponent().inject(this);
        details = new ArrayList<>();
    }

    @Override
    public int getItemViewType(int position) {
        return details.get(position).getItemViewType();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = detailFactory.create(parent, viewType);

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

    public void updateDetails(List<DetailsRowType> items) {
        this.details.clear();
        this.details.addAll(items);

        notifyDataSetChanged();
    }
}
