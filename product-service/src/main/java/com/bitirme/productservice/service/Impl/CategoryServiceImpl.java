package com.bitirme.productservice.service.Impl;

import com.bitirme.productservice.dto.CategoryDto;
import com.bitirme.productservice.exception.CategoryNotFoundException;
import com.bitirme.productservice.model.Category;
import com.bitirme.productservice.repository.CategoryRepository;
import com.bitirme.productservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    public String authName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @Override
    @Transactional
    public CategoryDto createCategory(CategoryDto dto) {
        checkCategoryExist(dto);
        Category category = toEntity(dto);
        logger.info("kategori oluşturuldu " +dto.getName()+" " + authName());
        return toDto(repository.save(category));
    }
    @Override
    @Transactional
    public CategoryDto updateCategory(String id, CategoryDto dto) {
        return repository.findById(id)
                .map(category -> checkCategoryUpdate(dto,category))
                .map(repository::save)
                .map(this::toDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Category getByName(String name) {
        return repository.findByName(name)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found   " + getClass().getName()));
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    @Override
    public Category getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found   " + getClass().getName()));
    }

    @Override
    public void deleteCategory(String id) {
       repository.deleteById(id);
        logger.info(id + " Kategori silindi "+ authName());
    }

    public Category toEntity(CategoryDto dto) {
        Category category = new Category();
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        return category;

    }
    public CategoryDto toDto(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .creaDate(category.getCreaDate())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }
    private Category checkCategoryUpdate(CategoryDto dto, Category category) {
        category.setName(dto.getName()==null?category.getName():dto.getName());
        category.setDescription(dto.getDescription()==null?category.getDescription():dto.getDescription());
        logger.info(category.getName() +" kategori güncellendi "+authName());
        return category;
    }

    private void checkCategoryExist(CategoryDto dto) {
        repository.findByName(dto.getName()).ifPresent(category -> {
            throw new EntityExistsException(
                    String.format("Entity %s already exists", category.getClass().getName())
            );
        });
    }
}
