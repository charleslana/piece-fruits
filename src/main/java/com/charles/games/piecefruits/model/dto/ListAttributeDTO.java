package com.charles.games.piecefruits.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class ListAttributeDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long punch;
    private Long defense;
    private Long sword;
    private Long weapon;
    private Long fruit;
}
