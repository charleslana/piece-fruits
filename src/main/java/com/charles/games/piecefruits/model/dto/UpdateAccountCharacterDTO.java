package com.charles.games.piecefruits.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class UpdateAccountCharacterDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(min = 10, max = 200)
    private String biography;
}
