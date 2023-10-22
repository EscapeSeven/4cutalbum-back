package com.pyo.cutalbum.controller;


import com.pyo.cutalbum.entity.Dto.ApiResponse;
import com.pyo.cutalbum.entity.SignupForm;
import com.pyo.cutalbum.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ApiResponse apiResponse;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(SignupForm signupForm) {
        boolean signup = userService.signup(signupForm);
        if (signup)
            return apiResponse.success("회원가입에 성공했습니다");
        return apiResponse.success("회원가입에 실패했습니다");
    }
}