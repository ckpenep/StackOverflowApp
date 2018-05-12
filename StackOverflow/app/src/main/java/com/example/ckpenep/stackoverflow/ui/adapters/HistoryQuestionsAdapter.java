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

    private int type;
    private Long currentMillis;
    private QuestionListAdapter.OnItemClickListener onItemClickListener;

    private List<Question> mQuestions;

    public HistoryQuestionsAdapter() {
        mQuestions = new ArrayList<>();
    }

    @Override
    public int getItemViewType(int position) {
        type = mQuestions.get(position).getItemViewType(currentMillis);
        currentMillis = mQuestions.get(position).getSaveDate();

        return type;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = ViewHolderHistoryFactory.create(parent, viewType);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(mQuestions.get(holder.getAdapterPosition()));
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        mQuestions.get(position).onBindViewHolder(holder, type);
    }

    public void updateContacts(List<Question> questions) {
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

    public void setOnItemClickListener(QuestionListAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(Question question);
    }
}
