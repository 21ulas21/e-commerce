package com.bitirme.orderservice.model;

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
public class Order {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

     @CreatedDate
     @Temporal(TemporalType.TIMESTAMP)
     private Date createdDate;

     private String personId;

     @OneToMany()
     private List<OrderItems> orderItems;

     private Double totalPrice;
}
