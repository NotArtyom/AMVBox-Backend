package ru.itis.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Slf4j
@Table(name = "itis_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;
    private String email;
    private String hashPassword;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "file_info_id")
    private FileInfo avatar;

    @ManyToMany
    private List<Post> likedPosts;

    @ManyToMany
    private List<Post> dislikedPosts;

    @OneToMany(mappedBy = "ownerId")
    @Where(clause = "like_count > 5")
    private List<Post> popularPosts;

    @Enumerated(value = EnumType.STRING)
    private State state;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    private String confirmCode;

    @Transient
    private File sourceFile;

//    @PostLoad
//    public void loadFile() {
//        sourceFile = new File(avatar.getUrl());
//        String fileName = sourceFile.getName().substring(0, sourceFile.getName().lastIndexOf("."));
//        log.info("Load file for " + fileName);
//    }
}
