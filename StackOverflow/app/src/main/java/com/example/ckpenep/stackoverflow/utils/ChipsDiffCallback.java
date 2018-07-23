package com.example.ckpenep.stackoverflow.utils;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import java.util.List;

public class ChipsDiffCallback extends DiffUtil.Callback {

    private final List<String> oldList;
    private final List<String> newList;

    public ChipsDiffCallback(List<String> oldList, List<String> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition)
    {
        try {
            return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        final String oldChips = oldList.get(oldItemPosition);
        final String newChips = newList.get(newItemPosition);

        oldChips.equals(newChips);
        return false;
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        // Implement method if you're going to use ItemAnimator
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
