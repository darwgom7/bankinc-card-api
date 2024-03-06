package com.darwgom.bankinccardapi.domain.entities;

import com.darwgom.bankinccardapi.domain.enums.TransactionStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String transactionNumber;

    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;

    private LocalDateTime transactionDate;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private TransactionStatusEnum status = TransactionStatusEnum.PENDING;

}

