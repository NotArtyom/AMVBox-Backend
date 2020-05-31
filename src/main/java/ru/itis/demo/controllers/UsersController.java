package ru.itis.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.itis.demo.dto.UserDTO;
import ru.itis.demo.service.UsersService;

import java.util.List;

@RestController
@CrossOrigin
public class UsersController {

    @Autowired
    private UsersService usersService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getUsers() {
        return ResponseEntity.ok(usersService.getAllUsers());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/users/{user-id}")
    public ResponseEntity<?> deleteUser(@PathVariable("user-id") Long userId) {
        usersService.deleteUser(userId);
        return ResponseEntity.accepted().build();
    }
}
