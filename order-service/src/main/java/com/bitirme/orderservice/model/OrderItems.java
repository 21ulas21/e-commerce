package com.bitirme.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;



@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "t_order_item")
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
}
