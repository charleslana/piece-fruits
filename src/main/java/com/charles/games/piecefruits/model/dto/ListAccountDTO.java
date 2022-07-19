package com.charles.games.piecefruits.model.dto;

import com.charles.games.piecefruits.model.enums.GenderEnum;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class ListAccountDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String email;
    private GenderEnum gender;
    private LocalDate birthDate;
}
