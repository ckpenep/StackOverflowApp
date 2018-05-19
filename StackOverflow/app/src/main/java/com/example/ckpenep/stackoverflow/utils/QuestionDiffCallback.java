package com.example.ckpenep.stackoverflow.utils;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import com.example.ckpenep.stackoverflow.model.question.Question;
import com.example.ckpenep.stackoverflow.model.dto.history.QuestionDate;
import com.example.ckpenep.stackoverflow.ui.adapters.factories.HistoryRowType;

import java.util.List;

public class QuestionDiffCallback extends DiffUtil.Callback {

    private final List<HistoryRowType> oldList;
    private final List<HistoryRowType> newList;

    public QuestionDiffCallback(List<HistoryRowType> oldList, List<HistoryRowType> newList) {
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
        if (oldList.get(oldItemPosition) instanceof Question && newList.get(newItemPosition) instanceof Question) {
            return ((Question) oldList.get(oldItemPosition)).getId() == ((Question) newList.get(newItemPosition)).getId();
        }
        if (oldList.get(oldItemPosition) instanceof QuestionDate && newList.get(newItemPosition) instanceof QuestionDate) {
            return (((QuestionDate) oldList.get(oldItemPosition)).getDate() == ((QuestionDate) newList.get(oldItemPosition)).getDate());
        }
        return false;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        final HistoryRowType oldQuestion = oldList.get(oldItemPosition);
        final HistoryRowType newQuestion = newList.get(newItemPosition);

        if (oldQuestion instanceof Question && newQuestion instanceof Question) {
            return ((Question) oldQuestion).getId().equals(((Question) newQuestion).getId());
        } else if (oldQuestion instanceof QuestionDate && newQuestion instanceof QuestionDate) {
            return ((QuestionDate) oldQuestion).getDate().equals(((QuestionDate) newQuestion).getDate());
        }
        return false;
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        // Implement method if you're going to use ItemAnimator
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
