package com.bitirme.orderservice.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "siparis")
@EntityListeners(AuditingEntityListener.class)
@ApiModel(value = "order entity", description = "entity")
public class Order {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @ApiModelProperty(value = "order-id")
    private String id;

     @CreatedDate
     @Temporal(TemporalType.TIMESTAMP)
     @ApiModelProperty(value = "oluşturulma tarihi")
     private Date createdDate;

    @ApiModelProperty(value = "kullanıcı id")
     private String personId;

     @OneToMany()
     private List<OrderItems> orderItems;

     private Double totalPrice;
}
