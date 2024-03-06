package com.darwgom.bankinccardapi.application.usecases;

import com.darwgom.bankinccardapi.application.dto.TransactionOutputDTO;

import java.math.BigDecimal;

public interface TransactionUseCase {
    TransactionOutputDTO createPurchase(String cardId, BigDecimal amount);
    TransactionOutputDTO getTransaction(String transactionId);
    TransactionOutputDTO annulTransaction(String transactionId, String cardId);
}
