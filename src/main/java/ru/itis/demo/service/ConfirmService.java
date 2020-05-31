package ru.itis.demo.service;

import ru.itis.demo.dto.PostDTO;
import ru.itis.demo.dto.TokenDTO;

public interface ConfirmService {
    TokenDTO confirm(PostDTO confirmCode);
}