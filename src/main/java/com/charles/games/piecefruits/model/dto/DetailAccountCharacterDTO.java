package com.charles.games.piecefruits.model.dto;

import com.charles.games.piecefruits.model.enums.FactionEnum;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class DetailAccountCharacterDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String image;
    private Long level;
    private FactionEnum faction;
    private Long bounty;
    private Long fame;
    private Long power;
    private String biography;
    private Long belly;
    private Long gold;
    private Long dayScore;
    private LocalDateTime immunity;
    private Long experience;
    private Long honorWin;
    private Long totalBattle;
    private Long win;
    private Long defeat;
    private Long draw;
    private Long damageHit;
    private Long damageTaken;
    private Long bellyWon;
    private Long bellyLost;
    private Long score;
    private ListAttributeDTO attribute;
    private ListCharacterDTO character;
    private ListCrewMemberDTO crewMember;
}
