package ru.itis.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpResultDTO {

    private boolean signUpSuccessful;
    private Map<String, String> invalidFields;
    private String reason;
    private UserDTO createdUser;
}
