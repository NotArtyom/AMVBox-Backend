package ru.itis.demo.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.itis.demo.dto.PostDTO;
import ru.itis.demo.dto.TokenDTO;
import ru.itis.demo.models.State;
import ru.itis.demo.models.User;
import ru.itis.demo.repositories.UsersRepository;

import java.util.Optional;

@Service
public class ConfirmServiceImpl implements ConfirmService {

    @Autowired
    private UsersRepository usersRepository;

    @Value("${jwt.secret}")
    private String secret;

    @Override
    public TokenDTO confirm(PostDTO confirmCode) {
        Optional<User> userOptional = usersRepository.findUserByConfirmCode(confirmCode.getCode());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setState(State.CONFIRMED);
            usersRepository.save(user);

                String token = Jwts.builder()
                        .setSubject(user.getId().toString()) // id пользователя
                        .claim("name", user.getName())// имя
                        .claim("role", user.getRole().name()) // роль
                        .signWith(SignatureAlgorithm.HS256, secret) // подписываем его с нашим secret
                        .compact(); // преобразовали в строку
                return new TokenDTO(token);
        }
        return null;
    }
}