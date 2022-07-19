package com.charles.games.piecefruits.model.dto;

import com.charles.games.piecefruits.model.enums.GenderEnum;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class CreateAccountDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Email(message = "email should be valid")
    @NotNull
    @Size(max = 50)
    private String email;

    @NotNull
    @NotBlank
    @Size(min = 6, max = 50)
    private String password;

    @NotNull
    private GenderEnum gender;

    @NotNull
    private LocalDate birthDate;
}
