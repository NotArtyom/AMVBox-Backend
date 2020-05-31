package ru.itis.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.demo.models.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByOwnerId(Long ownerId);

    @Query("select sum(likeCount) from Post where ownerId = :id")
    Long likeCountByUserId(@Param("id") Long userId);
}
