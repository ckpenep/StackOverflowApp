package com.example.ckpenep.stackoverflow.ui.adapters.factories;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ckpenep.stackoverflow.R;
import com.example.ckpenep.stackoverflow.model.datails.QuestionDetail;
import com.example.ckpenep.stackoverflow.ui.adapters.CommentsDetailsAdapter;
import com.example.ckpenep.stackoverflow.utils.Tools;
import com.example.ckpenep.stackoverflow.utils.ViewAnimation;
import com.squareup.picasso.Picasso;

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
        public LinearLayout editorBlock;

        public TextView dateEditQuestion;
        public ImageView editorImage;
        public TextView editorName;
        public TextView editorRating;

        public RecyclerView commentsRecycerView;
        public ImageView commentsButtonExpend;
        public LinearLayout commentsExpanded;

        public QuestionViewHolder(View itemView) {
            super(itemView);

            count_likes = (TextView) itemView.findViewById(R.id.question_count_likes);
            title = (TextView) itemView.findViewById(R.id.detail_question_title);
            tags = (TextView) itemView.findViewById(R.id.detail_question_tags);
            webView = (WebView) itemView.findViewById(R.id.detail_question_webview);

            dateCreateQuestion = (TextView) itemView.findViewById(R.id.owner_date_create_question);
            ownerImage = (ImageView) itemView.findViewById(R.id.owner_image);
            ownerName = (TextView) itemView.findViewById(R.id.owner_name);
            ownerRating = (TextView) itemView.findViewById(R.id.owner_rating);

            editorContainer = (LinearLayout) itemView.findViewById(R.id.editor_container);
            editorBlock = (LinearLayout) itemView.findViewById(R.id.editor_block);

            dateEditQuestion = (TextView) itemView.findViewById(R.id.editor_date_create_question);
            editorImage = (ImageView) itemView.findViewById(R.id.editor_image);
            editorName = (TextView) itemView.findViewById(R.id.editor_name);
            editorRating = (TextView) itemView.findViewById(R.id.editor_rating);

            commentsButtonExpend = (ImageView) itemView.findViewById(R.id.bt_expand);
            commentsExpanded = (LinearLayout) itemView.findViewById(R.id.comments_expanded);


            commentsRecycerView = (RecyclerView) itemView.findViewById(R.id.comments_recycler_view);
            commentsRecycerView.setHasFixedSize(true);
            commentsRecycerView.setNestedScrollingEnabled(false);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(itemView.getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            commentsRecycerView.setLayoutManager(linearLayoutManager);
            commentsRecycerView.addItemDecoration(new DividerItemDecoration(commentsRecycerView.getContext(), linearLayoutManager.getOrientation()));
        }

        public void bind(QuestionDetail questionDetail) {

            title.setText(questionDetail.getTitle());
            count_likes.setText(questionDetail.getAnswerCount().toString());
            tags.setText(questionDetail.getTagsByString());

            webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setVerticalScrollBarEnabled(false);
            webView.loadData(questionDetail.getBody(), "text/html", null);

            webView.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageFinished(WebView webView, String url) {
                    super.onPageFinished(webView, url);
                    int height = webView.getMeasuredHeight();

                    webView.clearFocus();
                    //TransitionManager.beginDelayedTransition(recyclerView);
                    //notifyDataSetChanged();
                }});

            if (questionDetail.getOwner() != null) {
                if (questionDetail.getOwner().getProfileImage() != null)
                    Picasso.get().load(questionDetail.getOwner().getProfileImage()).into(ownerImage);
                if (questionDetail.getOwner().getDisplayName() != null)
                    ownerName.setText(questionDetail.getOwner().getDisplayName());
                if (questionDetail.getOwner().getReputation() != null)
                    ownerRating.setText(Integer.toString(questionDetail.getOwner().getReputation()));
                if (questionDetail.getCreationDate() != null)
                    dateCreateQuestion.setText("Asked " + questionDetail.getDateByString(questionDetail.getCreationDate()));
            }
            if (questionDetail.getEditor() != null) {
                if(!questionDetail.getEditor().getUserId().equals(questionDetail.getEditor().getUserId())) {
                    if (questionDetail.getEditor().getProfileImage() != null)
                        Picasso.get().load(questionDetail.getEditor().getProfileImage()).into(editorImage);
                    if (questionDetail.getEditor().getDisplayName() != null)
                        editorName.setText(questionDetail.getEditor().getDisplayName());
                    if (questionDetail.getEditor().getReputation() != null)
                        editorRating.setText(Integer.toString(questionDetail.getEditor().getReputation()));
                }
                else
                {
                    editorBlock.setVisibility(View.GONE);
                }
                if (questionDetail.getLastEditDate() != null)
                    dateEditQuestion.setText("Edited " + questionDetail.getDateByString(questionDetail.getLastEditDate()));
            } else {
                editorContainer.setVisibility(View.GONE);
            }

            if(questionDetail.getComments() != null && questionDetail.getComments().size() != 0)
            {
                commentsButtonExpend.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean show = toggleLayoutExpand(!questionDetail.isExpanded(), v, commentsRecycerView);
                        questionDetail.setExpanded(show);

                    }
                });
                CommentsDetailsAdapter mAdapter = new CommentsDetailsAdapter(questionDetail.getComments());
                commentsRecycerView.setAdapter(mAdapter);
            }
            else
            {
                commentsExpanded.setVisibility(View.GONE);
            }

            // void recycling view
            if(questionDetail.isExpanded()){
                commentsRecycerView.setVisibility(View.VISIBLE);
            } else {
                commentsRecycerView.setVisibility(View.GONE);
            }
            Tools.toggleArrow(questionDetail.isExpanded(), commentsButtonExpend, false);
        }

        private boolean toggleLayoutExpand(boolean show, View view, View lyt_expand) {
            Tools.toggleArrow(show, view);
            if (show) {
                ViewAnimation.expand(lyt_expand);
            } else {
                ViewAnimation.collapse(lyt_expand);
            }
            return show;
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
