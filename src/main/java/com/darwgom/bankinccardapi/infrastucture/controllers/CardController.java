package com.darwgom.bankinccardapi.infrastucture.controllers;

import com.darwgom.bankinccardapi.application.dto.*;
import com.darwgom.bankinccardapi.application.usecases.CardUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("card")
public class CardController {

    @Autowired
    private CardUseCase cardUseCase;

    @GetMapping("/{productId}/number")
    public ResponseEntity<CardNumberOutputDTO> generateCardNumber(@PathVariable Long productId) {
        CardNumberOutputDTO cardNumberOutputDTO = cardUseCase.generateCardNumber(productId);
        return new ResponseEntity<>(cardNumberOutputDTO, HttpStatus.OK);
    }

    @PostMapping("/enroll")
    public ResponseEntity<CardDTO> activateCard(@RequestBody CardActivationInputDTO cardActivationInputDTO) {
        CardDTO cardDTO = cardUseCase.activateCard(cardActivationInputDTO.getCardId());
        return new ResponseEntity<>(cardDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{cardId}")
    public ResponseEntity<CardDTO> blockCard(@PathVariable String cardId) {
        CardDTO cardDTO = cardUseCase.blockCard(cardId);
        return new ResponseEntity<>(cardDTO, HttpStatus.OK);
    }

    @PostMapping("/balance")
    public ResponseEntity<CardDTO> rechargeCard(@RequestBody CardBalanceRechargeDTO cardBalanceRechargeDTO) {
        CardDTO cardDTO = cardUseCase.rechargeCard(cardBalanceRechargeDTO.getCardId(), cardBalanceRechargeDTO.getBalance());
        return new ResponseEntity<>(cardDTO, HttpStatus.OK);
    }

    @GetMapping("/balance/{cardId}")
    public ResponseEntity<CardBalanceOutputDTO> getCardBalance(@PathVariable String cardId) {
        CardBalanceOutputDTO balanceResponseDTO = cardUseCase.getCardBalance(cardId);
        return new ResponseEntity<>(balanceResponseDTO, HttpStatus.OK);
    }

}
