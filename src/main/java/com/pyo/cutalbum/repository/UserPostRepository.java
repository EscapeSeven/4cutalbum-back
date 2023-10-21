package com.pyo.cutalbum.repository;

import com.pyo.cutalbum.entity.UserPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPostRepository extends JpaRepository<UserPost,Long> {
}
