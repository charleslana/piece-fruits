package com.charles.games.piecefruits.service;

import com.charles.games.piecefruits.config.exceptions.BusinessRuleException;
import com.charles.games.piecefruits.model.dto.AttributeCostDTO;
import com.charles.games.piecefruits.model.dto.ListAttributeDTO;
import com.charles.games.piecefruits.model.dto.ResponseDTO;
import com.charles.games.piecefruits.model.dto.UpdateAttributeDTO;
import com.charles.games.piecefruits.model.entity.AccountCharacter;
import com.charles.games.piecefruits.model.entity.Attribute;
import com.charles.games.piecefruits.model.enums.AttributeEnum;
import com.charles.games.piecefruits.service.interfaces.BasicService;
import com.charles.games.piecefruits.service.utils.LocaleUtils;
import com.charles.games.piecefruits.service.utils.MessageUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class AttributeService implements BasicService {

    private final AccountCharacterService accountCharacterService;
    private final MessageSource ms;

    public AttributeCostDTO getBellyCost() {
        AccountCharacter accountCharacter = accountCharacterService.getAuthCharacter();
        AttributeCostDTO dto = new AttributeCostDTO();
        dto.setDefenseBellyCost(calculateAttributeBelly(accountCharacter.getAttribute().getDefense()));
        dto.setPunchBellyCost(calculateAttributeBelly(accountCharacter.getAttribute().getPunch()));
        dto.setFruitBellyCost(calculateAttributeBelly(accountCharacter.getAttribute().getFruit()));
        dto.setSwordBellyCost(calculateAttributeBelly(accountCharacter.getAttribute().getSword()));
        dto.setWeaponBellyCost(calculateAttributeBelly(accountCharacter.getAttribute().getWeapon()));
        ListAttributeDTO listAttributeDTO = getListAttributeDTO(accountCharacter);
        dto.setAttribute(listAttributeDTO);
        return dto;
    }

    @Override
    public BusinessRuleException getException(String message) {
        return new BusinessRuleException(MessageUtils.ATTRIBUTE_EXCEPTION, message);
    }

    @Override
    public ResponseDTO getSuccess(String message) {
        return new ResponseDTO(MessageUtils.ATTRIBUTE_SUCCESS, message, null, LocaleUtils.currentLocale(), ms);
    }

    @Transactional
    public ResponseDTO update(UpdateAttributeDTO dto) {
        AccountCharacter accountCharacter = accountCharacterService.getAuthCharacter();
        Attribute getAttribute = accountCharacter.getAttribute();
        Long calculateBelly = getCalculateBelly(dto.getAttribute(), accountCharacter, getAttribute);
        accountCharacter.setBelly(accountCharacter.getBelly() - calculateBelly);
        accountCharacter.setAttribute(getAttribute);
        accountCharacterService.updateAttribute(accountCharacter);
        return getSuccess("attribute.updated");
    }

    private Long calculateAttributeBelly(Long attribute) {
        return attribute * 50;
    }

    private Long getCalculateBelly(AttributeEnum attribute, AccountCharacter accountCharacter, Attribute getAttribute) {
        Long calculateBelly = 0L;
        switch (attribute) {
            case FRUIT -> calculateBelly = getFruitBelly(accountCharacter, getAttribute);
            case DEFENSE -> calculateBelly = getDefenseBelly(accountCharacter, getAttribute);
            case PUNCH -> calculateBelly = getPunchBelly(accountCharacter, getAttribute);
            case SWORD -> calculateBelly = getSwordBelly(accountCharacter, getAttribute);
            case WEAPON -> calculateBelly = getWeaponBelly(accountCharacter, getAttribute);
        }
        return calculateBelly;
    }

    private Long getDefenseBelly(AccountCharacter accountCharacter, Attribute getAttribute) {
        Long calculateBelly = calculateAttributeBelly(getAttribute.getDefense());
        validateBellyNotEnough(accountCharacter, calculateBelly);
        getAttribute.setDefense(getAttribute.getDefense() + 1);
        return calculateBelly;
    }

    private Long getFruitBelly(AccountCharacter accountCharacter, Attribute getAttribute) {
        Long calculateBelly = calculateAttributeBelly(getAttribute.getFruit());
        validateBellyNotEnough(accountCharacter, calculateBelly);
        getAttribute.setFruit(getAttribute.getFruit() + 1);
        return calculateBelly;
    }

    private Long getPunchBelly(AccountCharacter accountCharacter, Attribute getAttribute) {
        Long calculateBelly = calculateAttributeBelly(getAttribute.getPunch());
        validateBellyNotEnough(accountCharacter, calculateBelly);
        getAttribute.setPunch(getAttribute.getPunch() + 1);
        return calculateBelly;
    }

    private Long getSwordBelly(AccountCharacter accountCharacter, Attribute getAttribute) {
        Long calculateBelly = calculateAttributeBelly(getAttribute.getSword());
        validateBellyNotEnough(accountCharacter, calculateBelly);
        getAttribute.setSword(getAttribute.getSword() + 1);
        return calculateBelly;
    }

    private Long getWeaponBelly(AccountCharacter accountCharacter, Attribute getAttribute) {
        Long calculateBelly = calculateAttributeBelly(getAttribute.getWeapon());
        validateBellyNotEnough(accountCharacter, calculateBelly);
        getAttribute.setWeapon(getAttribute.getWeapon() + 1);
        return calculateBelly;
    }

    private void validateBellyNotEnough(AccountCharacter accountCharacter, Long calculateBelly) {
        if (accountCharacter.getBelly() < calculateBelly) {
            throw getException("attribute.not.enough.belly");
        }
    }

    private static ListAttributeDTO getListAttributeDTO(AccountCharacter accountCharacter) {
        ListAttributeDTO listAttributeDTO = new ListAttributeDTO();
        listAttributeDTO.setDefense(accountCharacter.getAttribute().getDefense());
        listAttributeDTO.setFruit(accountCharacter.getAttribute().getFruit());
        listAttributeDTO.setWeapon(accountCharacter.getAttribute().getWeapon());
        listAttributeDTO.setSword(accountCharacter.getAttribute().getSword());
        listAttributeDTO.setPunch(accountCharacter.getAttribute().getPunch());
        return listAttributeDTO;
    }
}
