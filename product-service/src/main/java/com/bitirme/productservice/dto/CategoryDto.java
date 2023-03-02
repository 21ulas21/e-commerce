package com.bitirme.productservice.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class CategoryDto {


    private String id;
    private String name;
    private String description;
    private Date creaDate;
}
