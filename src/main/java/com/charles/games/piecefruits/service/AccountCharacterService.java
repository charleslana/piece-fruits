package com.charles.games.piecefruits.service;

import com.charles.games.piecefruits.config.exceptions.BusinessRuleException;
import com.charles.games.piecefruits.config.security.SecurityUtils;
import com.charles.games.piecefruits.mapper.AccountCharacterMapper;
import com.charles.games.piecefruits.model.dto.CreateAccountCharacterDTO;
import com.charles.games.piecefruits.model.dto.ListAccountCharacterDTO;
import com.charles.games.piecefruits.model.dto.ResponseDTO;
import com.charles.games.piecefruits.model.dto.UpdateAccountCharacterDTO;
import com.charles.games.piecefruits.model.dto.UpdateAccountCharacterNameDTO;
import com.charles.games.piecefruits.model.entity.AccountCharacter;
import com.charles.games.piecefruits.model.entity.Attribute;
import com.charles.games.piecefruits.model.entity.Character;
import com.charles.games.piecefruits.model.enums.BannedEnum;
import com.charles.games.piecefruits.model.enums.InitialEnum;
import com.charles.games.piecefruits.repository.AccountCharacterRepository;
import com.charles.games.piecefruits.service.interfaces.BasicService;
import com.charles.games.piecefruits.service.utils.LocaleUtils;
import com.charles.games.piecefruits.service.utils.MessageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountCharacterService implements BasicService {

    private final AccountService accountService;
    private final CharacterService characterService;
    private final AccountCharacterMapper mapper;
    private final MessageSource ms;
    private final AccountCharacterRepository repository;

    @Transactional
    public ResponseDTO create(CreateAccountCharacterDTO dto) {
        validateExistsName(dto);
        validateCountExceeded();
        Character character = characterService.existsCharacterIdAndInitial(dto.getCharacterId(), InitialEnum.YES);
        AccountCharacter accountCharacter = mapper.toEntity(dto);
        accountCharacter.setImage("1");
        accountCharacter.setLevel(1L);
        accountCharacter.setBanned(BannedEnum.NO);
        accountCharacter.setAttribute(getAttribute());
        accountCharacter.setAccount(accountService.getAuthAccount());
        accountCharacter.setCharacter(character);
        repository.save(accountCharacter);
        return getSuccess("account.character.created");
    }

    @Transactional
    public ResponseDTO delete(Long id) {
        AccountCharacter accountCharacter = getCharacterId(id);
        repository.delete(accountCharacter);
        return getSuccess("account.character.delete");
    }

    public ListAccountCharacterDTO get() {
        return repository.findById(getAuthCharacter().getId()).map(mapper::toListDto).orElseThrow(() -> getException("account.character.not.found"));
    }

    public List<ListAccountCharacterDTO> getAll() {
        return repository.findAll().stream().map(mapper::toListDto).toList();
    }

    public List<ListAccountCharacterDTO> getAllByAccountId() {
        return repository.findAllByAccountId(accountService.getAuthAccount().getId()).stream().map(mapper::toListDto).toList();
    }

    public AccountCharacter getAuthCharacter() {
        existsCharacterId();
        return getCharacterId(SecurityUtils.getUserDetails().getCharacterId());
    }

    @Override
    public BusinessRuleException getException(String message) {
        return new BusinessRuleException(MessageUtils.ACCOUNT_CHARACTER_EXCEPTION, message);
    }

    @Override
    public ResponseDTO getSuccess(String message) {
        return new ResponseDTO(MessageUtils.ACCOUNT_CHARACTER_SUCCESS, message, null, LocaleUtils.currentLocale(), ms);
    }

    public void logoutCharacter() {
        existsCharacterId();
        SecurityUtils.removeCharacterId();
    }

    public void selectCharacter(Long id) {
        SecurityUtils.setCharacterId(getCharacterId(id).getId());
    }

    @Transactional
    public ResponseDTO update(UpdateAccountCharacterDTO dto) {
        AccountCharacter authAccountCharacter = getAuthCharacter();
        // TODO: 18/07/2022 atualizar personagem da conta
        return getSuccess("account.character.updated");
    }

    @Transactional
    public ResponseDTO updateName(UpdateAccountCharacterNameDTO dto) {
        validateExistsName(dto);
        AccountCharacter authAccountCharacter = getAuthCharacter();
        authAccountCharacter.setName(dto.getName());
        return getSuccess("account.character.updated");
    }

    private void existsCharacterId() {
        if (Boolean.FALSE.equals(SecurityUtils.existsCharacterId())) {
            throw getException("account.character.not.found");
        }
    }

    private Attribute getAttribute() {
        Attribute attribute = new Attribute();
        attribute.setPunch(1L);
        attribute.setDefense(1L);
        attribute.setSword(1L);
        attribute.setWeapon(1L);
        attribute.setFruit(1L);
        return attribute;
    }

    private AccountCharacter getCharacterId(Long id) {
        return repository.findByAccountIdAndIdAndBanned(accountService.getAuthAccount().getId(), id, BannedEnum.NO).orElseThrow(() -> getException("account.character.not.found"));
    }

    private void validateCountExceeded() {
        if (repository.countByAccountId(accountService.getAuthAccount().getId()) >= 4) {
            throw getException("account.character.count.exceeded");
        }
    }

    private void validateExistsName(UpdateAccountCharacterNameDTO dto) {
        boolean existsByName = repository.existsByName(dto.getName());
        if (existsByName && !Objects.equals(getAuthCharacter().getName(), dto.getName())) {
            throw getException("account.character.exists.name");
        }
    }

    private void validateExistsName(CreateAccountCharacterDTO dto) {
        boolean existsByName = repository.existsByName(dto.getName());
        if (existsByName) {
            throw getException("account.character.exists.name");
        }
    }
}
