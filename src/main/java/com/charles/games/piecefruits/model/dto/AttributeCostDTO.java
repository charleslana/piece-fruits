package com.charles.games.piecefruits.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class AttributeCostDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long punchBellyCost;
    private Long defenseBellyCost;
    private Long swordBellyCost;
    private Long weaponBellyCost;
    private Long fruitBellyCost;
    private ListAttributeDTO attribute;
}
