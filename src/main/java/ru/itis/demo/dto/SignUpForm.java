package ru.itis.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignUpForm {

    @NotNull
    @Email
    private String email;

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    @Size(min = 6, max = 20)
    private String password;
}
