package com.pyo.cutalbum.entity;

import com.pyo.cutalbum.entity.enums.Role;
import lombok.Builder;
import lombok.Getter;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@Builder
public class SignupForm {

    @NotNull
    private String nickname;
    private String password;
    public User toEntity(String encPwd) {
        return User.builder()
                .nickname(nickname)
                .password(password)
                .role(Role.USER)
                .build();
    }
}