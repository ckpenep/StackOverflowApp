package com.example.ckpenep.stackoverflow.ui.adapters;

import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.ckpenep.stackoverflow.R;
import com.example.ckpenep.stackoverflow.model.question.Question;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuestionListAdapter extends RecyclerBindableAdapter<Question, QuestionListAdapter.ItemViewHolder> {

    private OnItemClickListener onItemClickListener;

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

        public ItemViewHolder(View itemView) {
            super(itemView);
            view = itemView;

            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(getItem(getAdapterPosition()));
                }
            });
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
                bgShape.setStroke(4,  ContextCompat.getColor(count_answers.getContext(), R.color.color_line));
                //bgShape.setAlpha(45);
                count_answers.setTextColor(ContextCompat.getColor(count_answers.getContext(), R.color.answer_color_foreground));
            }
            else {

                bgShape.setStroke(4, ContextCompat.getColor(count_answers.getContext(), R.color.answer_background));
                bgShape.setAlpha(255);
                count_answers.setTextColor(ContextCompat.getColor(count_answers.getContext(), R.color.answer_background));
            }

        }
    }

    public void setOnItemClickListener(OnItemClickListener  onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(Question question);
    }
}
