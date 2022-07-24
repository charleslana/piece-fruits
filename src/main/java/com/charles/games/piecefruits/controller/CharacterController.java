package com.charles.games.piecefruits.controller;

import com.charles.games.piecefruits.model.dto.ListCharacterDTO;
import com.charles.games.piecefruits.service.CharacterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/character")
@RequiredArgsConstructor
@Slf4j
public class CharacterController {

    private final CharacterService service;

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ResponseEntity<List<ListCharacterDTO>> getAll() {
        log.info("REST to get all characters");
        return ResponseEntity.ok(service.getAll());
    }
}
