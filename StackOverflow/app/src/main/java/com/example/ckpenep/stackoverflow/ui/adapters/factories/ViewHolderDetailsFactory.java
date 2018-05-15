package com.example.ckpenep.stackoverflow.ui.adapters.factories;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.ckpenep.stackoverflow.R;

public class ViewHolderDetailsFactory {

    public static class QuestionViewHolder extends RecyclerView.ViewHolder {

        public TextView count_likes;
        public TextView title;
        public TextView tags;
        public WebView webView;

        public QuestionViewHolder(View itemView) {
            super(itemView);

            count_likes = (TextView) itemView.findViewById(R.id.question_count_likes);
            title = (TextView) itemView.findViewById(R.id.detail_question_title);
            tags = (TextView) itemView.findViewById(R.id.detail_question_tags);
            webView = (WebView) itemView.findViewById(R.id.detail_question_webview);
        }
    }

    public static class AnswerViewHolder extends RecyclerView.ViewHolder {

        public AnswerViewHolder(View itemView) {
            super(itemView);

        }
    }


    public static RecyclerView.ViewHolder create(ViewGroup parent, int viewType) {

        switch (viewType) {
            case DetailsRowType.QUESTION_ROW_TYPE:
                View historyTypeView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_question_details, parent, false);
                return new ViewHolderDetailsFactory.QuestionViewHolder(historyTypeView);

            case DetailsRowType.ANSWER_ROW_TYPE:
                View dateTypeView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_answer_details, parent, false);
                return new ViewHolderDetailsFactory.AnswerViewHolder(dateTypeView);

            default:
                return null;
        }
    }
}
