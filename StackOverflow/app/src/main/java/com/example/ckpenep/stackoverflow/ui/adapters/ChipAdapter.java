package com.example.ckpenep.stackoverflow.ui.adapters;

import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ckpenep.stackoverflow.R;
import com.example.ckpenep.stackoverflow.utils.ChipsDiffCallback;

import java.util.ArrayList;
import java.util.List;

public class ChipAdapter extends RecyclerView.Adapter<ChipAdapter.ChipHolder> {

    private List<String> chips;
    private OnChipsClickItem onItemClickListener;

    public ChipAdapter() {
        chips = new ArrayList<>();
    }

    @Override
    public ChipAdapter.ChipHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chips, parent, false);
        return new ChipAdapter.ChipHolder(view);
    }

    @Override
    public void onBindViewHolder(ChipAdapter.ChipHolder holder, int position) {
        holder.bind(chips.get(position));
    }

    @Override
    public int getItemCount() {
        return chips.size();
    }

    public void setData(List<String> items) {

        final ChipsDiffCallback diffCallback = new ChipsDiffCallback(this.chips, items);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        this.chips.clear();
        this.chips.addAll(items);

        diffResult.dispatchUpdatesTo(this);
    }

    class ChipHolder extends RecyclerView.ViewHolder {
        private TextView chipName;

        public ChipHolder(View itemView) {
            super(itemView);

            chipName = (TextView) itemView.findViewById(R.id.chip_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onChipsClick(getAdapterPosition());
                }
            });
        }

        public void bind(String name) {
            chipName.setText(name);
        }
    }

    public void setOnItemClickListener(OnChipsClickItem onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnChipsClickItem {
        void onChipsClick(int position);
    }
}
