package com.bitirme.orderservice.service.Impl;



import com.bitirme.orderservice.dto.CartDto;
import com.bitirme.orderservice.dto.InventoryDto;
import com.bitirme.orderservice.dto.ProductDto;
import com.bitirme.orderservice.model.Cart;
import com.bitirme.orderservice.model.Order;
import com.bitirme.orderservice.model.OrderItems;
import com.bitirme.orderservice.repository.CartRepository;

import com.bitirme.orderservice.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository repository;
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
            Cart resultCart = repository.findByPersonId(userId);
            resultCart.getOrderItems().add(orderItems);
            repository.save(resultCart);
            return "Ürün sepete Eklendi";
        }else {
            return "Stokta " + inventoryDto.getQuantity() + " adet ürün mevcut";
        }

    }
    //SEPET OLUŞTURMA
    public void createCart(String userId){

        Cart cart = new Cart();
        List<OrderItems> orderItems = new ArrayList<>();
        cart.setPersonId(userId);
        cart.setOrderItems(orderItems);
        repository.save(cart);
    }
    /*
    kullanıcıdan productId ve userId alıyor veri tabanında userId ile sorgu yapıp kişinin sepetini buluyor, sepette belirtilen
    ürünün productId ile aramasını yapıyor ve sepetten çıkarıp veritabanına tekrar kaydediyor
     */
    //SEPETTEN ÜRÜN ÇIKARMA
    public void removeItem(String productId, String userId){
        Cart cart = repository.findByPersonId(userId);
       List<OrderItems> orderItemsList = repository.findByPersonId(userId).getOrderItems();
        OrderItems orderItems1 = orderItemsList.stream()
                                    .filter(orderItems -> orderItems.getProductId().equals(productId))
                                    .findFirst().get();
        orderItemsList.remove(orderItems1);
        repository.save(cart);
    }
    //userId ile productDto çeken metot
    public CartDto getCart(String personId){
    Cart cart = repository.findByPersonId(personId);
    List<OrderItems> orderItemsList = cart.getOrderItems();

    orderItemsList.stream()
            .forEach(orderItems ->
                    cart.setTotalPrice(orderItems.getQuantity()*orderItems.getProductPrice()+ cart.getTotalPrice()));

    return toDto(cart);
    }
    //inventory-serviceten inventory nesnesi çeken metot
    public InventoryDto inventoryService(String productId){


        return inventoryClient.get()
            .uri("/{productId}",productId)
            .retrieve()
            .bodyToMono(InventoryDto.class)
            .block();

    }
    //product servisten id ile product nesnesi çekmeyi sağlayan metot
    public ProductDto productService(String productId){
        return webClient.get()
                .uri("/{id}", productId)
                .retrieve()
                .bodyToMono(ProductDto.class)
                .block();
    }

    public CartDto toDto(Cart cart){
        return CartDto.builder()
                .id(cart.getId())
                .orderItems(cart.getOrderItems())
                .totalPrice(cart.getTotalPrice())
                .personId(cart.getPersonId())
                .build();
    }
    public Order createOrder(String userId) {
        Cart cart = repository.findByPersonId(userId);
        List<OrderItems> orderItemsList = cart.getOrderItems();
        orderItemsList.stream()
                .filter(orderItems -> inventoryService(orderItems.getProductId()).getQuantity()<orderItems.getQuantity())
                .findFirst()
                .ifPresent(order -> {throw new RuntimeException(order.getProductName()+" stokta yeterli miktarda ürünü bulunmamakta");});
       Order order = orderService.createOrder(cart);
        cart.getOrderItems().clear();
        cart.setTotalPrice(0);
        repository.save(cart);
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
