package ru.itis.demo.service;

import ru.itis.demo.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UsersService {
    List<UserDTO> getAllUsers();

    void deleteUser(Long userId);

    Optional<UserDTO> getUserById(Long id);
}
