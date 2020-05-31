package ru.itis.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.demo.dto.UserDTO;
import ru.itis.demo.security.JWT.details.UserDetailsImpl;
import ru.itis.demo.service.ProfileService;

@RestController
@CrossOrigin
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/self")
    public ResponseEntity<UserDTO> getSelf() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getDetails();
        return ResponseEntity.ok(profileService.profile(userDetails));
    }
}
