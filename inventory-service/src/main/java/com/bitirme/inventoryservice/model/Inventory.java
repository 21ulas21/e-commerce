package com.bitirme.inventoryservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {
    private static final String COL_ID="id";
    private static final String COL_PRODUCT_ID="product_id";
    private static final String COL_QUANTİTY="quantity";
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = COL_ID)
    private String id;
    @Column(name = COL_PRODUCT_ID)
    private String productId;
    @Column(name = COL_QUANTİTY)
    private Integer quantity;

}
