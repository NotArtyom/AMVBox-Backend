package ru.itis.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.itis.demo.dto.RateDTO;
import ru.itis.demo.dto.RateResultDTO;
import ru.itis.demo.models.Post;
import ru.itis.demo.models.User;
import ru.itis.demo.repositories.PostRepository;
import ru.itis.demo.repositories.UsersRepository;
import ru.itis.demo.security.JWT.details.UserDetailsImpl;

import java.util.Collections;
import java.util.Optional;

@Service
public class RateServiceImpl implements RateService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostService postService;

    @Override
    public RateResultDTO ratePost(RateDTO dto) {

        RateResultDTO result;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getDetails();

        User user = usersRepository.findUserById(userDetails.getUserId()).orElseThrow(IllegalArgumentException::new);
        Optional<Post> postCandidate = postRepository.findById(dto.getPostId());

        Post post;

        if (postCandidate.isPresent()) {
            post = postCandidate.get();
        } else {
            return RateResultDTO.builder()
                    .success(false)
                    .message("Requested post could not be found")
                    .build();
        }

        if (dto.getLike()) {

            if (user.getDislikedPosts() != null) {

                if (user.getDislikedPosts().contains(post)) {
                    user.getDislikedPosts().remove(post);
                    post.setDislikeCount(post.getDislikeCount() - 1);
                }
            }

            if (user.getLikedPosts() == null) {
                user.setLikedPosts(Collections.singletonList(post));
                post.setLikeCount(post.getLikeCount() + 1);

                result = RateResultDTO.builder()
                        .success(true)
                        .message("Added like successfully")
                        .build();
            } else {

                if (!user.getLikedPosts().contains(post)) {
                    user.getLikedPosts().add(post);
                    post.setLikeCount(post.getLikeCount() + 1);

                    result = RateResultDTO.builder()
                            .success(true)
                            .message("Added like successfully")
                            .build();
                } else {
                    user.getLikedPosts().remove(post);
                    post.setLikeCount(post.getLikeCount() - 1);
                    result = RateResultDTO.builder()
                            .success(true)
                            .message("Removed like successfully")
                            .build();
                }
            }
        } else {

            if (user.getLikedPosts() != null) {

                if (user.getLikedPosts().contains(post)) {
                    user.getLikedPosts().remove(post);
                    post.setLikeCount(post.getLikeCount() - 1);
                }
            }

            if (user.getDislikedPosts() == null) {
                user.setLikedPosts(Collections.singletonList(post));
                post.setDislikeCount(post.getDislikeCount() + 1);

                result = RateResultDTO.builder()
                        .success(true)
                        .message("Added dislike successfully")
                        .build();
            } else {
                if (!user.getDislikedPosts().contains(post)) {
                    user.getDislikedPosts().add(post);
                    post.setDislikeCount(post.getDislikeCount() + 1);

                    result = RateResultDTO.builder()
                            .success(true)
                            .message("Added dislike successfully")
                            .build();
                } else {
                    user.getDislikedPosts().remove(post);
                    post.setDislikeCount(post.getDislikeCount() - 1);

                    result = RateResultDTO.builder()
                            .success(true)
                            .message("Removed dislike successfully")
                            .build();
                }
            }
        }


        usersRepository.save(user);
        postRepository.save(post);

        result.setPost(postService.getById(dto.getPostId()).orElseThrow(IllegalArgumentException::new));

        return result;
    }
}
