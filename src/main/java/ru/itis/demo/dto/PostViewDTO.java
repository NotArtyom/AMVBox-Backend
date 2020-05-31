package ru.itis.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostViewDTO {

    private UserDTO userDTO;
    private Long ownerId;
    private String youtubeLink;
    private String description;
    private Long likeCount;
    private Long dislikeCount;
}
