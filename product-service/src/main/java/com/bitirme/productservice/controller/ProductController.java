package com.bitirme.productservice.controller;

import com.bitirme.productservice.dto.ProductDto;
import com.bitirme.productservice.request.ProductRequest;
import com.bitirme.productservice.response.ProductResponse;
import com.bitirme.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService service;
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping()
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest request) {
        ProductDto product = service.createProduct(request.toDto());
        return ResponseEntity.ok(ProductResponse.toResponse(product));
    }

    @GetMapping("allproduct/{categoryName}")
    public ResponseEntity<List<ProductResponse>> getAllProducts(@PathVariable String categoryName){
        List<ProductResponse> productResponseList = toResponse(service.getProductByCategory(categoryName));
        return ResponseEntity.ok(productResponseList);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts(){
        List<ProductResponse> productResponseList = toResponse(service.getAllProducts());
        return ResponseEntity.ok(productResponseList);
    }
    @GetMapping("allproducts/{field}")
    public ResponseEntity<List<ProductResponse>> getAllProductByField(@PathVariable String field){
        List<ProductResponse> responses =toResponse(service.getAllProductByField(field));
        return ResponseEntity.ok(responses);


    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable(value = "id")String id,@Valid @RequestBody ProductRequest request){
        ProductDto product = service.updateProduct(id, request.toDto());
        return ResponseEntity.ok(ProductResponse.toResponse(product));
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    void delete(@PathVariable String id){
        service.deleteProduct(id);
    }
    public List<ProductResponse>toResponse(List<ProductDto> productDtoList){
        return productDtoList.stream().map(ProductResponse::toResponse).toList();

    }
    //Order-Service metodu
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable String id){
        ProductDto productDto = service.getProductById(id);
       return ResponseEntity.ok(ProductResponse.toResponse(productDto));

    }









}
