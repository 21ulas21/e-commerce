package com.bitirme.orderservice.service.Impl;


import com.bitirme.orderservice.config.InventoryWebClient;
import com.bitirme.orderservice.config.PaymentWebClient;
import com.bitirme.orderservice.dto.CartDto;
import com.bitirme.orderservice.dto.OrderDto;
import com.bitirme.orderservice.model.Order;
import com.bitirme.orderservice.repository.OrderRepository;
import com.bitirme.orderservice.request.PaymentRequest;
import com.bitirme.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;
    private final CartServiceImpl cartService;
    private final PaymentWebClient paymentService;
    private final InventoryWebClient inventoryService;

    public List<OrderDto> getAllOrder(){
        Authentication authentication = getUser();
        String user = authentication.getName();
        List<Order> orders = repository.findByPersonId(user);
        return orders.stream().map(this::toDto).collect(Collectors.toList());

    }


    public OrderDto createOrderEntity(CartDto dto){
        Order order = new Order();
        order.setOrderItems(dto.getOrderItems());
        order.setPersonId(dto.getPersonId());
        order.setTotalPrice(dto.getTotalPrice());
        order.setOrderItems(dto.getOrderItems());
       return toDto(repository.save(order));
    }

    public OrderDto createOrder(){
       CartDto cartDto = cartService.getCartById();
       OrderDto orderDto = createOrderEntity(cartDto);
       Authentication user = getUser();
       boolean status = paymentService.createPayment(orderDto.getId(),user.getName(), orderDto.getTotalPrice());
       if (status){
           System.out.println("!STATUS çalıştı");
           cartDto.getOrderItems().forEach(orderItems -> inventoryService.decStock(orderItems.getProductId(), orderItems.getQuantity()));
           return orderDto;
       }
        System.out.println("!STATUS çalışmadı");

       return  orderDto;
    }


    public OrderDto toDto(Order order){
        return OrderDto.builder()
                .id(order.getId())
                .orderItems(order.getOrderItems())
                .totalPrice(order.getTotalPrice())
                .personId(order.getPersonId())
                .creatDate(order.getCreatedDate())
                .build();
    }

    private Authentication getUser(){
        return SecurityContextHolder.getContext().getAuthentication();
    }




}
