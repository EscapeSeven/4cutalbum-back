package com.pyo.cutalbum.service;

import com.pyo.cutalbum.entity.Dto.ApiResponse;
import com.pyo.cutalbum.entity.SignupForm;
import com.pyo.cutalbum.entity.User;
import com.pyo.cutalbum.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final ApiResponse apiResponse;

    public boolean signup(SignupForm signupForm) {


        String encPwd = encoder.encode(signupForm.getPassword());

        User user = userRepository.save(signupForm.toEntity(encPwd));

        if(user!=null) {

            return true;

        }
        return false;
    }

}
