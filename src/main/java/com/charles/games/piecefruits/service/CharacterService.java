package com.charles.games.piecefruits.service;

import com.charles.games.piecefruits.config.exceptions.BusinessRuleException;
import com.charles.games.piecefruits.model.dto.ResponseDTO;
import com.charles.games.piecefruits.model.entity.Character;
import com.charles.games.piecefruits.repository.CharacterRepository;
import com.charles.games.piecefruits.service.interfaces.BasicService;
import com.charles.games.piecefruits.service.utils.MessageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterService implements BasicService {

    private final CharacterRepository repository;

    public Character existsCharacterId(Long id) {
        return repository.findById(id).orElseThrow(() -> getException("character.not.found"));
    }

    @Override
    public BusinessRuleException getException(String message) {
        return new BusinessRuleException(MessageUtils.CHARACTER_EXCEPTION, message);
    }

    @Override
    public ResponseDTO getSuccess(String message) {
        return null;
    }
}
