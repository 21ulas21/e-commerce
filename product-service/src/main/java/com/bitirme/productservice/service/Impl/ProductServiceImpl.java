package com.bitirme.productservice.service.Impl;

import com.bitirme.productservice.config.InventoryWebClient;
import com.bitirme.productservice.dto.CategoryDto;
import com.bitirme.productservice.dto.ProductDto;
import com.bitirme.productservice.exception.ProductNotFoundException;
import com.bitirme.productservice.model.Category;
import com.bitirme.productservice.model.Product;
import com.bitirme.productservice.repository.ProductRepository;
import com.bitirme.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    private final ProductRepository repository;
    private final CategoryServiceImpl categoryService;
    private final InventoryWebClient inventoryService;


    public String authName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @Override
    @Transactional
    public ProductDto createProduct(ProductDto dto) {
        checkProductExists(dto);
       Product product =repository.save(toEntity(dto));
         inventoryService.createInventory(product.getId());
         logger.info("Ürün oluşturuldu "+dto.getName()+authName());
        return toDto(product);
    }
    @Override
    @Transactional
    public ProductDto updateProduct(String id, ProductDto dto) {
        return repository.findById(id)
                .map(product -> checkProductUpdate(dto,product))
                .map(repository::save)
                .map(this::toDto)
                .orElseThrow(() ->new ProductNotFoundException("Ürün Bulunamadı"));

    }
    @Override
    public List<ProductDto> getAllProducts() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    @Override
    public List<ProductDto> getProductByCategory(String categoryName){
        Category category = categoryService.getByName(categoryName);
        return category.getUrunListesi().stream().map(this::toDto).toList();
    }

    @Override
    public ProductDto getProductById(String id) {
        Product product = repository.findById(id).orElseThrow(() -> new ProductNotFoundException(" Kayıt bulunamadı"));
        return toDto(product);
    }

    @Override
    public List<ProductDto> getAllProductByField(String field) {
        logger.info(field + " özelliğine göre ürünler listelendi");
        List<Product> products =repository.findAll(Sort.by(Sort.Direction.ASC,field));
        return products.stream().map(this::toDto).collect(Collectors.toList());

    }

    @Override
    public void deleteProduct(String id) {
        repository.deleteById(id);
        logger.info("ürün silindi " + id);
    }

    private void checkProductExists(ProductDto dto) {
        repository.findByName(dto.getName()).ifPresent(product -> {
            throw new ProductNotFoundException(
                    String.format("Entity %s already exists", product.getClass().getName())
            );
        });
    }


    private Product checkProductUpdate(ProductDto dto, Product product) {
        product.setName(dto.getName()==null?product.getName():dto.getName());
        product.setPrice(dto.getPrice()==null?product.getPrice():dto.getPrice());
        product.setBrand(dto.getBrand()==null?product.getBrand():dto.getBrand());
        product.setBarcode(dto.getBarcode()==null?product.getBarcode():dto.getBarcode());
        product.setDescription(dto.getDescription()==null?product.getDescription():dto.getDescription());
        logger.info("ürün güncellebdi " + product.getName() + " " + authName());
        return product;
    }

    private Product toEntity(ProductDto dto) {
        Category category = categoryService.getById(dto.getCategory().getId());
        Product product = new Product();
        product.setName(dto.getName());
        product.setBrand(dto.getBrand());
        product.setBarcode(dto.getBarcode());
        product.setCategory(category);
        product.setPrice(dto.getPrice());
        product.setDescription(dto.getDescription());
        return product;
    }
    private ProductDto toDto(Product product){
        CategoryDto category = categoryService.toDto(product.getCategory());
        return ProductDto.builder()
                .id(product.getId())
                .creaDate(product.getCreaDate())
                .description(product.getDescription())
                .brand(product.getBrand())
                .name(product.getName())
                .barcode(product.getBarcode())
                .price(product.getPrice())
                .category(category)
                .build();
    }


}
