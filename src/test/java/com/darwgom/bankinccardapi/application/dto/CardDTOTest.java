package com.darwgom.bankinccardapi.application.dto;

import com.darwgom.bankinccardapi.domain.enums.CardStatusEnum;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class CardDTOTest {
    @Test
    public void testNoArgsConstructor() {
        CardDTO cardDTO = new CardDTO();
        assertNotNull(cardDTO);
    }

    @Test
    public void testAllArgsConstructor() {
        CardDTO cardDTO = new CardDTO(1L, "6890987654567876", 1L, "Card Holder", LocalDate.now(), BigDecimal.ZERO, CardStatusEnum.ACTIVE);
        assertNotNull(cardDTO);
        assertEquals("6890987654567876", cardDTO.getCardNumber());
    }

    @Test
    public void testGettersAndSetters() {
        CardDTO cardDTO = new CardDTO();
        cardDTO.setId(1L);
        cardDTO.setCardNumber("6790987654345676");
        assertEquals(1L, cardDTO.getId());
        assertEquals("6790987654345676", cardDTO.getCardNumber());
    }

    @Test
    public void testEqualsAndHashCode() {
        CardDTO cardDTO1 = new CardDTO(1L, "6123456789456896", null, "Card Holder", LocalDate.now(), BigDecimal.ZERO, CardStatusEnum.ACTIVE);
        CardDTO cardDTO2 = new CardDTO(1L, "6123456789456896", null, "Card Holder", LocalDate.now(), BigDecimal.ZERO, CardStatusEnum.ACTIVE);
        assertEquals(cardDTO1, cardDTO2);
        assertEquals(cardDTO1.hashCode(), cardDTO2.hashCode());
    }

    @Test
    public void testToString() {
        CardDTO cardDTO = new CardDTO(1L, "5678908765432456", 1L, "Card Holder", LocalDate.now(), BigDecimal.ZERO, CardStatusEnum.ACTIVE);
        String cardString = cardDTO.toString();
        assertTrue(cardString.contains("5678908765432456"));
    }
}
