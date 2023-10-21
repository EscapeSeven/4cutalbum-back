package com.pyo.cutalbum.repository;

import com.pyo.cutalbum.entity.UserPost;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserPostRepository extends JpaRepository<UserPost,Long> {
    @Transactional
    @Modifying
    @Query(value = "update user_post p set p.likes = p.likes + 1 where p.id=:id",nativeQuery = true)
    void updateLikeView(@Param("id") Long id);
}
