package com.creditcard.card.service;
import com.creditcard.card.dto.*;
import java.util.List;
public interface CardService {
    CardDTO createCard(CreateCardRequest request);
    CardDTO getCardById(String id);
    List<CardDTO> getCardsByAccountId(String accountId);
    List<CardDTO> getAllCards();
    CardDTO updateCard(String id, UpdateCardRequest request);
    CardDTO activateCard(String id);
    CardDTO blockCard(String id);
    CardDTO unblockCard(String id);
    void deleteCard(String id);
}
