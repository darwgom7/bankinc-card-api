package com.darwgom.bankinccardapi.application.dto;

import com.darwgom.bankinccardapi.domain.enums.TransactionStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionOutputDTO {
    private String transactionNumber;
    private String cardNumber;
    private LocalDateTime transactionDate;
    private BigDecimal amount;
    private TransactionStatusEnum status;
}
