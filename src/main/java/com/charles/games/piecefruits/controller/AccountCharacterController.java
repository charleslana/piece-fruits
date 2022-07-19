package com.charles.games.piecefruits.controller;

import com.charles.games.piecefruits.model.dto.CreateAccountCharacterDTO;
import com.charles.games.piecefruits.model.dto.ListAccountCharacterDTO;
import com.charles.games.piecefruits.model.dto.ResponseDTO;
import com.charles.games.piecefruits.model.dto.UpdateAccountCharacterDTO;
import com.charles.games.piecefruits.model.dto.UpdateAccountCharacterNameDTO;
import com.charles.games.piecefruits.service.AccountCharacterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/account/character")
@RequiredArgsConstructor
@Slf4j
public class AccountCharacterController {

    private final AccountCharacterService service;

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<ResponseDTO> create(@RequestBody @Valid CreateAccountCharacterDTO dto) {
        log.info("REST request to create account character: {}", dto);
        return ResponseEntity.ok(service.create(dto));
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable("id") Long id) {
        log.info("REST request to delete account character with id: {}", id);
        return ResponseEntity.ok(service.delete(id));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/details")
    public ResponseEntity<ListAccountCharacterDTO> get() {
        log.info("REST to get account character details");
        return ResponseEntity.ok(service.get());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<ListAccountCharacterDTO>> getAll() {
        log.info("REST to get all account characters");
        return ResponseEntity.ok(service.getAll());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/all")
    public ResponseEntity<List<ListAccountCharacterDTO>> getAllByAccountId() {
        log.info("REST to get all account characters by account id");
        return ResponseEntity.ok(service.getAllByAccountId());
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/logout")
    public ResponseEntity<Void> logoutCharacter() {
        log.info("REST request to logout account character");
        service.logoutCharacter();
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{id}")
    public ResponseEntity<Void> selectCharacter(@PathVariable("id") Long id) {
        log.info("REST request to select account character");
        service.selectCharacter(id);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping
    public ResponseEntity<ResponseDTO> update(@RequestBody @Valid UpdateAccountCharacterDTO dto) {
        log.info("REST request to update account character: {}", dto);
        return ResponseEntity.ok(service.update(dto));
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/name")
    public ResponseEntity<ResponseDTO> updateName(@RequestBody @Valid UpdateAccountCharacterNameDTO dto) {
        log.info("REST request to update account character name: {}", dto.getName());
        return ResponseEntity.ok(service.updateName(dto));
    }
}
