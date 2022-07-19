package com.charles.games.piecefruits.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class UpdateAccountCharacterDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    // TODO: 18/07/2022 Adicionar campo para atualizar o personagem da conta
}
