package com.charles.games.piecefruits.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class ListCharacterDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String name;
    private List<ListAvatarDTO> avatars;
}
