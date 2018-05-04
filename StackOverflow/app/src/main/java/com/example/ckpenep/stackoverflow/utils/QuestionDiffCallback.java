package com.example.ckpenep.stackoverflow.utils;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import com.example.ckpenep.stackoverflow.model.Question;

import java.util.List;

public class QuestionDiffCallback extends DiffUtil.Callback {

    private final List<Question> oldList;
    private final List<Question> newList;

    public QuestionDiffCallback(List<Question> oldList, List<Question> newList) {
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
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) //сравнивает id из коллекции, появился ли новый элемент
    {
        return oldList.get(oldItemPosition).getId() == newList.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        final Question oldQuestion = oldList.get(oldItemPosition);
        final Question newQuestion = newList.get(newItemPosition);

        return oldQuestion.getId().equals(newQuestion.getId());
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        // Implement method if you're going to use ItemAnimator
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
