package com.bitirme.productservice.config;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
@Service
public class InventoryWebClient {


    private final static String INVENTORY_SERVICE_BASE_URL="http://INVENTORY-SERVICE/inventory";
    private final static String INVENTORY_SERVICE_CREATE="/create/{productId}";

    private final WebClient webClient;


    public InventoryWebClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(INVENTORY_SERVICE_BASE_URL).build();
    }

    public void createInventory(String productId){
        webClient.post().uri(INVENTORY_SERVICE_CREATE,productId)
                .header("API_KEY","123456")
                .retrieve()
                .bodyToMono(String.class)
                .block();

    }

}


