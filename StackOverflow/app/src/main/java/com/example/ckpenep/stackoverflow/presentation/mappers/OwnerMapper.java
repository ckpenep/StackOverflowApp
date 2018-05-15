package com.example.ckpenep.stackoverflow.presentation.mappers;

import com.example.ckpenep.stackoverflow.model.Owner;
import com.example.ckpenep.stackoverflow.model.dto.datails.OwnerDetail;

import io.reactivex.Observable;

public class OwnerMapper {
    public static Owner fromResultsItemToTasks(OwnerDetail ownerItem) {
        return Observable.just(ownerItem)
                .map(dOwner -> new Owner(dOwner.getReputation(), dOwner.getUserId(), dOwner.getUserType(), dOwner.getAcceptRate(), dOwner.getProfileImage(), dOwner.getDisplayName(), dOwner.getLink()))
                .blockingFirst();
    }
}
