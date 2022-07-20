package com.charles.games.piecefruits.mapper;

import com.charles.games.piecefruits.model.dto.CreateAccountDTO;
import com.charles.games.piecefruits.model.dto.ListAccountDTO;
import com.charles.games.piecefruits.model.entity.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    CreateAccountDTO toCreateDto(Account entity);

    ListAccountDTO toListDto(Account entity);

    Account toEntity(CreateAccountDTO dto);

    Account toEntity(ListAccountDTO dto);
}
