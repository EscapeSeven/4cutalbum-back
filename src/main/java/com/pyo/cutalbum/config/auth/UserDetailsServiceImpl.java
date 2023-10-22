package com.pyo.cutalbum.config.auth;

import com.pyo.cutalbum.entity.User;
import com.pyo.cutalbum.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String nickname) throws UsernameNotFoundException {
        User findUser = userRepository.findByNickname(nickname)
                .orElseThrow(() -> new UsernameNotFoundException("Can't find user with this nickname. -> " + nickname));


        return new org.springframework.security.core.userdetails.User(findUser.getNickname(), findUser.getPassword(), AuthorityUtils.createAuthorityList(findUser.getRole().toString()));
    }
}