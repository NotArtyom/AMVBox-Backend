package ru.itis.demo.service;

import ru.itis.demo.dto.UserDTO;
import ru.itis.demo.security.JWT.details.UserDetailsImpl;

public interface  ProfileService {
    UserDTO profile(UserDetailsImpl userDetails);
}
