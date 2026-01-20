package com.creditcard.card.model;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity @Table(name = "cards") @Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Card {
    @Id @GeneratedValue(strategy = GenerationType.UUID) private String id;
    @Column(name = "card_number", unique = true, nullable = false) private String cardNumber;
    @Column(name = "account_id", nullable = false) private String accountId;
    @Column(name = "cardholder_name", nullable = false) private String cardholderName;
    @Enumerated(EnumType.STRING) @Column(name = "card_type") private CardType cardType;
    @Column(precision = 15, scale = 2) private BigDecimal creditLimit;
    @Column(precision = 15, scale = 2) private BigDecimal availableCredit;
    private LocalDate expiryDate;
    private String cvv;
    @Enumerated(EnumType.STRING) private CardStatus status;
    private LocalDate issueDate;
    private Boolean contactlessEnabled;
    private Boolean internationalEnabled;
    private Boolean onlineEnabled;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @PrePersist void onCreate() { createdAt = updatedAt = LocalDateTime.now(); if(status==null) status=CardStatus.PENDING_ACTIVATION; }
    @PreUpdate void onUpdate() { updatedAt = LocalDateTime.now(); }
}
