package com.bitirme.orderservice.config;

import com.bitirme.orderservice.dto.ProductDto;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


@Service
public class ProductWebClient {
    private final static String PRODUCT_SERVICE_BASE_URL ="http://PRODUCT-SERVICE/product";
    public final static String GET_PRODUCT_BY_ID="/{id}";

    private final WebClient webClient;


    public ProductWebClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(PRODUCT_SERVICE_BASE_URL).build();
    }

    public ProductDto getProductById(String productId){
        return webClient
                .get()
                .uri(GET_PRODUCT_BY_ID,productId)
                .retrieve()
                .bodyToMono(ProductDto.class)
                .block();
    }




}
