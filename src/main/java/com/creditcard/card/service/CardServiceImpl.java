package com.creditcard.card.service;
import com.creditcard.card.dto.*;
import com.creditcard.card.exception.*;
import com.creditcard.card.model.*;
import com.creditcard.card.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor @Slf4j @Transactional
public class CardServiceImpl implements CardService {
    private final CardRepository repo;
    private final SecureRandom random = new SecureRandom();

    public CardDTO createCard(CreateCardRequest req) {
        Card card = Card.builder().cardNumber(genCardNum()).accountId(req.getAccountId())
            .cardholderName(req.getCardholderName()).cardType(req.getCardType())
            .creditLimit(req.getCreditLimit()).availableCredit(req.getCreditLimit())
            .expiryDate(LocalDate.now().plusYears(5)).cvv(genCVV())
            .status(CardStatus.PENDING_ACTIVATION).issueDate(LocalDate.now()).build();
        return map(repo.save(card));
    }
    @Transactional(readOnly=true) public CardDTO getCardById(String id) { return map(findById(id)); }
    @Transactional(readOnly=true) public List<CardDTO> getCardsByAccountId(String aid) { return repo.findByAccountId(aid).stream().map(this::map).collect(Collectors.toList()); }
    @Transactional(readOnly=true) public List<CardDTO> getAllCards() { return repo.findAll().stream().map(this::map).collect(Collectors.toList()); }
    public CardDTO updateCard(String id, UpdateCardRequest req) {
        Card c = findById(id);
        if(req.getCreditLimit()!=null) c.setCreditLimit(req.getCreditLimit());
        if(req.getContactlessEnabled()!=null) c.setContactlessEnabled(req.getContactlessEnabled());
        if(req.getInternationalEnabled()!=null) c.setInternationalEnabled(req.getInternationalEnabled());
        if(req.getOnlineEnabled()!=null) c.setOnlineEnabled(req.getOnlineEnabled());
        return map(repo.save(c));
    }
    public CardDTO activateCard(String id) { Card c=findById(id); if(c.getStatus()!=CardStatus.PENDING_ACTIVATION) throw new InvalidOperationException("Cannot activate"); c.setStatus(CardStatus.ACTIVE); return map(repo.save(c)); }
    public CardDTO blockCard(String id) { Card c=findById(id); c.setStatus(CardStatus.BLOCKED); return map(repo.save(c)); }
    public CardDTO unblockCard(String id) { Card c=findById(id); if(c.getStatus()!=CardStatus.BLOCKED) throw new InvalidOperationException("Not blocked"); c.setStatus(CardStatus.ACTIVE); return map(repo.save(c)); }
    public void deleteCard(String id) { repo.delete(findById(id)); }

    private Card findById(String id) { return repo.findById(id).orElseThrow(()->new CardNotFoundException("Card not found: "+id)); }
    private String genCardNum() { StringBuilder sb=new StringBuilder("4"); for(int i=0;i<15;i++)sb.append(random.nextInt(10)); String n=sb.toString(); while(repo.existsByCardNumber(n)){sb=new StringBuilder("4");for(int i=0;i<15;i++)sb.append(random.nextInt(10));n=sb.toString();} return n; }
    private String genCVV() { return String.format("%03d",random.nextInt(1000)); }
    private String mask(String n) { return n==null||n.length()<4?"****":"****-****-****-"+n.substring(n.length()-4); }
    private CardDTO map(Card c) { return CardDTO.builder().id(c.getId()).maskedCardNumber(mask(c.getCardNumber())).accountId(c.getAccountId()).cardholderName(c.getCardholderName()).cardType(c.getCardType()).creditLimit(c.getCreditLimit()).availableCredit(c.getAvailableCredit()).expiryDate(c.getExpiryDate()).status(c.getStatus()).issueDate(c.getIssueDate()).contactlessEnabled(c.getContactlessEnabled()).internationalEnabled(c.getInternationalEnabled()).onlineEnabled(c.getOnlineEnabled()).build(); }
}
