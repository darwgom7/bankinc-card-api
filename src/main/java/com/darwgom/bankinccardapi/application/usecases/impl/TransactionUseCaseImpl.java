package com.darwgom.bankinccardapi.application.usecases.impl;

import com.darwgom.bankinccardapi.application.dto.TransactionOutputDTO;
import com.darwgom.bankinccardapi.application.usecases.TransactionUseCase;
import com.darwgom.bankinccardapi.domain.entities.Card;
import com.darwgom.bankinccardapi.domain.entities.Transaction;
import com.darwgom.bankinccardapi.domain.enums.CardStatusEnum;
import com.darwgom.bankinccardapi.domain.enums.CurrencyUnitEnum;
import com.darwgom.bankinccardapi.domain.enums.TransactionStatusEnum;
import com.darwgom.bankinccardapi.domain.exceptions.*;
import com.darwgom.bankinccardapi.domain.exceptions.IllegalStateException;
import com.darwgom.bankinccardapi.domain.repositories.CardRepository;
import com.darwgom.bankinccardapi.domain.repositories.TransactionRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.javamoney.moneta.Money;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.money.MonetaryAmount;
import java.security.SecureRandom;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class TransactionUseCaseImpl implements TransactionUseCase {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private ModelMapper modelMapper;

    private static final SecureRandom random = new SecureRandom();

    private static final String CURRENT_CURRENCY_UNIT = "USD";

    @Override
    public TransactionOutputDTO createPurchase(String cardId, BigDecimal amount) {

        Card card = cardRepository.findByCardNumber(cardId)
                .orElseThrow(() -> new EntityNotFoundException("Card not found with number: " + cardId));

        MonetaryAmount monetaryAmount = Money.of(amount, CurrencyUnitEnum.USD.toString());

        if (!CURRENT_CURRENCY_UNIT.equals(monetaryAmount.getCurrency().getCurrencyCode())) {
            throw new IllegalCurrencyException("Currency must be USD");
        }

        if (card.getStatus() != CardStatusEnum.ACTIVE) {
            throw new IllegalStateException("Transaction cannot be completed: Card is not active.");
        }

        if (card.getBalance().compareTo(amount) < 0) {
            throw new InsufficientFundsException("Insufficient funds for the transaction.");
        }

        String transactionNumber = generateUniqueTransactionNumber();

        card.setBalance(card.getBalance().subtract(amount));
        cardRepository.save(card);

        Transaction transaction = new Transaction();
        transaction.setTransactionNumber(transactionNumber);
        transaction.setCard(card);
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setAmount(amount);
        transaction.setStatus(TransactionStatusEnum.COMPLETED);

        Transaction savedTransaction = transactionRepository.save(transaction);
        TransactionOutputDTO transactionOutputDTO = modelMapper.map(savedTransaction, TransactionOutputDTO.class);
        transactionOutputDTO.setTransactionNumber(transaction.getTransactionNumber());
        transactionOutputDTO.setCardNumber(transaction.getCard().getCardNumber());
        return transactionOutputDTO;
    }

    @Override
    public TransactionOutputDTO getTransaction(String transactionId) {
        Transaction transaction = transactionRepository.findByTransactionNumber(transactionId)
                .orElseThrow(() -> new EntityNotFoundException("Transaction not found with id: " + transactionId));
        TransactionOutputDTO transactionOutputDTO = modelMapper.map(transaction, TransactionOutputDTO.class);
        transactionOutputDTO.setTransactionNumber(transaction.getTransactionNumber());
        transactionOutputDTO.setCardNumber(transaction.getCard().getCardNumber());
        return transactionOutputDTO;
    }

    @Override
    public TransactionOutputDTO annulTransaction(String transactionId, String cardId) {
        Transaction transaction = transactionRepository.findByTransactionNumber(transactionId)
                .orElseThrow(() -> new EntityNotFoundException("Transaction not found with id: " + transactionId));

        if (!transaction.getCard().getCardNumber().equals(cardId)) {
            throw new IllegalParamException("Card ID does not match the transaction record.");
        }

        if (transaction.getStatus() == TransactionStatusEnum.ANNULLED) {
            throw new IllegalStateException("Transaction is already annulled.");
        }

        if (transaction.getTransactionDate().isBefore(LocalDateTime.now().minusHours(24))) {
            throw new IllegalStateException("Transaction cannot be cancelled after 24 hours.");
        }

        Card card = transaction.getCard();
        card.setBalance(card.getBalance().add(transaction.getAmount()));
        cardRepository.save(card);

        transaction.setStatus(TransactionStatusEnum.ANNULLED);
        transaction = transactionRepository.save(transaction);

        TransactionOutputDTO transactionOutputDTO = modelMapper.map(transaction, TransactionOutputDTO.class);
        transactionOutputDTO.setTransactionNumber(transaction.getTransactionNumber());
        transactionOutputDTO.setCardNumber(transaction.getCard().getCardNumber());
        return transactionOutputDTO;
    }

    private String generateUniqueTransactionNumber() {
        Integer num;
        String uniqueCode;
        do {
            num = 100000 + random.nextInt(900000);
            uniqueCode = String.valueOf(num);
        } while (transactionRepository.existsByTransactionNumber(uniqueCode));

        return uniqueCode;
    }

}
