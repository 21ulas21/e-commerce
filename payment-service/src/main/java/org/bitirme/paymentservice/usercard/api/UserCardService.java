package org.bitirme.paymentservice.usercard.api;

import org.bitirme.paymentservice.usercard.api.UserCardDto;

import java.util.List;

public interface UserCardService {

    UserCardDto createCard(UserCardDto userCardDto);

    UserCardDto updateCard(UserCardDto userCardDto, String cardId);

    void deleteCard(String cardId);

    List<UserCardDto> getAllCard();

    UserCardDto getCard(String cardId);

}
