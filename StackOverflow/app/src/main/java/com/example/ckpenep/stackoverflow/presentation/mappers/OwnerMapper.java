package com.example.ckpenep.stackoverflow.presentation.mappers;

import com.example.ckpenep.stackoverflow.model.Owner;
import com.example.ckpenep.stackoverflow.model.dto.datails.OwnerItem;

import io.reactivex.Observable;

public class OwnerMapper {
    public static Owner fromResultsItemToTasks(OwnerItem ownerItem) {
        return Observable.just(ownerItem)
                .map(dOwner -> new Owner(dOwner.getReputation(), dOwner.getUserId(), dOwner.getUserType(), dOwner.getAcceptRate(), dOwner.getProfileImage(), dOwner.getDisplayName(), dOwner.getLink()))
                .blockingFirst();
    }
}
