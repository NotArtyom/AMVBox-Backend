package ru.itis.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.demo.models.FileInfo;
import ru.itis.demo.models.User;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserDTO {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private FileInfo fileInfo;
    private List<?> likes;
    private List<?> dislikes;

    public static UserDTO from(User user) {
        return UserDTO.builder()
                .email(user.getEmail())
                .name(user.getName())
                .surname(user.getSurname())
                .fileInfo(user.getAvatar())
                .id(user.getId())
                .likes(user.getLikedPosts())
                .dislikes(user.getDislikedPosts())
                .build();
    }

    public static List<UserDTO> from(List<User> users) {
        return users.stream()
                .map(UserDTO::from)
                .collect(Collectors.toList());
    }
}
