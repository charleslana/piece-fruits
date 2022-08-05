package com.charles.games.piecefruits.model.dto;

import com.charles.games.piecefruits.model.enums.CrewRoleEnum;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class ListCrewMemberDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private CrewRoleEnum crewRole;
    private Long score;
    private ListCrewDTO crew;
}
