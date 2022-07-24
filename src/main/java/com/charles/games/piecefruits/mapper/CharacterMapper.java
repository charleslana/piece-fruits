package com.charles.games.piecefruits.mapper;

import com.charles.games.piecefruits.model.dto.ListCharacterDTO;
import com.charles.games.piecefruits.model.entity.Character;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CharacterMapper {

    ListCharacterDTO toListDto(Character entity);

    Character toEntity(ListCharacterDTO dto);
}
