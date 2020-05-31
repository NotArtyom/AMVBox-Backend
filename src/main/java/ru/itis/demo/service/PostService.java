package ru.itis.demo.service;

import ru.itis.demo.dto.PostCreationDTO;
import ru.itis.demo.dto.PostViewDTO;

import java.util.List;
import java.util.Optional;

public interface PostService {

    PostViewDTO createPost(PostCreationDTO dto);

    List<PostViewDTO> getAll();

    List<PostViewDTO> getAllByUserId(Long id);

    Optional<PostViewDTO> getById(Long id);
}
