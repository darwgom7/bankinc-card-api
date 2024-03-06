package com.darwgom.bankinccardapi.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionAnnulmentDTO {
    private String cardId;
    private String transactionId;
}
