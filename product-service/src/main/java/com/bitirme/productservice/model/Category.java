package com.bitirme.productservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_category")
public class Category {
    private static final String COL_ID = "id";
    public static final String COL_NAME ="name";
    public static final String COL_DESCRIPTION="description";
    private static final String COL_CREATE_DATE = "created_date";
    private static final String COL_PRODUCT_ID = "product_id";


    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = COL_ID)
    private String id;

    @Column(name = COL_NAME)
    private  String name;

    @Column(name = COL_DESCRIPTION)
    private String description;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = COL_CREATE_DATE)
    private Date creaDate;

    @OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
    @Column(name = COL_PRODUCT_ID)
    private List<Product> urunListesi;






}
