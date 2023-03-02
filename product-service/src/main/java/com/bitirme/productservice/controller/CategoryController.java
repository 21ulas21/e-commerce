package com.bitirme.productservice.controller;

import com.bitirme.productservice.dto.CategoryDto;
import com.bitirme.productservice.request.CategoryRequest;
import com.bitirme.productservice.response.CategoryResponse;
import com.bitirme.productservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(@Valid @RequestBody CategoryRequest request) {
        CategoryDto category = categoryService.createCategory(request.toDto());
        return ResponseEntity.ok(CategoryResponse.toResponse(category));
    }
    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategory (){
        List<CategoryResponse> categoryResponseList = toResponse(categoryService.getAllCategory());
        return ResponseEntity.ok(categoryResponseList);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable(value = "id")String id, @Valid @RequestBody CategoryDto dto){
        CategoryDto category = categoryService.updateCategory(id, dto);
        return ResponseEntity.ok(CategoryResponse.toResponse(category));
    }

    @DeleteMapping("/{id}") void delete(@PathVariable String id ){
        categoryService.deleteCategory(id);
    }

    public List<CategoryResponse>toResponse(List<CategoryDto> categoryDtoList){
        return categoryDtoList.stream().map(CategoryResponse::toResponse).toList();

    }
}
