package com.charles.games.piecefruits.config;

import com.charles.games.piecefruits.model.entity.Account;
import com.charles.games.piecefruits.model.entity.AccountCharacter;
import com.charles.games.piecefruits.model.entity.Attribute;
import com.charles.games.piecefruits.model.entity.Avatar;
import com.charles.games.piecefruits.model.entity.Character;
import com.charles.games.piecefruits.model.enums.BannedEnum;
import com.charles.games.piecefruits.model.enums.FactionEnum;
import com.charles.games.piecefruits.model.enums.GenderEnum;
import com.charles.games.piecefruits.model.enums.RoleEnum;
import com.charles.games.piecefruits.model.enums.StatusEnum;
import com.charles.games.piecefruits.repository.AccountCharacterRepository;
import com.charles.games.piecefruits.repository.AccountRepository;
import com.charles.games.piecefruits.repository.AvatarRepository;
import com.charles.games.piecefruits.repository.CharacterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
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

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            deleteAll();
            createAvatar();
            createCharacter();
            createAccount();
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
        accountRepository.save(account1);

        Account account2 = new Account();
        account2.setEmail("example2@example.com");
        account2.setPassword(encoder.encode("123456789"));
        account2.setGender(GenderEnum.OTHER);
        account2.setBirthDate(LocalDate.now());
        account2.setStatus(StatusEnum.ACTIVE);
        account2.setRole(RoleEnum.USER);
        accountRepository.save(account2);

        createAccountCharacter(account1);
    }

    private void createAccountCharacter(Account account) {
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
        accountCharacter.setName("Name");
        Optional<Character> character = characterRepository.findAll().stream().findFirst();
        accountCharacter.setCharacter(character.stream().findFirst().get());
        accountCharacterRepository.save(accountCharacter);
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
        for (String character : characters) {
            Character c = new Character();
            c.setName(character);
            List<Avatar> avatars = avatarRepository.findAll();
            c.setAvatars(avatars);
            cs.add(c);
        }
        characterRepository.saveAll(cs);
    }

    private void deleteAll() {
        accountCharacterRepository.deleteAll();
        accountRepository.deleteAll();
        characterRepository.deleteAll();
        avatarRepository.deleteAll();
    }
}
