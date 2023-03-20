package com.bitirme.productservice.service;

import com.bitirme.productservice.dto.ProductDto;
import com.bitirme.productservice.model.Product;

import java.util.List;

public interface ProductService {

    ProductDto createProduct(ProductDto dto);
    List<ProductDto> getAllProducts();
    void deleteProduct(String id);
    ProductDto updateProduct(String id , ProductDto product);
    List<ProductDto> getProductByCategory(String categoryName);

    ProductDto getProductById(String id);
    List<ProductDto> getAllProductByField(String field);
}
