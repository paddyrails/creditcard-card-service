package com.creditcard.card.dto;
import com.creditcard.card.model.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class CardDTO {
    private String id; private String maskedCardNumber; private String accountId; private String cardholderName;
    private CardType cardType; private BigDecimal creditLimit; private BigDecimal availableCredit;
    private LocalDate expiryDate; private CardStatus status; private LocalDate issueDate;
    private Boolean contactlessEnabled; private Boolean internationalEnabled; private Boolean onlineEnabled;
}
