package com.example.ckpenep.stackoverflow.presentation.mappers;

import com.example.ckpenep.stackoverflow.model.dto.questions.OwnerItem;
import com.example.ckpenep.stackoverflow.model.question.Owner;

import io.reactivex.Observable;

public class OwnerMapper {
    public static Owner fromResultsItemToTasks(OwnerItem ownerItem) {
        if(ownerItem != null) {
            return Observable.just(ownerItem)
                    .map(dOwner -> new Owner(dOwner.getReputation(), dOwner.getUserId(), dOwner.getUserType(), dOwner.getAcceptRate(), dOwner.getProfileImage(), dOwner.getDisplayName(), dOwner.getLink()))
                    .blockingFirst();
        }
        else
        {
            return null;
        }
    }
}
