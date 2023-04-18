package com.bitirme.orderservice.service.Impl;



import com.bitirme.orderservice.config.InventoryWebClient;
import com.bitirme.orderservice.config.ProductWebClient;
import com.bitirme.orderservice.dto.CartDto;
import com.bitirme.orderservice.dto.InventoryDto;
import com.bitirme.orderservice.dto.ProductDto;
import com.bitirme.orderservice.exception.MissingStockException;
import com.bitirme.orderservice.model.Cart;
import com.bitirme.orderservice.model.OrderItems;
import com.bitirme.orderservice.repository.CartRepository;
import com.bitirme.orderservice.service.CartService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository repository;
    private final OrderItemServiceImpl orderItemService;
    private final InventoryWebClient inventoryService;
    private final ProductWebClient productService;

    Logger log = LoggerFactory.getLogger(CartServiceImpl.class);


    //SEPETE ÜRÜN EKLEME
    public String addItem(String productId, Integer quantity){
        InventoryDto inventoryDto = inventoryService.getInventory(productId);
        if (inventoryDto.getQuantity()>=quantity){
            ProductDto productDto = productService.getProductById(productId);
            OrderItems orderItems =orderItemService.creatOrderItem(productDto, quantity);
            Cart resultCart = getUserCart();
            resultCart.getOrderItems().add(orderItems);
            repository.save(resultCart);
            log.info("Sepete ürün eklendi "+resultCart.getPersonId()+" "+productDto.getName());
            return "Ürün sepete Eklendi";
        }
        return "Stokta yeterli miktarda ürün bulunmamakta";

    }
    //SEPET OLUŞTURMA
    public Cart createCart(String userId){

        Cart cart = new Cart();
        List<OrderItems> orderItems = new ArrayList<>();
        cart.setPersonId(userId);
        cart.setOrderItems(orderItems);
        repository.save(cart);
        log.info("Sepet oluşturuldu "+cart.getPersonId());

        return cart;
    }

    //SEPETTEN ÜRÜN ÇIKARMA
    public void removeItem(String productId){
        Cart cart = getUserCart();
       List<OrderItems> orderItemsList = cart.getOrderItems();
        OrderItems orderItems1 = orderItemsList.stream()
                                    .filter(orderItems -> orderItems.getProductId().equals(productId))
                                    .findFirst().get();
        orderItemsList.remove(orderItems1);
        log.info(cart.getPersonId()+" Sepetinden ürün çıkarıldı "+productId );
        repository.save(cart);    }


    //userId ile sepeti getiren metot
    public CartDto getCart(){
    Cart cart = getUserCart();
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
        Cart cart = calculatePrice(getUserCart());
        checkInventory();
        List<OrderItems> orderItems = new ArrayList<>(cart.getOrderItems());
        CartDto cartDto = toDto(cart);
        cart.getOrderItems().clear();
        repository.save(cart);
        cartDto.setOrderItems(orderItems);

        return  cartDto;


    }
    public Cart calculatePrice(Cart cart){
        List<OrderItems> orderItemsList = cart.getOrderItems();
        double temp = orderItemsList.stream()
                .mapToDouble(orderItems -> orderItems.getProductPrice()*orderItems.getQuantity())
                .sum();
        cart.setTotalPrice(temp);
        return cart;

    }
    public void checkInventory(){
        Cart cart = getUserCart();
        List<OrderItems> orderItems = cart.getOrderItems();
        orderItems.stream()
                .filter(orderItems1
                        -> inventoryService
                        .getInventory(orderItems1.getProductId()).getQuantity()>=orderItems1.getQuantity())
                .findFirst()
                .orElseThrow(
                        () -> new MissingStockException("ürün stokta bulunamadı"));

    }



    public CartDto toDto(Cart cart){
        return CartDto.builder()
                .id(cart.getId())
                .orderItems(cart.getOrderItems())
                .totalPrice(cart.getTotalPrice())
                .personId(cart.getPersonId())
                .build();
    }

    /*
    TODO: AUTH kısmı yapılacak !!!!!!!
            Filtreleme
            Analiz son 30 ürün getirme

     */

    public Cart getUserCart(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<Cart> cart = repository.findByPersonId(authentication.getName());
        if (cart.isPresent()){
            return cart.get();
        }else return createCart(authentication.getName());

    }

















}
