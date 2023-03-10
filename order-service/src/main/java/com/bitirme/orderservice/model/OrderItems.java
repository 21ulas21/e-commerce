package com.bitirme.orderservice.model;


import lombok.Data;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Data
@Entity
@Table(name = "ordered_item")
public class OrderItems {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private String productId;
    private String productName;
    private double productPrice;
    private String imageUrl;
    private Integer quantity;
  /*  @ManyToOne()
    @JoinColumn(name = "cart_id")
    private Cart cart;
*/


}
