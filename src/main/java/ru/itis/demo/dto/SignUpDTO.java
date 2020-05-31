package ru.itis.demo.dto;

import lombok.Data;

@Data
public class SignUpDTO {
    private String email;
    private String name;
    private String surname;
    private String password;
}
