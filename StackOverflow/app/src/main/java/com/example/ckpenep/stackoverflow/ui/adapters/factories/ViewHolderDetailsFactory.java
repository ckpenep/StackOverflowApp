package com.example.ckpenep.stackoverflow.ui.adapters.factories;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ckpenep.stackoverflow.R;

public class ViewHolderDetailsFactory {

    public static class QuestionViewHolder extends RecyclerView.ViewHolder {

        public TextView count_likes;
        public TextView title;
        public TextView tags;
        public WebView webView;

        public TextView dateCreateQuestion;
        public ImageView ownerImage;
        public TextView ownerName;
        public TextView ownerRating;

        public LinearLayout editorContainer;
        public LinearLayout commentContainer;
        public LinearLayout editorBlock;

        public TextView dateEditQuestion;
        public ImageView editorImage;
        public TextView editorName;
        public TextView editorRating;

        public QuestionViewHolder(View itemView) {
            super(itemView);

            count_likes = (TextView) itemView.findViewById(R.id.question_count_likes);
            title = (TextView) itemView.findViewById(R.id.detail_question_title);
            tags = (TextView) itemView.findViewById(R.id.detail_question_tags);
            webView = (WebView) itemView.findViewById(R.id.detail_question_webview);
            webView.getSettings().setJavaScriptEnabled(true);
            //webView.setVerticalScrollBarEnabled(false);

            dateCreateQuestion = (TextView) itemView.findViewById(R.id.owner_date_create_question);
            ownerImage = (ImageView) itemView.findViewById(R.id.owner_image);
            ownerName = (TextView) itemView.findViewById(R.id.owner_name);
            ownerRating = (TextView) itemView.findViewById(R.id.owner_rating);

            editorContainer = (LinearLayout) itemView.findViewById(R.id.editor_container);
            commentContainer = (LinearLayout) itemView.findViewById(R.id.comments_container);
            editorBlock = (LinearLayout) itemView.findViewById(R.id.editor_block);

            dateEditQuestion = (TextView) itemView.findViewById(R.id.editor_date_create_question);
            editorImage = (ImageView) itemView.findViewById(R.id.editor_image);
            editorName = (TextView) itemView.findViewById(R.id.editor_name);
            editorRating = (TextView) itemView.findViewById(R.id.editor_rating);
        }
    }

    public static class AnswerViewHolder extends RecyclerView.ViewHolder {

        public AnswerViewHolder(View itemView) {
            super(itemView);
        }
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {

        public HeaderViewHolder(View itemView) {
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

            case DetailsRowType.HEADER_ROW_TYPE:
                View headerTypeView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_header_details, parent, false);
                return new ViewHolderDetailsFactory.HeaderViewHolder(headerTypeView);
            default:
                return null;
        }
    }
}
