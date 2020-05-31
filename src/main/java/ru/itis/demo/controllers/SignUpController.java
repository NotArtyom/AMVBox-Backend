package ru.itis.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import ru.itis.demo.dto.SignUpForm;
import ru.itis.demo.dto.SignUpResultDTO;
import ru.itis.demo.service.SignUpService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @PostMapping("/signUp")
    public ResponseEntity<SignUpResultDTO> signIn(@RequestBody @Valid SignUpForm signUpData) {
        return ResponseEntity.ok(signUpService.signUp(signUpData));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public SignUpResultDTO handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return SignUpResultDTO.builder()
                .signUpSuccessful(false)
                .reason("Some fields have not passed the validation")
                .invalidFields(errors)
                .build();
    }

}
