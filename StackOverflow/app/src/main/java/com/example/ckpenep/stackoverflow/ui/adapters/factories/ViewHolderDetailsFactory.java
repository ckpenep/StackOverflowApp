package com.example.ckpenep.stackoverflow.ui.adapters.factories;

import android.os.Handler;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ckpenep.stackoverflow.R;
import com.example.ckpenep.stackoverflow.model.datails.AnswerDetail;
import com.example.ckpenep.stackoverflow.model.datails.QuestionDetail;
import com.example.ckpenep.stackoverflow.ui.adapters.CommentsDetailsAdapter;
import com.example.ckpenep.stackoverflow.utils.Tools;
import com.example.ckpenep.stackoverflow.utils.ViewAnimation;
import com.squareup.picasso.Picasso;

public class ViewHolderDetailsFactory {

    public class QuestionViewHolder extends RecyclerView.ViewHolder {

        public TextView question_count_likes;
        public TextView question_title;
        public TextView question_tags;
        public WebView question_webView;

        public TextView question_dateCreateQuestion;
        public ImageView question_ownerImage;
        public TextView question_ownerName;
        public TextView question_ownerRating;

        public LinearLayout question_editorContainer;
        public LinearLayout question_editorBlock;

        public TextView question_dateEditQuestion;
        public ImageView question_editorImage;
        public TextView question_editorName;
        public TextView question_editorRating;

        public RecyclerView question_commentsRecycerView;
        public ImageView question_commentsButtonExpend;
        public LinearLayout question_commentsExpanded;

        public QuestionViewHolder(View itemView) {
            super(itemView);

            question_count_likes = (TextView) itemView.findViewById(R.id.question_count_likes);
            question_title = (TextView) itemView.findViewById(R.id.detail_question_title);
            question_tags = (TextView) itemView.findViewById(R.id.detail_question_tags);
            question_webView = (WebView) itemView.findViewById(R.id.detail_question_webview);

            question_dateCreateQuestion = (TextView) itemView.findViewById(R.id.owner_date_create_question);
            question_ownerImage = (ImageView) itemView.findViewById(R.id.owner_image);
            question_ownerName = (TextView) itemView.findViewById(R.id.owner_name);
            question_ownerRating = (TextView) itemView.findViewById(R.id.owner_rating);

            question_editorContainer = (LinearLayout) itemView.findViewById(R.id.editor_container);
            question_editorBlock = (LinearLayout) itemView.findViewById(R.id.editor_block);

            question_dateEditQuestion = (TextView) itemView.findViewById(R.id.editor_date_create_question);
            question_editorImage = (ImageView) itemView.findViewById(R.id.editor_image);
            question_editorName = (TextView) itemView.findViewById(R.id.editor_name);
            question_editorRating = (TextView) itemView.findViewById(R.id.editor_rating);

            question_commentsButtonExpend = (ImageView) itemView.findViewById(R.id.bt_expand);
            question_commentsExpanded = (LinearLayout) itemView.findViewById(R.id.comments_expanded);


            question_commentsRecycerView = (RecyclerView) itemView.findViewById(R.id.comments_recycler_view);
            question_commentsRecycerView.setHasFixedSize(true);
            question_commentsRecycerView.setNestedScrollingEnabled(false);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(itemView.getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            question_commentsRecycerView.setLayoutManager(linearLayoutManager);
            question_commentsRecycerView.addItemDecoration(new DividerItemDecoration(question_commentsRecycerView.getContext(), linearLayoutManager.getOrientation()));
        }

        public void bind(QuestionDetail questionDetail) {

            question_title.setText(questionDetail.getTitle());
            question_count_likes.setText(questionDetail.getAnswerCount().toString());
            question_tags.setText(questionDetail.getTagsByString());

            question_webView.getSettings().setJavaScriptEnabled(true);
            question_webView.setVerticalScrollBarEnabled(false);
            question_webView.loadData(questionDetail.getBody(), "text/html", null);

            question_webView.setWebViewClient(new WebViewClient() {

                public void onPageFinished(WebView view, String url) {
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            question_webView.setVisibility(View.GONE);
                            question_webView.setVisibility(View.VISIBLE);
                        }
                    }, 300);
                }
            });

            if (questionDetail.getOwner() != null) {
                if (questionDetail.getOwner().getProfileImage() != null)
                    Picasso.get().load(questionDetail.getOwner().getProfileImage()).into(question_ownerImage);
                if (questionDetail.getOwner().getDisplayName() != null)
                    question_ownerName.setText(questionDetail.getOwner().getDisplayName());
                if (questionDetail.getOwner().getReputation() != null)
                    question_ownerRating.setText(Integer.toString(questionDetail.getOwner().getReputation()));
                if (questionDetail.getCreationDate() != null)
                    question_dateCreateQuestion.setText("Asked " + questionDetail.getDateByString(questionDetail.getCreationDate()));
            }
            if (questionDetail.getEditor() != null) {
                if (!questionDetail.getEditor().getUserId().equals(questionDetail.getEditor().getUserId())) {
                    if (questionDetail.getEditor().getProfileImage() != null)
                        Picasso.get().load(questionDetail.getEditor().getProfileImage()).into(question_editorImage);
                    if (questionDetail.getEditor().getDisplayName() != null)
                        question_editorName.setText(questionDetail.getEditor().getDisplayName());
                    if (questionDetail.getEditor().getReputation() != null)
                        question_editorRating.setText(Integer.toString(questionDetail.getEditor().getReputation()));
                } else {
                    question_editorBlock.setVisibility(View.GONE);
                }
                if (questionDetail.getLastEditDate() != null)
                    question_dateEditQuestion.setText("Edited " + questionDetail.getDateByString(questionDetail.getLastEditDate()));
            } else {
                question_editorContainer.setVisibility(View.GONE);
            }

            if (questionDetail.getComments() != null && questionDetail.getComments().size() != 0) {
                question_commentsButtonExpend.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean show = toggleLayoutExpand(!questionDetail.isExpanded(), v, question_commentsRecycerView);
                        questionDetail.setExpanded(show);

                    }
                });
                CommentsDetailsAdapter mAdapter = new CommentsDetailsAdapter(questionDetail.getComments());
                question_commentsRecycerView.setAdapter(mAdapter);
            } else {
                question_commentsExpanded.setVisibility(View.GONE);
            }

            // void recycling view
            if (questionDetail.isExpanded()) {
                question_commentsRecycerView.setVisibility(View.VISIBLE);
            } else {
                question_commentsRecycerView.setVisibility(View.GONE);
            }
            Tools.toggleArrow(questionDetail.isExpanded(), question_commentsButtonExpend, false);
        }
    }

    public class AnswerViewHolder extends RecyclerView.ViewHolder {

        public TextView answer_count_likes;
        public TextView answer_title;
        public WebView answer_webView;

        public TextView answer_dateCreateAnswer;
        public ImageView answer_ownerImage;
        public TextView answer_ownerName;
        public TextView answer_ownerRating;

        public LinearLayout answer_editorContainer;
        public LinearLayout answer_editorBlock;

        public TextView answer_dateEditAnswer;
        public ImageView answer_editorImage;
        public TextView answern_editorName;
        public TextView answer_editorRating;

        public RecyclerView answer_commentsRecycerView;
        public ImageView answer_commentsButtonExpend;
        public LinearLayout answer_commentsExpanded;

        public AnswerViewHolder(View itemView) {
            super(itemView);

            answer_count_likes = (TextView) itemView.findViewById(R.id.detail_answer_count_likes);
            answer_title = (TextView) itemView.findViewById(R.id.detail_answer_title);
            answer_webView = (WebView) itemView.findViewById(R.id.detail_answer_webview);

            answer_dateCreateAnswer = (TextView) itemView.findViewById(R.id.detail_answer_owner_date_created);
            answer_ownerImage = (ImageView) itemView.findViewById(R.id.detail_answer_owner_image);
            answer_ownerName = (TextView) itemView.findViewById(R.id.detail_answer_owner_name);
            answer_ownerRating = (TextView) itemView.findViewById(R.id.detail_answer_owner_rating);

            answer_editorContainer = (LinearLayout) itemView.findViewById(R.id.detail_answer_editor_container);
            answer_editorBlock = (LinearLayout) itemView.findViewById(R.id.detail_answer_editor_block);

            answer_dateEditAnswer = (TextView) itemView.findViewById(R.id.detail_answer_editor_date_created);
            answer_editorImage = (ImageView) itemView.findViewById(R.id.detail_answer_editor_image);
            answern_editorName = (TextView) itemView.findViewById(R.id.detail_answer_editor_name);
            answer_editorRating = (TextView) itemView.findViewById(R.id.detail_answer_editor_rating);


            answer_commentsButtonExpend = (ImageView) itemView.findViewById(R.id.detail_answer_bt_expand);
            answer_commentsExpanded = (LinearLayout) itemView.findViewById(R.id.detail_answer_comments_expanded);


            answer_commentsRecycerView = (RecyclerView) itemView.findViewById(R.id.detail_answer_comments_recycler_view);
            answer_commentsRecycerView.setHasFixedSize(true);
            answer_commentsRecycerView.setNestedScrollingEnabled(false);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(itemView.getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            answer_commentsRecycerView.setLayoutManager(linearLayoutManager);
            answer_commentsRecycerView.addItemDecoration(new DividerItemDecoration(answer_commentsRecycerView.getContext(), linearLayoutManager.getOrientation()));
        }

        public void bind(AnswerDetail answerDetail) {

            answer_title.setText(answerDetail.getTitle());
            answer_count_likes.setText(answerDetail.getScore().toString());

            answer_webView.getSettings().setJavaScriptEnabled(true);
            answer_webView.setVerticalScrollBarEnabled(false);
            answer_webView.loadData(answerDetail.getBody(), "text/html", null);

            answer_webView.setWebViewClient(new WebViewClient() {

                public void onPageFinished(WebView view, String url) {
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            answer_webView.setVisibility(View.GONE);
                            answer_webView.setVisibility(View.VISIBLE);
                        }
                    }, 300);
                }
            });

            if (answerDetail.getOwner() != null) {
                if (answerDetail.getOwner().getProfileImage() != null)
                    Picasso.get().load(answerDetail.getOwner().getProfileImage()).into(answer_ownerImage);
                if (answerDetail.getOwner().getDisplayName() != null)
                    answer_ownerName.setText(answerDetail.getOwner().getDisplayName());
                if (answerDetail.getOwner().getReputation() != null)
                    answer_ownerRating.setText(Integer.toString(answerDetail.getOwner().getReputation()));
                if (answerDetail.getCreationDate() != null)
                    answer_dateCreateAnswer.setText("Asked " + answerDetail.getDateByString(answerDetail.getCreationDate()));
            }
            if (answerDetail.getLastEditor() != null) {
                if (!answerDetail.getLastEditor().getUserId().equals(answerDetail.getLastEditor().getUserId())) {
                    if (answerDetail.getLastEditor().getProfileImage() != null)
                        Picasso.get().load(answerDetail.getLastEditor().getProfileImage()).into(answer_editorImage);
                    if (answerDetail.getLastEditor().getDisplayName() != null)
                        answern_editorName.setText(answerDetail.getLastEditor().getDisplayName());
                    if (answerDetail.getLastEditor().getReputation() != null)
                        answer_editorRating.setText(Integer.toString(answerDetail.getLastEditor().getReputation()));
                } else {
                    answer_editorBlock.setVisibility(View.GONE);
                }
                if (answerDetail.getLastEditDate() != null)
                    answer_dateEditAnswer.setText("Edited " + answerDetail.getDateByString(answerDetail.getLastEditDate()));
            } else {
                answer_editorContainer.setVisibility(View.GONE);
            }

            if (answerDetail.getComments() != null && answerDetail.getComments().size() != 0) {
                answer_commentsButtonExpend.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean show = toggleLayoutExpand(!answerDetail.isExpanded(), v, answer_commentsRecycerView);
                        answerDetail.setExpanded(show);

                    }
                });
                CommentsDetailsAdapter mAdapter = new CommentsDetailsAdapter(answerDetail.getComments());
                answer_commentsRecycerView.setAdapter(mAdapter);
            } else {
                answer_commentsExpanded.setVisibility(View.GONE);
            }

            // void recycling view
            if (answerDetail.isExpanded()) {
                answer_commentsRecycerView.setVisibility(View.VISIBLE);
            } else {
                answer_commentsRecycerView.setVisibility(View.GONE);
            }
            Tools.toggleArrow(answerDetail.isExpanded(), answer_commentsButtonExpend, false);
        }
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {

        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }

    public RecyclerView.ViewHolder create(ViewGroup parent, int viewType) {

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
