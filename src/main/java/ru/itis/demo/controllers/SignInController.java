package ru.itis.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.demo.dto.SignInDTO;
import ru.itis.demo.dto.TokenDTO;
import ru.itis.demo.service.SignInService;

@RestController
@CrossOrigin
public class SignInController {

    @Autowired
    private SignInService signInService;

    @PostMapping("/signIn")
    public ResponseEntity<TokenDTO> signIn(@RequestBody SignInDTO signInData) {
        return ResponseEntity.ok(signInService.signIn(signInData));
    }
}

