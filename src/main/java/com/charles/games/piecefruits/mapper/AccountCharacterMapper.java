package com.charles.games.piecefruits.mapper;

import com.charles.games.piecefruits.model.dto.CreateAccountCharacterDTO;
import com.charles.games.piecefruits.model.dto.ListAccountCharacterDTO;
import com.charles.games.piecefruits.model.entity.AccountCharacter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountCharacterMapper {

    CreateAccountCharacterDTO toCreateDto(AccountCharacter entity);

    ListAccountCharacterDTO toListDto(AccountCharacter entity);

    AccountCharacter toEntity(CreateAccountCharacterDTO dto);

    AccountCharacter toEntity(ListAccountCharacterDTO dto);
}
