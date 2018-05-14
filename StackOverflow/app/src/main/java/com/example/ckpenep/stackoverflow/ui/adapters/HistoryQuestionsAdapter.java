package com.example.ckpenep.stackoverflow.ui.adapters;

import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.ckpenep.stackoverflow.model.Question;
import com.example.ckpenep.stackoverflow.utils.QuestionDiffCallback;

import java.util.ArrayList;
import java.util.List;

public class HistoryQuestionsAdapter extends RecyclerView.Adapter {

    private HistoryQuestionsAdapter.OnItemClickListener onItemClickListener;

    private List<HistoryRowType> mQuestions;

    public HistoryQuestionsAdapter() {
        mQuestions = new ArrayList<>();
    }

    @Override
    public int getItemViewType(int position) {
        return mQuestions.get(position).getItemViewType();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = ViewHolderHistoryFactory.create(parent, viewType);

        if(holder instanceof ViewHolderHistoryFactory.HistoryViewHolder) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(mQuestions.get(holder.getAdapterPosition()));
                }
            });
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        mQuestions.get(position).onBindViewHolder(holder);
    }

    public void updateContacts(List<HistoryRowType> questions) {
        final QuestionDiffCallback diffCallback = new QuestionDiffCallback(this.mQuestions, questions);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        this.mQuestions.clear();
        this.mQuestions.addAll(questions);
        diffResult.dispatchUpdatesTo(this);
    }

    public void addContractsItems(List<Question> giList) {
        for (int i = mQuestions.size(); i < giList.size(); i++) {
            mQuestions.add(giList.get(i));
            notifyItemInserted(mQuestions.size() - 1);
        }
    }

    @Override
    public int getItemCount() {
        return mQuestions.size();
    }

    public void setOnItemClickListener(HistoryQuestionsAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void onItemClick(HistoryRowType question);
    }
}
