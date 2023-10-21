package com.pyo.cutalbum.service;

import com.pyo.cutalbum.entity.UserPost;
import com.pyo.cutalbum.repository.UserPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserPostService {
    private final UserPostRepository userPostRepository;

    public void save(UserPost userPost)
    {
        userPostRepository.save(userPost);
    }
}
