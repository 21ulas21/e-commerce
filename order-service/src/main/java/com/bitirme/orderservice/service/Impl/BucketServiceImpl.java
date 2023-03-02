package com.bitirme.orderservice.service.Impl;


import com.bitirme.orderservice.dto.BucketDto;
import com.bitirme.orderservice.dto.InventoryDto;
import com.bitirme.orderservice.dto.ProductDto;
import com.bitirme.orderservice.model.Bucket;
import com.bitirme.orderservice.model.Order;
import com.bitirme.orderservice.model.OrderItems;
import com.bitirme.orderservice.repository.BucketRepository;
import com.bitirme.orderservice.service.BucketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BucketServiceImpl implements BucketService {

    private final BucketRepository repository;
    private final OrderItemServiceImpl orderItemService;
    private final OrderServiceImpl orderService;
    WebClient webClient = WebClient.create("http://localhost:8080/product");
    WebClient inventoryClient = WebClient.create("http://localhost:8181/api/inventory");

    //SEPETE ÜRÜN EKLEME
    public String addItem(String productId, Integer quantity, String userId){
        InventoryDto inventoryDto = inventoryService(productId);
        if (inventoryDto.getQuantity()>=quantity){
            ProductDto productDto = productService(productId);
            OrderItems orderItems =orderItemService.creatOrderItem(productDto, quantity);
            Bucket resultBucket = repository.findByUserId(userId);
            resultBucket.getOrderItems().add(orderItems);
            repository.save(resultBucket);
            return "Ürün sepete Eklendi";
        }else {
            return "Stokta " + inventoryDto.getQuantity() + " adet ürün mevcut";
        }

    }
    //SEPET OLUŞTURMA
    public void createBucket(String userId){

        Bucket bucket = new Bucket();
        List<OrderItems> orderItems = new ArrayList<>();
        bucket.setUserId(userId);
        bucket.setOrderItems(orderItems);
        repository.save(bucket);
    }
    /*
    kullanıcıdan productId ve userId alıyor veri tabanında userId ile sorgu yapıp kişinin sepetini buluyor, sepette belirtilen
    ürünün productId ile aramasını yapıyor ve sepetten çıkarıp veritabanına tekrar kaydediyor
     */
    //SEPETTEN ÜRÜN ÇIKARMA
    public void removeItem(String productId, String userId){
        Bucket bucket = repository.findByUserId(userId);
       List<OrderItems> orderItemsList = repository.findByUserId(userId).getOrderItems();
        OrderItems orderItems1 = orderItemsList.stream()
                                    .filter(orderItems -> orderItems.getProductId()==productId)
                                    .findFirst().get();
        orderItemsList.remove(orderItems1);
        repository.save(bucket);
    }
    //userId ile productDto çeken metot
    public BucketDto getBucket(String userId){
    Bucket bucket = repository.findByUserId(userId);
    List<OrderItems> orderItemsList = bucket.getOrderItems();

    orderItemsList.stream().forEach(orderItems -> bucket.setTotalPrice(orderItems.getQuantity()*orderItems.getProductPrice()+ bucket.getTotalPrice()));

    return toDto(bucket);
    }
    //inventory-serviceten inventory nesnesi çeken metot
    public InventoryDto inventoryService(String productId){
    InventoryDto inventoryDto =inventoryClient.get()
            .uri("/{productId}",productId)
            .retrieve()
            .bodyToMono(InventoryDto.class)
            .block();
        return inventoryDto;
    }
    //product servisten id ile product nesnesi çekmeyi sağlayan metot
    public ProductDto productService(String productId){
        return webClient.get()
                .uri("/{id}", productId)
                .retrieve()
                .bodyToMono(ProductDto.class)
                .block();
    }

    public BucketDto toDto(Bucket bucket){
        return BucketDto.builder()
                .id(bucket.getId())
                .orderItems(bucket.getOrderItems())
                .totalPrice(bucket.getTotalPrice())
                .userId(bucket.getUserId())
                .build();
    }
    public Order createOrder(String userId) {
        Bucket bucket = repository.findByUserId(userId);
        List<OrderItems> orderItemsList = bucket.getOrderItems();
        orderItemsList.stream()
                .filter(orderItems -> inventoryService(orderItems.getProductId()).getQuantity()<orderItems.getQuantity())
                .findFirst()
                .ifPresent(order -> {throw new RuntimeException(order.getProductName()+" stokta yeterli miktarda ürünü bulunmamakta");});
       Order order = orderService.createOrder(bucket);
        bucket.getOrderItems().clear();
        bucket.setTotalPrice(0);
        repository.save(bucket);
        return order;
    }


/*
sepet oluşturma=create(userId)
ürün ekleme=+
ürün çıkarma=+
sepetteki ürünleri gösterme=+
siparişi oluşturma=




 */



















}
