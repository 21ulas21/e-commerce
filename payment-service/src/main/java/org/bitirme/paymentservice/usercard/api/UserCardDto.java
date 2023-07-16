package org.bitirme.paymentservice.usercard.api;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserCardDto {
    private final String id;
    private final String userId;
    private final String cardNumber;
    private final String cvvNumber;
    private final String cardOwner;
    private final String cardDate;
}
