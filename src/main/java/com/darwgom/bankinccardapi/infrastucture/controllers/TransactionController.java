package com.darwgom.bankinccardapi.infrastucture.controllers;

import com.darwgom.bankinccardapi.application.dto.TransactionAnnulmentDTO;
import com.darwgom.bankinccardapi.application.dto.TransactionOutputDTO;
import com.darwgom.bankinccardapi.application.dto.TransactionPurchaseDTO;
import com.darwgom.bankinccardapi.application.usecases.TransactionUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("transaction")
public class TransactionController {

    @Autowired
    private TransactionUseCase transactionUseCase;

    @PostMapping("/purchase")
    public ResponseEntity<TransactionOutputDTO> createPurchase(@Valid @RequestBody TransactionPurchaseDTO transactionPurchaseDTO) {
        TransactionOutputDTO transactionOutputDTO = transactionUseCase.createPurchase(
                transactionPurchaseDTO.getCardId(),
                transactionPurchaseDTO.getPrice()
        );
        return new ResponseEntity<>(transactionOutputDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<TransactionOutputDTO> getTransaction(@PathVariable String transactionId) {
        TransactionOutputDTO transactionOutputDTO = transactionUseCase.getTransaction(transactionId);
        return new ResponseEntity<>(transactionOutputDTO, HttpStatus.OK);
    }

    @PostMapping("/anulation")
    public ResponseEntity<TransactionOutputDTO> annulTransaction(@RequestBody TransactionAnnulmentDTO transactionAnnulmentDTO) {
        TransactionOutputDTO transactionOutputDTO = transactionUseCase.annulTransaction(
                transactionAnnulmentDTO.getTransactionId(),
                transactionAnnulmentDTO.getCardId()
        );
        return new ResponseEntity<>(transactionOutputDTO, HttpStatus.OK);
    }

}
