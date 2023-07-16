package org.bitirme.paymentservice.usercard.impl;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserCardRepository extends JpaRepository<UserCard, String> {

    Optional<UserCard> findByUserId(String userId);
}
