package com.charles.games.piecefruits.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class ListAccountCharacterDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String image;
    private Long level;
    private ListAttributeDTO attribute;
    private ListCharacterDTO character;
}
