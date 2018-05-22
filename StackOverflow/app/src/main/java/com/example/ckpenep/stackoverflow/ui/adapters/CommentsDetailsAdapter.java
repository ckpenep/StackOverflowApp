package com.example.ckpenep.stackoverflow.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ckpenep.stackoverflow.R;
import com.example.ckpenep.stackoverflow.model.datails.CommentDetail;

import java.util.List;

public class CommentsDetailsAdapter extends RecyclerView.Adapter<CommentsDetailsAdapter.CommentDetailsHolder> {
    private List<CommentDetail> mComments;

    public CommentsDetailsAdapter(List<CommentDetail> comments) {
        this.mComments = comments;
    }

    @Override
    public CommentDetailsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent, false);
        return new CommentDetailsHolder(view);
    }

    @Override
    public void onBindViewHolder(CommentDetailsHolder holder, int position) {
        CommentDetail comment = mComments.get(position);
        holder.bind(comment);
    }

    @Override
    public int getItemCount() {
        return mComments.size();
    }

    class CommentDetailsHolder extends RecyclerView.ViewHolder
    {
        private TextView commentOwner;
        private TextView commentText;

        public CommentDetailsHolder(View itemView) {
            super(itemView);
            commentOwner = (TextView) itemView.findViewById(R.id.comment_owner_rating);
            commentText = (TextView) itemView.findViewById(R.id.comment_text);

        }

        public void bind(CommentDetail comment) {
            commentOwner.setText(comment.getOwner().getReputation().toString());
            commentText.setText(comment.getBody());
        }
    }
}
