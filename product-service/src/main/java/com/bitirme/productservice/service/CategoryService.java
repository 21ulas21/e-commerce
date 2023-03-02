package com.bitirme.productservice.service;

import com.bitirme.productservice.dto.CategoryDto;
import com.bitirme.productservice.model.Category;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto dto);
    List<CategoryDto> getAllCategory();
    Category getById(String id);
    void deleteCategory(String id);
    CategoryDto updateCategory(String id , CategoryDto dto);
    Category getByName(String name);
}
