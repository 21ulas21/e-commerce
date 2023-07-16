package org.bitirme.paymentservice.usercard.impl;

import lombok.RequiredArgsConstructor;
import org.bitirme.paymentservice.usercard.api.UserCardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-cards")
@RequiredArgsConstructor
public class UserCardController {

    private final UserCardService service;
    @PostMapping
    public ResponseEntity<UserCardResponse> createCard(@RequestBody UserCardRequest request){
       var result = service.createCard(request.toDto());
       return ResponseEntity.ok(UserCardResponse.fromDto(result));

    }
    @PutMapping("/{id}")
    public ResponseEntity<UserCardResponse> updateCard(@PathVariable(value = "id") String cardId, @RequestBody UserCardRequest request){
        var result = service.updateCard(request.toDto(), cardId);
        return ResponseEntity.ok(UserCardResponse.fromDto(result));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCard(@PathVariable(value = "id") String cardId){
        service.deleteCard(cardId);
        return ResponseEntity.ok("Silme işlemi başarılı");
    }
    @GetMapping("/get-all")
    public ResponseEntity<List<UserCardResponse>> getAllCard(){
        var result = service.getAllCard().stream().map(UserCardResponse::fromDto).toList();
        return ResponseEntity.ok(result);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserCardResponse> getCard(@PathVariable(value = "id") String cardId){
        var result = service.getCard(cardId);
        return ResponseEntity.ok(UserCardResponse.fromDto(result));
    }
}
