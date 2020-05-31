package ru.itis.demo.service;

import ru.itis.demo.dto.SignInDTO;
import ru.itis.demo.dto.TokenDTO;

public interface SignInService {
    TokenDTO signIn(SignInDTO signInData);
}

