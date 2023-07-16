package org.bitirme.paymentservice.usercard.impl;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.bitirme.paymentservice.usercard.api.UserCardDto;
import org.bitirme.paymentservice.usercard.api.UserCardService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class UserCardServiceImpl implements UserCardService {

    private final UserCardRepository repository;

    @Override
    public UserCardDto createCard(UserCardDto userCardDto) {
        var userCard = toEntity(new UserCard(), userCardDto);

        return toDto(repository.save(userCard));
    }

    @Override
    public UserCardDto updateCard(UserCardDto userCardDto, String cardId) {
        return repository.findById(cardId)
                .map(userCard -> toEntity(userCard, userCardDto))
                .map(repository::save)
                .map(this::toDto)
                .orElseThrow(EntityExistsException::new);
    }

    @Override
    public void deleteCard(String cardId) {
        var card = repository.findById(cardId).orElseThrow(EntityExistsException::new);
        repository.delete(card);

    }

    @Override
    public List<UserCardDto> getAllCard() {

        return repository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }


    @Override
    public UserCardDto getCard(String cardId) {

        return repository.findById(cardId)
                .map(this::toDto)
                .orElseThrow(EntityExistsException::new);
    }
    public UserCardDto getUserCardByUserId(String userId){
        return repository.findByUserId(userId)
                .map(this::toDto)
                .orElseThrow(EntityNotFoundException::new);
    }


    public UserCard toEntity(UserCard userCard, UserCardDto dto){
        userCard.setUserId(dto.getUserId());
        userCard.setCardNumber(dto.getCardNumber());
        userCard.setCvvNumber(dto.getCvvNumber());
        userCard.setCardOwner(dto.getCardOwner());
        userCard.setCardDate(dto.getCardDate());
        return userCard;
    }

    public UserCardDto toDto(UserCard userCard){
        return UserCardDto.builder()
                .id(userCard.getId())
                .userId(userCard.getUserId())
                .cardNumber(userCard.getCardNumber())
                .cvvNumber(userCard.getCvvNumber())
                .cardOwner(userCard.getCardOwner())
                .cardDate(userCard.getCardDate())
                .build();
    }





}
