package org.bitirme.paymentservice.usercard.impl;

import lombok.Builder;
import lombok.Data;
import org.bitirme.paymentservice.usercard.api.UserCardDto;

@Data
@Builder
public class UserCardResponse {
    private final String id;
    private final String userId;
    private final String cardNumber;
    private final String cvvNumber;
    private final String cardOwner;
    private final String cardDate;

    public static UserCardResponse fromDto(UserCardDto dto){
        return UserCardResponse.builder()
                .id(dto.getId())
                .userId(dto.getUserId())
                .cardNumber(dto.getCardNumber())
                .cvvNumber(dto.getCvvNumber())
                .cardOwner(dto.getCardOwner())
                .cardDate(dto.getCardDate())
                .build();
    }


}
