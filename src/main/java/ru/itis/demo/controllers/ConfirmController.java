package ru.itis.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.demo.dto.PostDTO;
import ru.itis.demo.dto.TokenDTO;
import ru.itis.demo.service.ConfirmService;

@RestController
@CrossOrigin
public class ConfirmController {

    @Autowired
    private ConfirmService confirmService;

        @PostMapping("/confirm")
        public ResponseEntity<TokenDTO> confirm(@RequestBody PostDTO postDTO) {
            System.out.println(postDTO);
            System.out.println(confirmService.confirm(postDTO));
            return ResponseEntity.ok(confirmService.confirm(postDTO));
        }
    }

