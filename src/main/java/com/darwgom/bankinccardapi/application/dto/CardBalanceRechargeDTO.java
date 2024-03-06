package com.darwgom.bankinccardapi.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardBalanceRechargeDTO {
    private String cardId;
    private BigDecimal balance;
}
