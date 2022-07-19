package com.charles.games.piecefruits.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class ListAvatarDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String image;
}
