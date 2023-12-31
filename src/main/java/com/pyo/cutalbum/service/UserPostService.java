package com.pyo.cutalbum.service;

import com.pyo.cutalbum.entity.UserPost;
import com.pyo.cutalbum.repository.UserPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserPostService {
    private final UserPostRepository userPostRepository;

    public void save(UserPost userPost)
    {
        userPostRepository.save(userPost);
    }
    public void like(Long id){
        userPostRepository.updateLikeView(id);
    }
    public boolean existsById(Long id)
    {
        return userPostRepository.existsById(id);
    }
    public Optional<UserPost> findById(Long id)
    {
        return userPostRepository.findById(id);
    }
}
