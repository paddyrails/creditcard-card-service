package com.creditcard.card.controller;
import com.creditcard.card.dto.*;
import com.creditcard.card.service.CardService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/api/cards") @RequiredArgsConstructor @Tag(name="Card Management")
public class CardController {
    private final CardService svc;
    @PostMapping public ResponseEntity<CardDTO> create(@Valid @RequestBody CreateCardRequest req) { return ResponseEntity.status(HttpStatus.CREATED).body(svc.createCard(req)); }
    @GetMapping("/{id}") public ResponseEntity<CardDTO> getById(@PathVariable String id) { return ResponseEntity.ok(svc.getCardById(id)); }
    @GetMapping("/account/{accountId}") public ResponseEntity<List<CardDTO>> getByAccount(@PathVariable String accountId) { return ResponseEntity.ok(svc.getCardsByAccountId(accountId)); }
    @GetMapping public ResponseEntity<List<CardDTO>> getAll() { return ResponseEntity.ok(svc.getAllCards()); }
    @PutMapping("/{id}") public ResponseEntity<CardDTO> update(@PathVariable String id, @Valid @RequestBody UpdateCardRequest req) { return ResponseEntity.ok(svc.updateCard(id,req)); }
    @PostMapping("/{id}/activate") public ResponseEntity<CardDTO> activate(@PathVariable String id) { return ResponseEntity.ok(svc.activateCard(id)); }
    @PostMapping("/{id}/block") public ResponseEntity<CardDTO> block(@PathVariable String id) { return ResponseEntity.ok(svc.blockCard(id)); }
    @PostMapping("/{id}/unblock") public ResponseEntity<CardDTO> unblock(@PathVariable String id) { return ResponseEntity.ok(svc.unblockCard(id)); }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable String id) { svc.deleteCard(id); return ResponseEntity.noContent().build(); }
}
