package com.bitirme.orderservice.config;

import com.bitirme.orderservice.dto.InventoryDto;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import static com.bitirme.orderservice.constants.InventoryConstants.GET_INVENTORY_BY_PRODUCT_ID;

@Service
public class InventoryWebClient {
    private final static String INVENTORY_SERVICE_BASE_URL="http://localhost:8181/api/inventory";
    private final WebClient webClient;

    public InventoryWebClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(INVENTORY_SERVICE_BASE_URL).build();
    }

    public InventoryDto getInventory(String productId){
       return webClient
               .get()
               .uri(GET_INVENTORY_BY_PRODUCT_ID,productId)
               .retrieve()
               .bodyToMono(InventoryDto.class)
               .block();

    }




}
