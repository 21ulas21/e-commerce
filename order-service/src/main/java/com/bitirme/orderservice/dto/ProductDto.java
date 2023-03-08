package com.bitirme.orderservice.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private String id;
    private String barcode;
    private Date creaDate;
    private String name;
    private String brand;
    private Double price;
    private String description;
    private String imageUrl;


}
