package com.bitirme.orderservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cart {

    private final static String COL_ID="id";
    private final static String COL_PERSON_ID = "person_id";
    private final static String COL_TOTAL_PRICE="total_price";
    private final static String COL_ITEM_LIST="item_list";

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = COL_ID)
    private String id;

    @Column(name = COL_PERSON_ID)
    private String personId;

    @Column(name = COL_TOTAL_PRICE)
    private double totalPrice;

    @OneToMany()
    public List<OrderItems> orderItems;

}
