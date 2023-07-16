package com.bitirme.orderservice.config;


import com.bitirme.orderservice.request.PaymentRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class PaymentWebClient {
    private final static String INVENTORY_SERVICE_BASE_URL="http://PAYMENT-SERVICE/payments";
    public final static String CREATE_PAYMENT="/{id}";
    private final WebClient webClient;

    public PaymentWebClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(INVENTORY_SERVICE_BASE_URL).build();
    }

    public boolean createPayment(String orderId, String userId, Double price){
            return Boolean.TRUE.equals(webClient.post()
                    .bodyValue(createPaymentRequest(orderId, userId, price))
                    .header("API_KEY", "123456")
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block());



    }

    private PaymentRequest createPaymentRequest(String orderId, String userId, Double price){
        return PaymentRequest.builder()
                .orderId(orderId)
                .price(price)
                .userId(userId)
                .build();
    }
}
