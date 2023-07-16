package com.bitirme.orderservice.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "Cart entity", description = "Entity")
public class Cart {

    private final static String COL_ID="id";
    private final static String COL_PERSON_ID = "person_id";
    private final static String COL_TOTAL_PRICE="total_price";
    private final static String COL_ITEM_LIST="item_list";

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = COL_ID)
    @ApiModelProperty(value = "sepet id")
    private String id;

    @ApiModelProperty("sepete ait olan kişi")
    @Column(name = COL_PERSON_ID)
    private String personId;

    @ApiModelProperty(value ="toplan tutar" )
    @Column(name = COL_TOTAL_PRICE)
    private double totalPrice;

    @ApiModelProperty(value = "sepetteki ürünler, fiyatları ve adedleri")
    @OneToMany()
    public List<OrderItems> orderItems;

}
