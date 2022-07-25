package com.charles.games.piecefruits.repository;

import com.charles.games.piecefruits.model.entity.Character;
import com.charles.games.piecefruits.model.enums.InitialEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {

    @Query("select c from Character c where c.initial = ?1")
    List<Character> findAllByInitial(InitialEnum initial);

    @Query("select c from Character c where c.id = ?1 and c.initial = ?2")
    Optional<Character> findByIdAndInitial(Long id, InitialEnum initial);
}
