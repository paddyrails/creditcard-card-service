package com.creditcard.card.dto;
import com.creditcard.card.model.CardType;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class CreateCardRequest {
    @NotBlank private String accountId;
    @NotBlank @Size(min=2,max=100) private String cardholderName;
    @NotNull private CardType cardType;
    @NotNull @DecimalMin("100.00") @DecimalMax("1000000.00") private BigDecimal creditLimit;
}
