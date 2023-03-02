package com.bitirme.orderservice.request;

import com.bitirme.orderservice.model.Bucket;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
@Data
@Builder
public class OrderRequest {
    private String id;
    private Bucket bucket;
    private Date creatDate;
}
