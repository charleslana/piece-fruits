package com.charles.games.piecefruits.config;

import com.charles.games.piecefruits.model.entity.Account;
import com.charles.games.piecefruits.model.entity.AccountCharacter;
import com.charles.games.piecefruits.model.entity.Attribute;
import com.charles.games.piecefruits.model.entity.Avatar;
import com.charles.games.piecefruits.model.entity.Character;
import com.charles.games.piecefruits.model.entity.Crew;
import com.charles.games.piecefruits.model.entity.CrewMember;
import com.charles.games.piecefruits.model.enums.BannedEnum;
import com.charles.games.piecefruits.model.enums.CrewRoleEnum;
import com.charles.games.piecefruits.model.enums.FactionEnum;
import com.charles.games.piecefruits.model.enums.GenderEnum;
import com.charles.games.piecefruits.model.enums.InitialEnum;
import com.charles.games.piecefruits.model.enums.RoleEnum;
import com.charles.games.piecefruits.model.enums.StatusEnum;
import com.charles.games.piecefruits.repository.AccountCharacterRepository;
import com.charles.games.piecefruits.repository.AccountRepository;
import com.charles.games.piecefruits.repository.AvatarRepository;
import com.charles.games.piecefruits.repository.CharacterRepository;
import com.charles.games.piecefruits.repository.CrewRepository;
import com.charles.games.piecefruits.service.utils.FunctionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class PieceFruitsConfig {

    private final BCryptPasswordEncoder encoder;
    private final AccountRepository accountRepository;
    private final AccountCharacterRepository accountCharacterRepository;
    private final CharacterRepository characterRepository;
    private final AvatarRepository avatarRepository;
    private final CrewRepository crewRepository;

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
//            deleteAll();
//            createAvatar();
//            createCharacter();
//            createCrew();
//            createAccount();
        };
    }

    private void createAccount() {
        Account account1 = new Account();
        account1.setEmail("example@example.com");
        account1.setPassword(encoder.encode("123456"));
        account1.setGender(GenderEnum.MALE);
        account1.setBirthDate(LocalDate.now());
        account1.setStatus(StatusEnum.ACTIVE);
        account1.setRole(RoleEnum.ADMIN);

        Account account2 = new Account();
        account2.setEmail("example2@example.com");
        account2.setPassword(encoder.encode("123456789"));
        account2.setGender(GenderEnum.OTHER);
        account2.setBirthDate(LocalDate.now());
        account2.setStatus(StatusEnum.ACTIVE);
        account2.setRole(RoleEnum.USER);

        accountRepository.saveAll(List.of(account1, account2));

        CrewMember crewMember = new CrewMember();
        crewMember.setCrewRole(CrewRoleEnum.LEADER);
        crewMember.setScore(0L);
        Optional<Crew> crew = crewRepository.findAll().stream().findFirst();
        crewMember.setCrew(crew.stream().findFirst().get());

        createAccountCharacter(account1, FunctionUtils.getRandomString(), crewMember);
        createAccountCharacter(account1, FunctionUtils.getRandomString(), null);
    }

    private void createAccountCharacter(Account account, String characterName, CrewMember crewMember) {
        AccountCharacter accountCharacter = new AccountCharacter();
        accountCharacter.setAccount(account);
        Attribute attribute = new Attribute();
        attribute.setFruit(1L);
        attribute.setDefense(1L);
        attribute.setPunch(1L);
        attribute.setSword(1L);
        attribute.setWeapon(1L);
        accountCharacter.setAttribute(attribute);
        accountCharacter.setBanned(BannedEnum.NO);
        accountCharacter.setFaction(FactionEnum.PIRATE);
        accountCharacter.setImage("1");
        accountCharacter.setLevel(1L);
        accountCharacter.setName(characterName);
        accountCharacter.setBounty(0L);
        accountCharacter.setFame(0L);
        accountCharacter.setBelly(5000L);
        accountCharacter.setGold(0L);
        accountCharacter.setDayScore(0L);
        accountCharacter.setImmunity(LocalDateTime.now().plusDays(1));
        accountCharacter.setExperience(0L);
        accountCharacter.setHonorWin(0L);
        accountCharacter.setTotalBattle(0L);
        accountCharacter.setWin(0L);
        accountCharacter.setDefeat(0L);
        accountCharacter.setDraw(0L);
        accountCharacter.setDamageHit(0L);
        accountCharacter.setDamageTaken(0L);
        accountCharacter.setBellyWon(0L);
        accountCharacter.setBellyLost(0L);
        accountCharacter.setScore(0L);
        accountCharacter.setCrewMember(crewMember);
        Optional<Character> character = characterRepository.findAll().stream().findFirst();
        accountCharacter.setCharacter(character.stream().findFirst().get());
        accountCharacterRepository.save(accountCharacter);
    }

    private void createCrew() {
        Crew crew = new Crew();
        crew.setName(FunctionUtils.getRandomString());
        crew.setLevel(1L);
        crew.setExperience(0L);
        crewRepository.save(crew);
    }

    private void createAvatar() {
        List<Avatar> avatars = new ArrayList<>();
        for (int i = 1; i <= 2; i++) {
            Avatar avatar = new Avatar();
            avatar.setImage(String.valueOf(i));
            avatars.add(avatar);
        }
        avatarRepository.saveAll(avatars);
    }

    private void createCharacter() {
        String[] characters = new String[]{"Monkey D. Luffy", "Roronoa Zoro", "Nami", "Usopp", "Vinsmoke Sanji", "Tony Tony Chopper", "Nico Robin", "Franky", "Brook", "Jinbe"};
        List<Character> cs = new ArrayList<>();
        List<Avatar> avatars = avatarRepository.findAll();
        for (String character : characters) {
            Character c = new Character();
            c.setName(character);
            c.setInitial(InitialEnum.YES);
            c.setAvatars(avatars);
            cs.add(c);
        }
        characterRepository.saveAll(cs);

        Character character = new Character();
        character.setName("Portgas D. Ace");
        character.setInitial(InitialEnum.NO);
        character.setAvatars(avatars);
        characterRepository.save(character);
    }

    private void deleteAll() {
        accountCharacterRepository.deleteAll();
        accountRepository.deleteAll();
        characterRepository.deleteAll();
        avatarRepository.deleteAll();
    }
}
