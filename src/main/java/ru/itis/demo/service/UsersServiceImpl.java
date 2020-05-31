package ru.itis.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.demo.dto.UserDTO;
import ru.itis.demo.repositories.UsersRepository;

import java.util.List;
import java.util.Optional;

@Component
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public List<UserDTO> getAllUsers() {
        return UserDTO.from(usersRepository.findAll());
    }

    @Override
    public void deleteUser(Long userId) {
        usersRepository.deleteById(userId);
    }

    @Override
    public Optional<UserDTO> getUserById(Long id) {
        return usersRepository.findUserById(id).map(user -> UserDTO.builder()
                .email(user.getEmail())
                .dislikes(user.getDislikedPosts())
                .likes(user.getLikedPosts())
                .fileInfo(user.getAvatar())
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .build());
    }
}
