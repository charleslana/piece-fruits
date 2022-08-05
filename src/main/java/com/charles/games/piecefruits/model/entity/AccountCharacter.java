package com.charles.games.piecefruits.model.entity;

import com.charles.games.piecefruits.model.enums.BannedEnum;
import com.charles.games.piecefruits.model.enums.FactionEnum;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "account_character")
public class AccountCharacter implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "account_character_sequence", sequenceName = "account_character_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_character_sequence")
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 20, unique = true, nullable = false)
    private String name;

    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "banned", nullable = false)
    @Enumerated(EnumType.STRING)
    private BannedEnum banned;

    @Column(name = "level", nullable = false)
    private Long level;

    @Column(name = "faction", nullable = false)
    @Enumerated(EnumType.STRING)
    private FactionEnum faction;

    @Column(name = "bounty", nullable = false)
    private Long bounty;

    @Column(name = "fame", nullable = false)
    private Long fame;

    private Long power = 0L;

    @Column(name = "biography", length = 200)
    private String biography;

    @Column(name = "belly", nullable = false)
    private Long belly;

    @Column(name = "gold", nullable = false)
    private Long gold;

    @Column(name = "day_score", nullable = false)
    private Long dayScore;

    @Column(name = "immunity")
    private LocalDateTime immunity;

    @Column(name = "experience", nullable = false)
    private Long experience;

    @Column(name = "honor_win", nullable = false)
    private Long honorWin;

    @Column(name = "total_battle", nullable = false)
    private Long totalBattle;

    @Column(name = "win", nullable = false)
    private Long win;

    @Column(name = "defeat", nullable = false)
    private Long defeat;

    @Column(name = "draw", nullable = false)
    private Long draw;

    @Column(name = "damage_hit", nullable = false)
    private Long damageHit;

    @Column(name = "damage_taken", nullable = false)
    private Long damageTaken;

    @Column(name = "belly_won", nullable = false)
    private Long bellyWon;

    @Column(name = "belly_lost", nullable = false)
    private Long bellyLost;

    @Column(name = "score", nullable = false)
    private Long score;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "character_id", nullable = false)
    private Character character;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "attribute_id", nullable = false)
    private Attribute attribute;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "crew_member_id")
    private CrewMember crewMember;
}
