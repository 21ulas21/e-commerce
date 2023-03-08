package com.bitirme.productservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Product implements Serializable {

 public static final String COL_ID = "id";
 public static final String COL_NAME ="name";
 public static final String COL_DESCRIPTION = "description";
 public static final String COL_CREATE_DATE="created_date";
 public static final String COL_BRAND="brand";
 public static final String COL_BARCODE="barcode";
 public static final String COL_PRICE="price";
 public static final String COL_CATEGORY_ID="category_id";

 @Id
@GeneratedValue(generator = "UUID")
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
 String id;
 @Column(name = COL_BARCODE)
 private String barcode;

 @CreatedDate
 @Temporal(TemporalType.TIMESTAMP)
 @Column(name = COL_CREATE_DATE)
 private Date creaDate;

 @Column(name=COL_NAME)
 private String name;

 @Column(name=COL_BRAND)
 private String brand;

 @Column(name = COL_PRICE)
 private Double price;

 @Column(name = COL_DESCRIPTION)
 private String description;

 @ManyToOne
 @JoinColumn(name = COL_CATEGORY_ID)
 Category category;



}
