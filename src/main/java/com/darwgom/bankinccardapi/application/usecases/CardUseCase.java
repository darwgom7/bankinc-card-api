package com.darwgom.bankinccardapi.application.usecases;

import com.darwgom.bankinccardapi.application.dto.CardBalanceOutputDTO;
import com.darwgom.bankinccardapi.application.dto.CardDTO;
import com.darwgom.bankinccardapi.application.dto.CardNumberOutputDTO;

import java.math.BigDecimal;

public interface CardUseCase {
    CardNumberOutputDTO generateCardNumber(Long productCode);
    CardDTO activateCard(String cardNumber);
    CardDTO blockCard(String cardNumber);
    CardDTO rechargeCard(String cardId, BigDecimal balance);
    CardBalanceOutputDTO getCardBalance(String cardId);

}
