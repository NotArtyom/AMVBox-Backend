package ru.itis.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.itis.demo.dto.SignUpForm;
import ru.itis.demo.dto.SignUpResultDTO;
import ru.itis.demo.models.Role;
import ru.itis.demo.models.State;
import ru.itis.demo.models.User;
import ru.itis.demo.repositories.UsersRepository;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.ExecutorService;

@Slf4j
@Component
public class SignUpServiceImpl implements SignUpService {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ExecutorService threadPool;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsersService usersService;

    @Value("${jwt.secret}")
    private String secret;

    @Override
    public SignUpResultDTO signUp(SignUpForm form) {
        String rawPassword = form.getPassword();
        String hashPassword = passwordEncoder.encode(rawPassword);

        User user = User.builder()
                .email(form.getEmail())
                .hashPassword(hashPassword)
                .name(form.getName())
                .surname(form.getSurname())
                .createdAt(LocalDateTime.now())
                .state(State.NOT_CONFIRMED)
                .confirmCode(UUID.randomUUID().toString())
                .role(Role.USER)
                .build();
        usersRepository.save(user);

        threadPool.submit(() -> {
            emailService.sendMail("Регистрация", user.getConfirmCode(), user.getEmail());
        });

        return SignUpResultDTO.builder()
                .signUpSuccessful(true)
                .createdUser(usersService.getUserById(user.getId()).orElseThrow(IllegalArgumentException::new))
                .build();
    }


}
