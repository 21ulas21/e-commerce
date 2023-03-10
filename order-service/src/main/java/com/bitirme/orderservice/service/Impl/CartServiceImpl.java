package com.bitirme.orderservice.service.Impl;



import com.bitirme.orderservice.config.InventoryWebClient;
import com.bitirme.orderservice.config.ProductWebClient;
import com.bitirme.orderservice.dto.CartDto;
import com.bitirme.orderservice.dto.InventoryDto;
import com.bitirme.orderservice.dto.ProductDto;
import com.bitirme.orderservice.model.Cart;
import com.bitirme.orderservice.model.OrderItems;
import com.bitirme.orderservice.repository.CartRepository;
import com.bitirme.orderservice.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository repository;
    private final OrderItemServiceImpl orderItemService;
    private final InventoryWebClient inventoryService;
    private final ProductWebClient productService;


    //SEPETE ÜRÜN EKLEME
    public String addItem(String productId, Integer quantity, String userId){
        InventoryDto inventoryDto = inventoryService.getInventory(productId);
        if (inventoryDto.getQuantity()>=quantity){
            ProductDto productDto = productService.getProductById(productId);
            OrderItems orderItems =orderItemService.creatOrderItem(productDto, quantity);
            Cart resultCart = repository.findByPersonId(userId);
            resultCart.getOrderItems().add(orderItems);
            repository.save(resultCart);
            return "Ürün sepete Eklendi";
        }
        return "Stokta yeterli miktarda ürün bulunmamakta";

    }
    //SEPET OLUŞTURMA
    public void createCart(String userId){

        Cart cart = new Cart();
        List<OrderItems> orderItems = new ArrayList<>();
        cart.setPersonId(userId);
        cart.setOrderItems(orderItems);
        repository.save(cart);
    }

    //SEPETTEN ÜRÜN ÇIKARMA
    public void removeItem(String productId, String userId){
        Cart cart = repository.findByPersonId(userId);
       List<OrderItems> orderItemsList = repository.findByPersonId(userId).getOrderItems();
        OrderItems orderItems1 = orderItemsList.stream()
                                    .filter(orderItems -> orderItems.getProductId().equals(productId))
                                    .findFirst().get();
        orderItemsList.remove(orderItems1);
        repository.save(cart);    }


    //userId ile sepeti getiren metot
    public CartDto getCart(String personId){
    Cart cart = repository.findByPersonId(personId);
    List<OrderItems> orderItems = cart.getOrderItems();
    orderItems.forEach(orderItems1 -> orderItems1
            .setProductPrice(productService
                    .getProductById(orderItems1.getProductId())
                    .getPrice()));
    cart.setOrderItems(orderItems);
    Cart cart1 = calculatePrice(cart);
    repository.save(cart1);
    return toDto(cart1);
    }


    public CartDto getCartById(String personId){//A ve B ürününden
        checkInventory(personId);
        Cart cart = repository.findByPersonId(personId);
        List<OrderItems> orderItems = new ArrayList<>(cart.getOrderItems());
        CartDto cartDto = toDto(cart);
        cart.getOrderItems().clear();
        repository.save(cart);
        cartDto.setOrderItems(orderItems);

        return  cartDto;


    }
    public Cart calculatePrice(Cart cart){
        List<OrderItems> orderItemsList = cart.getOrderItems();
        Double temp = orderItemsList.stream()
                .mapToDouble(orderItems -> orderItems.getProductPrice()*orderItems.getQuantity())
                .sum();
        cart.setTotalPrice(temp);
        return cart;

    }
    public void checkInventory(String personId){
        Cart cart = repository.findByPersonId(personId);
        List<OrderItems> orderItems = cart.getOrderItems();
        orderItems.stream()
                .filter(orderItems1
                        -> inventoryService
                        .getInventory(orderItems1.getProductId()).getQuantity()>=orderItems1.getQuantity())
                .findFirst()
                .orElseThrow(
                        () -> new RuntimeException("ürün stokta bulunamadı"));

    }



    public CartDto toDto(Cart cart){
        return CartDto.builder()
                .id(cart.getId())
                .orderItems(cart.getOrderItems())
                .totalPrice(cart.getTotalPrice())
                .personId(cart.getPersonId())
                .build();
    }
















}
