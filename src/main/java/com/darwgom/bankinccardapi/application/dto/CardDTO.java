package com.darwgom.bankinccardapi.application.dto;

import com.darwgom.bankinccardapi.domain.enums.CardStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardDTO {
    private Long id;
    private String cardNumber;
    private Long productId;
    private String cardholderName;
    private LocalDate expirationDate;
    private BigDecimal balance;
    private CardStatusEnum status;
}
