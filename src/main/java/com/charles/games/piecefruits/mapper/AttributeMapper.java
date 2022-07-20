package com.charles.games.piecefruits.mapper;

import com.charles.games.piecefruits.model.dto.ListAttributeDTO;
import com.charles.games.piecefruits.model.entity.Attribute;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AttributeMapper {

    ListAttributeDTO toListDto(Attribute entity);

    Attribute toEntity(ListAttributeDTO dto);
}
