package ru.itis.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.itis.demo.dto.PostCreationDTO;
import ru.itis.demo.dto.PostViewDTO;
import ru.itis.demo.service.PostService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public ResponseEntity<PostViewDTO> createPost(@RequestBody PostCreationDTO dto) {
        return ResponseEntity.ok(postService.createPost(dto));
    }

    @GetMapping("/")
    public ResponseEntity<List<PostViewDTO>> getAllPosts() {
        System.out.println("Executed");
        return ResponseEntity.ok(postService.getAll());
    }

    @GetMapping("/{user-id}")
    public ResponseEntity<List<PostViewDTO>> getAllPostsByUserId(@PathVariable(name = "user-id") Long userId) {
        return ResponseEntity.ok(postService.getAllByUserId(userId));
    }
}
