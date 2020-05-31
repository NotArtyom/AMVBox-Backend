package ru.itis.demo.security.JWT.provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.itis.demo.security.JWT.authentication.JwtAuthentication;
import ru.itis.demo.security.JWT.details.UserDetailsImpl;

// проверить аутентификацию пользователя
@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {
    // секретный ключ, которым мы подписываем токен
    @Value("${jwt.secret}")
    private String secret;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = authentication.getName();

        Claims claims;
        try {
            // выполняю парсинг токена со своим секретным ключом
            claims =  Jwts.parser().setSigningKey(secret).parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            throw new AuthenticationCredentialsNotFoundException("Bad token");
        }
        // создаем UserDetails
        UserDetails userDetails = UserDetailsImpl.builder()
                .userId(Long.parseLong(claims.get("sub", String.class)))
                .role(claims.get("role", String.class))
                .name(claims.get("name", String.class))
                .build();
        // аутентификация прошла успешно
        authentication.setAuthenticated(true);
        // положили в эту аутентификацию пользователя
        ((JwtAuthentication)authentication).setUserDetails(userDetails);
        return authentication;
    }

    // проверяет, подходит ли текущий провайдер для этой аутентификации
    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthentication.class.equals(authentication);
    }
}
