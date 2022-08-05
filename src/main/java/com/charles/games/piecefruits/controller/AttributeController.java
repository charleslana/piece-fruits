package com.charles.games.piecefruits.controller;

import com.charles.games.piecefruits.model.dto.AttributeCostDTO;
import com.charles.games.piecefruits.model.dto.ResponseDTO;
import com.charles.games.piecefruits.model.dto.UpdateAttributeDTO;
import com.charles.games.piecefruits.service.AttributeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/attribute")
@RequiredArgsConstructor
@Slf4j
public class AttributeController {

    private final AttributeService service;

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ResponseEntity<AttributeCostDTO> get() {
        log.info("REST to get attributes belly cost");
        return ResponseEntity.ok(service.getBellyCost());
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping
    public ResponseEntity<ResponseDTO> update(@RequestBody @Valid UpdateAttributeDTO dto) {
        log.info("REST request to update account character attribute: {}", dto);
        return ResponseEntity.ok(service.update(dto));
    }
}
