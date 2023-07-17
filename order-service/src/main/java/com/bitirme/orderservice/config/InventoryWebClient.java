package com.bitirme.orderservice.config;

import com.bitirme.orderservice.dto.InventoryDto;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class InventoryWebClient {
    private final static String INVENTORY_SERVICE_BASE_URL="http://INVENTORY-SERVICE/inventory";
    public final static String GET_INVENTORY_BY_PRODUCT_ID="/{productId}";
    public static final String DEC_STOCK="/decstock";
    private final WebClient webClient;

    public InventoryWebClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(INVENTORY_SERVICE_BASE_URL).build();
    }

    public InventoryDto getInventory(String productId){
       return webClient
               .get()
               .uri(GET_INVENTORY_BY_PRODUCT_ID,productId)
               .header("API_KEY","123456")
               .retrieve()
               .bodyToMono(InventoryDto.class)
               .block();

    }
    public void decStock(String productId, int quantity) {
        webClient.put()
                .uri(uriBuilder -> uriBuilder.path("/decstock")
                        .queryParam("productId", productId)
                        .queryParam("quantity", quantity)
                        .build())
                .header("API_KEY", "123456")
                .retrieve()
                .toBodilessEntity()
                .subscribe(response -> {
                    if (response.getStatusCode().is2xxSuccessful()) {
                        System.out.println("İstek başarıyla tamamlandı");
                    } else {
                        System.out.println("İstek başarısız oldu. Hata kodu: " + response.getStatusCode());
                    }
                });
    }




}
