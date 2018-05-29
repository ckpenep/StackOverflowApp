package com.example.ckpenep.stackoverflow.presentation.mappers;

import com.example.ckpenep.stackoverflow.model.datails.OwnerDetail;
import com.example.ckpenep.stackoverflow.model.dto.questions.OwnerItem;
import com.example.ckpenep.stackoverflow.model.question.Owner;

import io.reactivex.Observable;

public class OwnerDetailMapper {
    public static OwnerDetail fromOwnerToOwnerDetails(Owner ownerItem) {
        if (ownerItem != null) {
            return Observable.just(ownerItem)
                    .map(dOwner -> new OwnerDetail(
                            dOwner.getReputation(),
                            dOwner.getUserId(),
                            dOwner.getUserType(),
                            dOwner.getAcceptRate(),
                            dOwner.getProfileImage(),
                            dOwner.getDisplayName(),
                            dOwner.getLink()))
                    .blockingFirst();
        }
        return null;
    }

    public static OwnerDetail fromOwnerToOwnerDetails(OwnerItem ownerItem) {
        if (ownerItem != null) {
            return Observable.just(ownerItem)
                    .map(dOwner -> new OwnerDetail(
                            dOwner.getReputation(),
                            dOwner.getUserId(),
                            dOwner.getUserType(),
                            dOwner.getAcceptRate(),
                            dOwner.getProfileImage(),
                            dOwner.getDisplayName(),
                            dOwner.getLink()))
                    .blockingFirst();
        }
        return null;
    }
}
