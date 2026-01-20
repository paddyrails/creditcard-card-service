package com.creditcard.card.repository;
import com.creditcard.card.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface CardRepository extends JpaRepository<Card, String> {
    List<Card> findByAccountId(String accountId);
    Optional<Card> findByCardNumber(String cardNumber);
    boolean existsByCardNumber(String cardNumber);
}
