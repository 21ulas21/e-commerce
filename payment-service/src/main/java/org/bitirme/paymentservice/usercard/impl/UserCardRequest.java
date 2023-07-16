package org.bitirme.paymentservice.usercard.impl;

import lombok.Builder;
import lombok.Data;
import org.bitirme.paymentservice.usercard.api.UserCardDto;

@Data
@Builder
public class UserCardRequest {
    private final String userId;
    private final String cardNumber;
    private final String cvvNumber;
    private final String cardOwner;
    private final String cardDate;


    public UserCardDto toDto(){
        return UserCardDto.builder()
                .userId(userId)
                .cardNumber(cardNumber)
                .cvvNumber(cvvNumber)
                .cardOwner(cardOwner)
                .cardDate(cardDate)
                .build();
    }



}
