package com.charles.games.piecefruits.repository;

import com.charles.games.piecefruits.model.entity.AccountCharacter;
import com.charles.games.piecefruits.model.enums.BannedEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountCharacterRepository extends JpaRepository<AccountCharacter, Long> {

    @Query("select (count(a) > 0) from AccountCharacter a where a.name = ?1")
    Boolean existsByName(String name);

    @Query("select a from AccountCharacter a where a.account.id = ?1")
    List<AccountCharacter> findAllByAccountId(Long id);

    @Query("select a from AccountCharacter a where a.account.id = ?1 and a.id = ?2 and a.banned = ?3")
    Optional<AccountCharacter> findByAccountIdAndIdAndBanned(Long accountId, Long id, BannedEnum banned);
}
