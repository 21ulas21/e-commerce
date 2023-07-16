package org.bitirme.paymentservice.usercard.impl;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = UserCard.TABLE)
public class UserCard {
    public static final String TABLE="user_card";
    private static final String COL_USER_ID="user_id";
    private static final String COL_CARD_NUMBER="card_number";
    private static final String COL_CVV_NUMBER="cvv_number";
    private static final String COL_CARD_OWNER="card_owner";
    private static final String COL_CARD_DATE="card_date";

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @Column(name = COL_USER_ID)
    private String userId;
    @Column(name = COL_CARD_NUMBER)
    private String cardNumber;
    @Column(name = COL_CVV_NUMBER)
    private String cvvNumber;
    @Column(name = COL_CARD_OWNER)
    private String cardOwner;
    @Column(name = COL_CARD_DATE)
    private String cardDate;

}
