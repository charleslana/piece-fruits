package com.charles.games.piecefruits.model.dto;

import com.charles.games.piecefruits.model.enums.AttributeEnum;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class UpdateAttributeDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull
    private AttributeEnum attribute;
}
