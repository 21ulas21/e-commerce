package com.bitirme.orderservice.repository;

import com.bitirme.orderservice.model.Bucket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BucketRepository extends JpaRepository<Bucket, String> {

    Bucket findByUserId(String userId);



}
