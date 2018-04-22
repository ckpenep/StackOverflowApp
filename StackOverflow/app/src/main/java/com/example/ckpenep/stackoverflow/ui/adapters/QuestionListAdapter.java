package com.example.ckpenep.stackoverflow.ui.adapters;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.ckpenep.stackoverflow.R;
import com.example.ckpenep.stackoverflow.model.Question;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuestionListAdapter extends RecyclerBindableAdapter<Question, QuestionListAdapter.ItemViewHolder> {

    @Override
    protected int layoutId(int type) {
        return R.layout.item_question;
    }

    @Override
    protected ItemViewHolder viewHolder(View view, int type) {
        return new ItemViewHolder(view);
    }

    @Override
    protected void onRecycledItemViewHolder(ItemViewHolder vh) {

    }

    @Override
    protected void onBindItemViewHolder(ItemViewHolder viewHolder, final int position, int type) {
        viewHolder.bind(getItem(position));
    }

    @Override
    protected int getGridSpan(int position) {
        if (isFooter(position)) {
            return getMaxGridSpan();
        }
        return (position % 3 == 0 ? 2 : 1);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.count_answers)
        TextView count_answers;
        @BindView(R.id.date_created)
        TextView created_date;
        @BindView(R.id.tags)
        TextView tags;
        public View view;

        public ItemViewHolder(View v) {
            super(v);
            view = v;

            ButterKnife.bind(this, itemView);
        }

        void bind(Question item)
        {
            title.setText(item.getTitle());
            count_answers.setText(item.getAnswerCount().toString());
            created_date.setText(item.getDateByString());
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
}
