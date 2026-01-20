package com.creditcard.card.dto;
import lombok.*;
import java.math.BigDecimal;
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class UpdateCardRequest {
    private BigDecimal creditLimit; private Boolean contactlessEnabled; private Boolean internationalEnabled; private Boolean onlineEnabled;
}
