package ru.itis.demo.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.itis.demo.dto.SignUpDTO;
import ru.itis.demo.dto.UserDTO;
import ru.itis.demo.models.Role;
import ru.itis.demo.models.State;
import ru.itis.demo.models.User;
import ru.itis.demo.repositories.UsersRepository;
import ru.itis.demo.security.JWT.details.UserDetailsImpl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutorService;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDTO profile(UserDetailsImpl userDetails) {
        System.out.println(userDetails.getUsername());
        Optional<User> userOptional = usersRepository.findUserById(userDetails.getUserId());
        if (userOptional.isPresent()) {
            return UserDTO.from(userOptional.get());
        }else throw new AccessDeniedException("User not found");
    }
}
