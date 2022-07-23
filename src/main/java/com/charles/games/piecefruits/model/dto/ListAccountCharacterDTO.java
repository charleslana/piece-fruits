package com.charles.games.piecefruits.model.dto;

import com.charles.games.piecefruits.model.enums.FactionEnum;
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
    private FactionEnum faction;
    private ListAttributeDTO attribute;
    private ListCharacterDTO character;
}
