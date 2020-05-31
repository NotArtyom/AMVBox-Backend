package ru.itis.demo.service;

import ru.itis.demo.dto.SignUpForm;
import ru.itis.demo.dto.SignUpResultDTO;

public interface SignUpService {
    SignUpResultDTO signUp(SignUpForm form);
}
