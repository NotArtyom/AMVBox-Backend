package ru.itis.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.itis.demo.dto.PostCreationDTO;
import ru.itis.demo.dto.PostViewDTO;
import ru.itis.demo.dto.UserDTO;
import ru.itis.demo.models.Post;
import ru.itis.demo.models.User;
import ru.itis.demo.repositories.PostRepository;
import ru.itis.demo.repositories.UsersRepository;
import ru.itis.demo.security.JWT.details.UserDetailsImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UsersService usersService;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public PostViewDTO createPost(PostCreationDTO dto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getDetails();

        User owner = usersRepository.findUserById(userDetails.getUserId()).orElseThrow(IllegalArgumentException::new);

        Post postToCreate = Post.builder()
                .description(dto.getDescription())
                .youtubeLink(dto.getYoutubeLink())
                .dislikeCount(0L)
                .likeCount(0L)
                .ownerId(owner.getId())
                .build();

        postRepository.save(postToCreate);

        return PostViewDTO.builder()
                .description(postToCreate.getDescription())
                .youtubeLink(postToCreate.getYoutubeLink())
                .dislikeCount(postToCreate.getDislikeCount())
                .likeCount(postToCreate.getLikeCount())
                .ownerId(owner.getId())
                .build();
    }

    @Override
    public List<PostViewDTO> getAll() {
        return postRepository.findAll()
                .stream()
                .map(post ->
                        PostViewDTO.builder()
                                .description(post.getDescription())
                                .youtubeLink(post.getYoutubeLink())
                                .dislikeCount(post.getDislikeCount())
                                .likeCount(post.getLikeCount())
                                .ownerId(post.getId())
                                .userDTO(usersService.getUserById(post.getOwnerId()).get())
                                .build()).collect(Collectors.toList());
    }

    @Override
    public List<PostViewDTO> getAllByUserId(Long id) {
        return postRepository.findAll()
                .stream()
                .filter(post -> post.getOwnerId().equals(id))
                .map(post -> PostViewDTO.builder()
                        .description(post.getDescription())
                        .youtubeLink(post.getYoutubeLink())
                        .dislikeCount(post.getDislikeCount())
                        .likeCount(post.getLikeCount())
                        .ownerId(post.getId())
                        .build()).collect(Collectors.toList());
    }

    @Override
    public Optional<PostViewDTO> getById(Long id) {
        Post post = postRepository.findById(id).orElse(null);
        System.out.println(post.getOwnerId());
        UserDTO userDto = usersService.getUserById(post.getOwnerId()).get();
        return Optional.of(PostViewDTO.builder()
                .description(post.getDescription())
                .youtubeLink(post.getYoutubeLink())
                .dislikeCount(post.getDislikeCount())
                .likeCount(post.getLikeCount())
                .ownerId(post.getId())
                .userDTO(userDto)
                .build());
    }
}
