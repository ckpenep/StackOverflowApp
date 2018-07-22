package com.example.ckpenep.stackoverflow.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ckpenep.stackoverflow.R;
import com.example.ckpenep.stackoverflow.model.tag.Tag;

import java.util.ArrayList;
import java.util.List;

public class TagsAdapter extends RecyclerView.Adapter<TagsAdapter.TagHolder> {

    private List<Tag> tags;
    private OnItemClickListener onItemClickListener;

    public TagsAdapter() {
        tags = new ArrayList<>();
    }

    @Override
    public TagsAdapter.TagHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tag, parent, false);
        return new TagHolder(view);
    }

    @Override
    public void onBindViewHolder(TagHolder holder, int position) {
        holder.bind(tags.get(position));
    }

    @Override
    public int getItemCount() {
        return tags.size();
    }

    public void setData(List<Tag> items) {
        this.tags.clear();
        this.tags.addAll(items);

        notifyDataSetChanged();
    }

    class TagHolder extends RecyclerView.ViewHolder {
        private TextView countryTextView;

        public TagHolder(View itemView) {
            super(itemView);

            countryTextView = (TextView) itemView.findViewById(R.id.tag_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(tags.get(getAdapterPosition()));
                }
            });
        }

        public void bind(Tag tag) {
            countryTextView.setText(tag.getName());
        }
    }

    public void setOnItemClickListener(OnItemClickListener  onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(Tag tag);
    }
}
