package ru.itis.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.demo.dto.RateDTO;
import ru.itis.demo.dto.RateResultDTO;
import ru.itis.demo.service.RateService;

@RestController
@CrossOrigin
public class RateController {

    @Autowired
    private RateService rateService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/rate")
    public ResponseEntity<RateResultDTO> ratePost(@RequestBody RateDTO dto) {
        return ResponseEntity.ok(rateService.ratePost(dto));
    }
}
