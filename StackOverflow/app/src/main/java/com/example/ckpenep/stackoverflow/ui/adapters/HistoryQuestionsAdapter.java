package com.example.ckpenep.stackoverflow.ui.adapters;

import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.ckpenep.stackoverflow.app.App;
import com.example.ckpenep.stackoverflow.model.dto.history.QuestionDate;
import com.example.ckpenep.stackoverflow.model.question.Question;
import com.example.ckpenep.stackoverflow.ui.adapters.factories.HistoryRowType;
import com.example.ckpenep.stackoverflow.ui.adapters.factories.ViewHolderHistoryFactory;
import com.example.ckpenep.stackoverflow.ui.common.ItemTouchHelperAdapter;
import com.example.ckpenep.stackoverflow.utils.QuestionDiffCallback;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class HistoryQuestionsAdapter extends RecyclerView.Adapter implements ItemTouchHelperAdapter {

    @Inject
    ViewHolderHistoryFactory historyFactory;

    private HistoryQuestionsAdapter.OnItemClickListener onItemClickListener;
    private HistoryQuestionsAdapter.IDeleteItem mIDeleteItem;

    private List<HistoryRowType> mQuestions;

    public HistoryQuestionsAdapter() {
        App.getAppComponent().inject(this);
        mQuestions = new ArrayList<>();
    }

    @Override
    public int getItemViewType(int position) {
        return mQuestions.get(position).getItemViewType();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = historyFactory.create(parent, viewType);

        if (holder instanceof ViewHolderHistoryFactory.HistoryViewHolder) {
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

    @Override
    public void onItemDismiss(int position) {
        boolean flag = false;

        if (position != 0) {
            if (position == this.mQuestions.size() - 1) {
                if (this.mQuestions.get(position - 1).getClass().equals(QuestionDate.class)) {
                    flag = true;
                }
            } else if (this.mQuestions.get(position - 1).getClass().equals(QuestionDate.class) && this.mQuestions.get(position + 1).getClass().equals(QuestionDate.class)) {
                flag = true;
            }
        }

        if (mIDeleteItem != null)
            mIDeleteItem.onDeleteItem((Question) this.mQuestions.get(position));

        this.mQuestions.remove(position);
        notifyItemRemoved(position);

        if (flag) {
            this.mQuestions.remove(position - 1);
            notifyItemRemoved(position - 1);
        }
    }

    public void setQuestions(List<HistoryRowType> questions) {
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

    public void setOnDeleteItem(HistoryQuestionsAdapter.IDeleteItem onItemDelete) {
        this.mIDeleteItem = onItemDelete;
    }

    public interface OnItemClickListener {
        void onItemClick(HistoryRowType question);
    }

    public interface IDeleteItem {
        void onDeleteItem(Question question);
    }
}
