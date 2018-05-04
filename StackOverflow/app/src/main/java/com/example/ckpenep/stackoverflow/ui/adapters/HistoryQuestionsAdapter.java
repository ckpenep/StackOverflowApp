package com.example.ckpenep.stackoverflow.ui.adapters;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ckpenep.stackoverflow.R;
import com.example.ckpenep.stackoverflow.model.Question;
import com.example.ckpenep.stackoverflow.utils.QuestionDiffCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryQuestionsAdapter extends RecyclerView.Adapter<HistoryQuestionsAdapter.HistoryViewHolder> {

    private QuestionListAdapter.OnItemClickListener onItemClickListener;

    private List<Question> mQuestions;

    public HistoryQuestionsAdapter() {
        mQuestions = new ArrayList<>();
    }

    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final HistoryViewHolder holder, final int position) {
        holder.bind(mQuestions.get(position));

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

    public class HistoryViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.count_answers)
        TextView count_answers;
        @BindView(R.id.tags)
        TextView tags;
        public View view;

        public HistoryViewHolder(View itemView) {
            super(itemView);
            view = itemView;

            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(mQuestions.get(getAdapterPosition()));
                }
            });
        }

        void bind(Question item)
        {
            title.setText(item.getTitle());
            count_answers.setText(item.getAnswerCount().toString());
            tags.setText(item.getTagsByString());

            setBorderColor(item.getAnswerCount());
        }

        private void setBorderColor(int count)
        {
            GradientDrawable bgShape = (GradientDrawable)count_answers.getBackground();
            if(count <= 0)
            {
                bgShape.setStroke(4, Color.BLACK);
                bgShape.setAlpha(45);
                count_answers.setTextColor(ContextCompat.getColor(count_answers.getContext(), R.color.answer_color_foreground));
            }
            else {

                bgShape.setStroke(4, ContextCompat.getColor(count_answers.getContext(), R.color.answer_background));
                bgShape.setAlpha(255);
                count_answers.setTextColor(ContextCompat.getColor(count_answers.getContext(), R.color.answer_background));
            }

        }
    }

    public void setOnItemClickListener(QuestionListAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(Question question);
    }
}
